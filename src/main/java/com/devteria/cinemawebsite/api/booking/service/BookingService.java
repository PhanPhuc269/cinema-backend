package com.devteria.cinemawebsite.api.booking.service;

import com.devteria.cinemawebsite.api.booking.dto.request.BookingRequest;
import com.devteria.cinemawebsite.api.booking.dto.response.BookingResponse;
import com.devteria.cinemawebsite.api.booking.dto.response.SeatResponse;
import com.devteria.cinemawebsite.api.booking.entity.*;
import com.devteria.cinemawebsite.api.booking.mapper.BookingMapper;
import com.devteria.cinemawebsite.api.booking.mapper.OrderItemMapper;
import com.devteria.cinemawebsite.api.booking.mapper.SeatMapper;
import com.devteria.cinemawebsite.api.booking.mapper.TicketMapper;
import com.devteria.cinemawebsite.api.booking.repository.BookingRepository;
import com.devteria.cinemawebsite.api.booking.repository.ComboRepository;
import com.devteria.cinemawebsite.api.booking.repository.SeatRepository;
import com.devteria.cinemawebsite.api.booking.repository.TicketRepository;
import com.devteria.cinemawebsite.api.showtime.entity.Showtime;
import com.devteria.cinemawebsite.api.showtime.repository.ShowtimeRepository;
import com.devteria.cinemawebsite.api.user.entity.User;
import com.devteria.cinemawebsite.api.user.repository.UserRepository;
import com.devteria.cinemawebsite.enums.BookingStatus;
import com.devteria.cinemawebsite.enums.StatusSeat;
import com.devteria.cinemawebsite.exception.AppException;
import com.devteria.cinemawebsite.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {
    ShowtimeRepository showtimeRepository;
    BookingRepository bookingRepository;
    UserRepository userRepository;
    SeatRepository seatRepository;
    ComboRepository comboRepository;
    TicketRepository ticketRepository;


    TicketMapper ticketMapper;
    BookingMapper bookingMapper;
    OrderItemMapper orderItemMapper;
    SeatMapper seatMapper;

    @Transactional
    public BookingResponse createBooking(String userId, BookingRequest request) {
        // 1. Validate input
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Showtime showtime = showtimeRepository.findById(request.getShowtimeId())
                .orElseThrow(() -> new AppException(ErrorCode.SHOWTIME_NOT_EXISTED));

        // 2. Process tickets and check seat availability
        List<Ticket> tickets = request.getTickets().stream().map(ticketRequest -> {
            // Find seat by seatNumber and roomId
            Seat seat = seatRepository.findBySeatNumberAndRoomId(
                            ticketRequest.getSeatNumber(), showtime.getRoom().getId())
                    .orElseThrow(() -> new AppException(ErrorCode.SEAT_NOT_EXISTED));

            // Check if seat is already booked for this showtime
            if (ticketRepository.existsBySeatIdAndBooking_Showtime_Id(seat.getId(), showtime.getId())) {
                throw new AppException(ErrorCode.SEAT_ALREADY_BOOKED);
            }

            return Ticket.builder()
                    .seat(seat)
                    .price(ticketRequest.getPrice())
                    .showtime(showtime) // Add if showtime is added to Ticket entity
                    .build();
        }).collect(Collectors.toList());

        // 3. Process order items (combos)
        List<OrderItem> orderItems = request.getOrderItems() != null
                ? request.getOrderItems().stream().map(orderItemRequest -> {
            Combo combo = comboRepository.findById(orderItemRequest.getComboId())
                    .orElseThrow(() -> new AppException(ErrorCode.COMBO_NOT_EXISTED));
            return OrderItem.builder()
                    .combo(combo)
                    .quantity(orderItemRequest.getQuantity())
                    .price(combo.getPrice() * orderItemRequest.getQuantity())
                    .build();
        }).collect(Collectors.toList())
                : List.of();

        // 4. Calculate total price
        double ticketTotal = tickets.stream().mapToDouble(Ticket::getPrice).sum();
        double orderItemTotal = orderItems.stream().mapToDouble(OrderItem::getPrice).sum();
        double totalPrice = ticketTotal + orderItemTotal;

        // 5. Create Booking entity
        Booking booking = Booking.builder()
                .user(user)
                .showtime(showtime)
                .tickets(tickets)
                .orderItems(orderItems)
                .promotionCode(request.getPromotionCode())
                .note(request.getNote())
                .totalAmount(totalPrice)
                .status(BookingStatus.PENDING)
                .bookingTime(LocalDateTime.now())
                .build();

        // 6. Set bidirectional relationships
        tickets.forEach(ticket -> ticket.setBooking(booking));
        orderItems.forEach(orderItem -> orderItem.setBooking(booking));
        try {
            return bookingMapper.toBookingResponse(bookingRepository.save(booking));
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.SEAT_ALREADY_BOOKED);
        }
    }

    public List<SeatResponse> getSeats(Long showtimeId) {
        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new AppException(ErrorCode.SHOWTIME_NOT_EXISTED));
        List<Seat> seats = seatRepository.findByRoom_id(showtime.getRoom().getId());

        //Lay cac ve da dat
        List<Ticket> bookedTickets = ticketRepository.findByShowtime_Id(showtimeId);
        //Tao danh sach seatResponse
        List<SeatResponse> seatResponses = seats.stream()
                .map(seat -> {
                    SeatResponse seatResponse = seatMapper.toSeatResponse(seat);
                    //Kiem tra ve da dat
                    boolean isBooked = bookedTickets.stream()
                            .anyMatch(ticket -> ticket.getSeat().getId().equals(seat.getId()));
                    StatusSeat status = isBooked ? StatusSeat.BOOKED : StatusSeat.AVAILABLE;
                    seatResponse.setStatus(status);
                    return seatResponse;
                })
                .toList();
        return seatResponses;
    }
}
