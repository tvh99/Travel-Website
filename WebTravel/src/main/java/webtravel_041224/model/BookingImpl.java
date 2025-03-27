package webtravel_041224.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import webtravel_041224.entities.Booking;

import java.util.List;

@Repository
public class BookingImpl implements BookingDao{


    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public List<Booking> getList() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getById(Integer bookingID) {
        Booking objBooking = null;
        try {
            objBooking = (Booking) entityManager.createQuery("FROM Booking  b  where b.bookingID = :ma")
                    .setParameter("ma", bookingID)
                    .getSingleResult();
        }
        catch(Exception ex)
        {
            objBooking = null;
        }
        return objBooking;
    }

    @Override
    public boolean add(Booking obj) {

        Booking objBooking = bookingRepository.save(obj);

        if (objBooking != null) {



            return true;
        }
        return false;
    }



    @Override
    public boolean update(Booking obj) {
        Booking objBooking = bookingRepository.getReferenceById(obj.getBookingID());

        if(objBooking != null)
        {

            objBooking.setNgayDatPhong(obj.getNgayDatPhong());
            objBooking.setNgayDuLich(obj.getNgayDuLich());
            objBooking.setTongChiPhi(obj.getTongChiPhi());
            objBooking.setHoTen(obj.getHoTen());
            objBooking.setNgaySinh(obj.getNgaySinh());
            objBooking.setEmail(obj.getEmail());
            objBooking.setDiaChi(obj.getDiaChi());



            bookingRepository.save(objBooking);

            return true;


        }

        return  false;

    }

    @Override
    public boolean delete(Integer id) {
        Booking objBooking = getById(id);

        if(objBooking != null) {
            bookingRepository.delete(objBooking);

            return true;
        }

        return false;
    }
}
