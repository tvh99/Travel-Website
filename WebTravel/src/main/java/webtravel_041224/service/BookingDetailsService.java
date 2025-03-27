package webtravel_041224.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webtravel_041224.entities.BookingDetails;
import webtravel_041224.model.BookingDetailsDao;

import java.util.List;


@Service
public class BookingDetailsService {

    @Autowired
    BookingDetailsDao bookingDetailsDao;




    public List<BookingDetails> layDanhSach()
    {
        return  bookingDetailsDao.getList();
    }

    public BookingDetails layChiTiet(int bookingDetailsId) {
        return bookingDetailsDao.getById(bookingDetailsId);
    }



    public boolean themMoi(BookingDetails objBookingDetails)
    {
        return bookingDetailsDao.add(objBookingDetails);
    }

    public boolean capNhat(BookingDetails objBookingDetails)
    {
        return bookingDetailsDao.update(objBookingDetails);
    }

    public boolean xoa(int bookingDetailsId)
    {
        return bookingDetailsDao.delete(bookingDetailsId);
    }


    public List<BookingDetails> layDanhSachNguoiDiKem(int bookingId)
    {
        return  bookingDetailsDao.findByBookingId(bookingId);
    }

    public void xoaTheoBookingId(int bookingId) {

        bookingDetailsDao.deleteByBookingId(bookingId);
    }


}
