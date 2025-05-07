package com.devteria.cinemawebsite.api.payment.service;

import com.devteria.cinemawebsite.api.booking.entity.Booking;
import com.devteria.cinemawebsite.api.booking.repository.BookingRepository;
import com.devteria.cinemawebsite.api.payment.dto.response.PaymentCancelResponse;
import com.devteria.cinemawebsite.api.payment.dto.response.PaymentResponse;
import com.devteria.cinemawebsite.api.payment.dto.response.PaymentResult;
import com.devteria.cinemawebsite.api.payment.entity.Payment;
import com.devteria.cinemawebsite.api.payment.helper.PaymentUtils;
import com.devteria.cinemawebsite.api.payment.mapper.PaymentMapper;
import com.devteria.cinemawebsite.api.payment.repository.PaymentRepository;
import com.devteria.cinemawebsite.enums.BookingStatus;
import com.devteria.cinemawebsite.enums.PaymentStatus;
import com.devteria.cinemawebsite.exception.AppException;
import com.devteria.cinemawebsite.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentService {
    PaymentRepository paymentRepository;
    BookingRepository bookingRepository;

    PaymentMapper paymentMapper;
    PaymentUtils paymentUtils; // Giả định util để kiểm tra thanh toán

    // Lấy danh sách giao dịch của người dùng
    public List<Payment> getPaymentsByUser(String userId) {
        try {
            return paymentRepository.findByBooking_User_id(userId);
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving payments: " + e.getMessage());
        }
    }

    // Lấy giao dịch theo ID
    public Payment getPaymentById(String paymentId) {
        try {
            return paymentRepository.findById(paymentId)
                    .orElseThrow(() -> new RuntimeException("Payment not found"));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving payment by ID: " + e.getMessage());
        }
    }

    // Tạo giao dịch mới
    public PaymentResponse createPayment(String bookingId, String userId) {
        try {
            Booking booking = bookingRepository.findById(bookingId)
                    .orElseThrow(() -> new AppException(ErrorCode.BOOKING_NOT_EXISTED));

            double amount = booking.getTotalAmount();
            if (amount <= 0) {
                throw new AppException((ErrorCode.INVALID_PAYMENT_AMOUNT));
            }

            //Tạo mã giao dịch duy nhất để làm key nhận biết chuyển khoản
            String transactionId = paymentUtils.generateTransactionId(bookingId, userId);

            Payment payment = Payment.builder()
                    .booking(booking)
                    .amount(amount)
                    .paymentMethod("bank")
                    .transactionId(transactionId)
                    .status(PaymentStatus.PENDING)
                    .paymentTime(LocalDateTime.now())
                    .build();

            payment = paymentRepository.save(payment);

            return paymentMapper.toResponse(payment);
        } catch (Exception e) {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }
    public String generateQrCodeUrl(Payment payment) {
        String description = URLEncoder.encode(payment.getTransactionId(), StandardCharsets.UTF_8);
        return String.format("https://img.vietqr.io/image/%s-%s-%s.png?amount=%s&addInfo=%s&accountName=%s",
                "MB", "0363353610", "compact",
                payment.getAmount(), description, "PHAN HONG PHUC");
    }
    // Xử lý thanh toán
    public PaymentResponse processPayment(String paymentId) {
        try {
            Payment payment = paymentRepository.findById(paymentId)
                    .orElseThrow(() -> new RuntimeException("Payment not found"));

            if (payment.getStatus()!=PaymentStatus.PENDING) {
                throw new AppException(ErrorCode.PAYMENT_NOT_PENDING);
            }

            int timeout = 5 * 60 * 1000; // 5 phút
            int checkInterval = 3 * 1000; // 3 giây
            int elapsed = 0;

            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    Payment currentPayment = paymentRepository.findById(paymentId)
                            .orElseThrow(() -> new RuntimeException("Payment not found during processing"));

                    if (currentPayment.getStatus()==PaymentStatus.FAILED) {
                        scheduler.shutdown();
                        return;
                    }

                    PaymentResult paymentResult = paymentUtils.checkPaid(currentPayment.getAmount(), currentPayment.getTransactionId());
                    if (paymentResult.isSuccess()) {
                        currentPayment.setStatus(PaymentStatus.COMPLETED);
                        paymentRepository.save(currentPayment);

                        Booking booking = bookingRepository.findById(currentPayment.getBooking().getId())
                                .orElseThrow(() -> new RuntimeException("Booking not found"));
                        booking.setStatus(BookingStatus.CONFIRMED);
                        bookingRepository.save(booking);

                        scheduler.shutdown();
                    }
                } catch (Exception e) {
                    scheduler.shutdown();
                }
            }, 0, checkInterval, TimeUnit.MILLISECONDS);

            // Đóng scheduler sau timeout
            scheduler.schedule(() -> {
                if (payment.getStatus() != PaymentStatus.COMPLETED) {
                    payment.setStatus(PaymentStatus.FAILED);
                    paymentRepository.save(payment);
                }
                scheduler.shutdown();
            }, timeout, TimeUnit.MILLISECONDS);
            return paymentMapper.toResponse(payment);

        } catch (Exception e) {
            throw  new RuntimeException("Error creating payment: " + e.getMessage());
        }
    }

    // Lấy tất cả giao dịch
    public List<Payment> getAllPayments() {
        try {
            return paymentRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving all payments: " + e.getMessage());
        }
    }

    // Hủy giao dịch
    public PaymentCancelResponse cancelPayment(String paymentId) {
        try {
            Payment payment = paymentRepository.findById(paymentId)
                    .orElseThrow(() -> new RuntimeException("Payment not found"));

            if (payment.getStatus()!=PaymentStatus.PENDING) {
                throw new RuntimeException("Payment cannot be canceled because it is not pending.");
            }

            payment.setStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);

            return new PaymentCancelResponse(true, "Payment canceled successfully.");
        } catch (Exception e) {
             throw new AppException(ErrorCode.PAYMENT_CANCELLATION_FAILED);
        }
    }
}
