package webtravel_041224.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "bookingdetails")
public class BookingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BookingDetailsId" ,unique = true, nullable = false)
    private Integer bookingDetailsId;

    @Column(name="BookingId", nullable = true)
    private int bookingId;
    @Column(name="TenKhachDuLich", nullable = true, length = 50)
    private String tenKhachDuLich;
    @Column(name="Tuoi", nullable = true)
    private int tuoi;
    @Column(name="YeuCauDacBiet", nullable = true, length = 100)
    private String yeuCauDacBiet;


    public Integer getBookingDetailsId() {
        return bookingDetailsId;
    }

    public void setBookingDetailsId(Integer bookingDetailsId) {
        this.bookingDetailsId = bookingDetailsId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getTenKhachDuLich() {
        return tenKhachDuLich;
    }

    public void setTenKhachDuLich(String tenKhachDuLich) {
        this.tenKhachDuLich = tenKhachDuLich;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getYeuCauDacBiet() {
        return yeuCauDacBiet;
    }

    public void setYeuCauDacBiet(String yeuCauDacBiet) {
        this.yeuCauDacBiet = yeuCauDacBiet;
    }

//    public Booking getBook() {
//        return book;
//    }
//
//    public void setBook(Booking book) {
//        this.book = book;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "bookingId")
//    private Booking book;
}
