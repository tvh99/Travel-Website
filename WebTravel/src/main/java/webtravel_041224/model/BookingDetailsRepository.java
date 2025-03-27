package webtravel_041224.model;

import org.springframework.data.jpa.repository.JpaRepository;
import webtravel_041224.entities.BookingDetails;
import java.util.List;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Integer> {
    List<BookingDetails> findByBookingId(int bookingId);

    List<BookingDetails> deleteByBookingId(int bookingId);
}
