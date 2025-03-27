package webtravel_041224.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import webtravel_041224.entities.BookingDetails;

import java.util.List;

@Repository
public class BookingDetailsImpl implements BookingDetailsDao {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    BookingDetailsRepository bookingDetailsRepository;

    @Override
    public List<BookingDetails> getList() {
        return bookingDetailsRepository.findAll();

    }

    @Override
    public BookingDetails getById(Integer id) {
        BookingDetails objBD = null;
        try {
            objBD = (BookingDetails) entityManager.createQuery("FROM BookingDetails  bd  where bd.bookingDetailsId = :ma")
                    .setParameter("ma", id)
                    .getSingleResult();
        }
        catch(Exception ex)
        {
            objBD = null;
        }
        return objBD;

    }

    @Override
    public boolean add(BookingDetails obj) {
        BookingDetails objBookingDetails = bookingDetailsRepository.save(obj);

        if (objBookingDetails != null) {


            return true;
        }
        return false;

    }

    @Override
    public boolean update(BookingDetails objbdz) {
        BookingDetails objBookingDetails = bookingDetailsRepository.getReferenceById(objbdz.getBookingDetailsId());

        if(objBookingDetails != null)
        {


            objBookingDetails.setTenKhachDuLich(objbdz.getTenKhachDuLich());
            objBookingDetails.setTuoi(objbdz.getTuoi());
            objBookingDetails.setYeuCauDacBiet(objbdz.getYeuCauDacBiet());

            bookingDetailsRepository.save(objBookingDetails);

            return true;

        }

        return  false;
    }

    @Override
    public boolean delete(Integer id) {
        BookingDetails objBookingDetails = getById(id);

        if(objBookingDetails != null) {
            bookingDetailsRepository.delete(objBookingDetails);

            return true;
        }

        return false;

    }

    @Override
    public List<BookingDetails> findByBookingId(int bookingId)
    {
        return bookingDetailsRepository.findByBookingId(bookingId);
    }

    @Override
    public List<BookingDetails> deleteByBookingId(int bookingId) {

        return bookingDetailsRepository.deleteByBookingId(bookingId);
    }
}
