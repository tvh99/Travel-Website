package webtravel_041224.model;

import webtravel_041224.entities.BookingDetails;
import java.util.List;


public interface BookingDetailsDao extends IHanhDong<BookingDetails, Integer> {

    List<BookingDetails> findByBookingId(int bookingId);

    List<BookingDetails> deleteByBookingId(int bookingId);
}
