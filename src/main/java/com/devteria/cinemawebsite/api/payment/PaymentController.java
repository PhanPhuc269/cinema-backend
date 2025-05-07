package com.devteria.cinemawebsite.api.payment;

import com.devteria.cinemawebsite.api.payment.dto.request.PaymentProcessRequest;
import com.devteria.cinemawebsite.api.payment.dto.request.PaymentRequest;
import com.devteria.cinemawebsite.api.payment.dto.response.PaymentResponse;
import com.devteria.cinemawebsite.api.payment.service.PaymentService;
import com.devteria.cinemawebsite.base.ApiResponse;
import com.devteria.cinemawebsite.enums.PaymentStatus;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PaymentController {
    PaymentService paymentService;

    @PostMapping
    ApiResponse<PaymentResponse> createPayment(@RequestBody PaymentRequest request)
            throws ParseException, JOSEException {
        String bookingId = request.getBookingId();
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = jwt.getClaimAsString("userId"); // nếu token dùng "sub" để chứa user ID

        PaymentResponse payment = paymentService.createPayment(bookingId, userId);
        return ApiResponse.<PaymentResponse>builder()
                .result(payment)
                .build();
    }

    // Xử lý thanh toán cho giao dịch
    @PostMapping("/process")
    public ResponseEntity<PaymentResponse> processPayment(
            @RequestBody PaymentProcessRequest request) {
        PaymentResponse response = paymentService.processPayment(request.getPaymentId());
        if (response.getStatus()== PaymentStatus.COMPLETED) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
//
//    // Hủy giao dịch
//    @PostMapping("/cancel")
//    public ResponseEntity<PaymentCancelResponse> cancelPayment(
//            @RequestParam String paymentId) {
//        PaymentCancelResponse response = paymentService.cancelPayment(paymentId);
//        if (response.isSuccess()) {
//            return ResponseEntity.ok(response);
//        } else {
//            return ResponseEntity.badRequest().body(response);
//        }
//    }

//    // Lấy thông tin giao dịch theo ID
//    @GetMapping("/{paymentId}")
//    public ResponseEntity<Payment> getPaymentById(
//            @PathVariable String paymentId) {
//        Payment payment = paymentService.getPaymentById(paymentId);
//        return ResponseEntity.ok(payment);
//    }
//
//    // Lấy danh sách giao dịch của người dùng
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<Payment>> getPaymentsByUser(
//            @PathVariable String userId) {
//        List<Payment> payments = paymentService.getPaymentsByUser(userId);
//        return ResponseEntity.ok(payments);
//    }
//
//    // Lấy tất cả giao dịch (chỉ dành cho admin)
//    @GetMapping("/all")
//    public ResponseEntity<List<Payment>> getAllPayments() {
//        List<Payment> payments = paymentService.getAllPayments();
//        return ResponseEntity.ok(payments);
//    }
//    @PostMapping("/introspect")
//    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
//            throws ParseException, JOSEException {
//        var result = paymentService.introspect(request);
//        return ApiResponse.<IntrospectResponse>builder()
//                .result(result)
//                .build();
//    }
//
//    @PostMapping("/refresh")
//    ApiResponse<CinemaResponse> authenticate(@RequestBody RefreshRequest request)
//            throws ParseException, JOSEException {
//        var result = paymentService.refreshToken(request);
//        return ApiResponse.<CinemaResponse>builder()
//                .result(result)
//                .build();
//    }
//
//    @PostMapping("/logout")
//    ApiResponse<Void> logout(@RequestBody LogoutRequest request)
//            throws ParseException, JOSEException {
//        paymentService.logout(request);
//        return ApiResponse.<Void>builder()
//                .build();
//    }
}

