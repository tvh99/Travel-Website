package webtravel_041224.model;

import org.springframework.data.jpa.repository.JpaRepository;
import webtravel_041224.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {


}
