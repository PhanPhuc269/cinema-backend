package com.devteria.cinemawebsite.api.payment.helper;

import com.devteria.cinemawebsite.api.payment.dto.response.PaymentResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.Normalizer;
import java.time.Instant;
import java.util.Random;
import java.util.regex.Pattern;

@Component
public class PaymentUtils {

    @Value("${payment.url}")
    private String paymentApiUrl;

    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();

    public String removeVietnameseTones(String str) {
        if (str == null) return "";
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized)
                .replaceAll("")
                .replaceAll("đ", "d")
                .replaceAll("Đ", "D");
    }

    public String removeWhitespaceAndHyphen(String str) {
        if (str == null) return "";
        return str.replaceAll("[\\s\\-:]+", "");
    }

    public PaymentResult checkPaid(double price, String description) {
        try {
            String response = restTemplate.getForObject(paymentApiUrl, String.class);
            JsonNode jsonResponse = objectMapper.readTree(response);
            JsonNode data = jsonResponse.get("data");

            if (data == null || !data.isArray() || data.size() == 0) {
                return new PaymentResult(false, "No payment data available");
            }

            JsonNode lastPaid = data.get(data.size() - 1);
            double lastPrice = lastPaid.get("Giá trị").asDouble();
            String lastContent = removeVietnameseTones(lastPaid.get("Mô tả").asText());
            String normalizedDescription = removeWhitespaceAndHyphen(description);

            System.out.println("Normalized Description: " + normalizedDescription);

            if (lastPrice >= price && removeWhitespaceAndHyphen(lastContent).contains(normalizedDescription)) {
                return new PaymentResult(true, "Payment verified successfully");
            } else {
                return new PaymentResult(false, "Payment details do not match");
            }
        } catch (Exception e) {
            System.err.println("Error checking payment: " + e.getMessage());
            return new PaymentResult(false, "API error: " + e.getMessage());
        }
    }

    static final String PREFIX = "TX";
    static final Random random = new Random();

    public String generateTransactionId(String bookingId, String userId) {
        // Lấy timestamp hiện tại (epoch seconds)
        long timestamp = Instant.now().getEpochSecond();

        // Lấy một phần của bookingId và userId (lấy 4 ký tự đầu, loại bỏ dấu -)
        String shortBookingId = bookingId != null && !bookingId.isEmpty()
                ? bookingId.replaceAll("-", "").substring(0, Math.min(4, bookingId.length()))
                : "0000";
        String shortUserId = userId != null && !userId.isEmpty()
                ? userId.replaceAll("-", "").substring(0, Math.min(4, userId.length()))
                : "0000";

        // Thêm số ngẫu nhiên (0-999) để đảm bảo duy nhất
        int randomNum = random.nextInt(1000);

        // Tạo mã giao dịch (không chứa dấu -)
        String transactionId = String.format("%s%s%s%d%03d", PREFIX, shortBookingId, shortUserId, timestamp, randomNum);

        // Giới hạn độ dài (tối đa 50 ký tự)
        if (transactionId.length() > 50) {
            transactionId = transactionId.substring(0, 50);
        }

        return transactionId;
    }

    // Hàm tạo description không chứa dấu -
    public String generateDescription(String bookingId) {
        // Loại bỏ dấu - trong bookingId
        String cleanBookingId = bookingId != null ? bookingId.replaceAll("-", "") : "Unknown";
        return "BookingId" + cleanBookingId;
    }
}
