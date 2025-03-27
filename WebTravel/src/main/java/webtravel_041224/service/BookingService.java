package webtravel_041224.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webtravel_041224.entities.Booking;
import webtravel_041224.model.BookingDao;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingDao bookingDao;


    public List<Booking> layDanhSach()
    {
        return  bookingDao.getList();
    }

    public Booking layChiTiet(int bookingId) {
        return bookingDao.getById(bookingId);
    }

    public boolean themMoi(Booking objBooking)
    {
        return bookingDao.add(objBooking);
    }

    public boolean capNhat(Booking objBooking)
    {
        return bookingDao.update(objBooking);
    }

    public boolean xoa(int bookingId)
    {
        return bookingDao.delete(bookingId);
    }
}
