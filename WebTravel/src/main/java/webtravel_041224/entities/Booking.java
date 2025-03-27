package webtravel_041224.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Entity
@Table(name = "Booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BookingID" ,unique = true, nullable = false)
    private int bookingID;
    @Column(name="PackageID",  length = 10)
    private String packageID;
    @Column(name="NgayDatPhong")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayDatPhong;
    @Column(name="NgayDuLich" )
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayDuLich;
    @Column(name="TongChiPhi")
    @NotNull(message = "Bạn cần phải nhập so tien ")
    @Min(value = 0, message = "Bạn phải nhập so tien  lớn hơn 0")
    private int tongChiPhi;
    @Column(name="HoTen", length = 500)
    private String hoTen;
    @Column(name="NgaySinh")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngaySinh;
    @Column(name="Email", length = 500)
    private String email;
    @Column(name="DiaChi", length = 500)
    private String diaChi;

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }



    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public Date getNgayDatPhong() {
        return ngayDatPhong;
    }

    public void setNgayDatPhong(Date ngayDatPhong) {
        this.ngayDatPhong = ngayDatPhong;
    }

    public Date getNgayDuLich() {
        return ngayDuLich;
    }

    public void setNgayDuLich(Date ngayDuLich) {
        this.ngayDuLich = ngayDuLich;
    }

    public int getTongChiPhi() {
        return tongChiPhi;
    }

    public void setTongChiPhi(int tongChiPhi) {
        this.tongChiPhi = tongChiPhi;
    }

//    public Set<BookingDetails> getLstNguoiDiKem() {
//        return bookingdetails;
//    }
//
//    public void setLstNguoiDiKem(Set<BookingDetails> lstNguoiDiKem) {
//        this.bookingdetails = lstNguoiDiKem;
//    }
//
//    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
//    private Set<BookingDetails> bookingdetails;

}
