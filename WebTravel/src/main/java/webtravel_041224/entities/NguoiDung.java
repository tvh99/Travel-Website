package webtravel_041224.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "NguoiDung")
public class NguoiDung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
    private int id;
    @Column(name = "TenDangNhap", nullable = true, length = 50)
    private String tenDangNhap;
    @Column(name = "MatKhau", nullable = true, length = 50)
    private String matKhau;
    @Column(name = "HoTen", nullable = true, length = 30)
    private String hoTen;
    @Column(name = "DienThoai", nullable = true, length = 20)
    private String dienThoai;
    @Column(name = "Email", nullable = true, length = 50)
    private String email;
    @Column(name = "DiaChi", nullable = true, length = 150)
    private String diaChi;

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
