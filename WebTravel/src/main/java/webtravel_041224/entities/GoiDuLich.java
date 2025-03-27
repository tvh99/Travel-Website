package webtravel_041224.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "GoiDuLich")
public class GoiDuLich {

    @Id
    @Column(name="Id", nullable = false, length = 10)
    @NotBlank(message = "Bạn cần phải nhập mã id")
    private String packageId;
    @Column(name="TenGoi", nullable = false, length = 500)
    @NotEmpty(message = "Bạn cần phải nhập tên gói")
    private String tenGoi;
    @Column(name="MoTa", nullable = false, length = 5000)
    private String moTa;

    @Column(name="Gia", nullable = false)
    @NotNull(message = "Bạn cần phải nhập giá ")
    @Min(value = 0, message = "Bạn phải nhập giá  lớn hơn 0")
    private int giaGoi;
    @Column(name="NgayThucHien", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayThucHien;

    @Column(name = "NgayTao" , nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayTao;
    @Column(name = "NgayCapNhat", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayCapNhat;
    @Column(name="Anh", nullable = false, length = 255)
    private String anh;
    @Column(name="IdDiemDen", nullable = false, length = 10)
    private String idDiemDen;
    @Column(name="DiemKhoiHanh", nullable = false, length = 500)
    private String diemKhoiHanh;

    public String getDiemKhoiHanh() {
        return diemKhoiHanh;
    }

    public void setDiemKhoiHanh(String diemKhoiHanh) {
        this.diemKhoiHanh = diemKhoiHanh;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getTenGoi() {
        return tenGoi;
    }

    public void setTenGoi(String tenGoi) {
        this.tenGoi = tenGoi;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getGiaGoi() {
        return giaGoi;
    }

    public void setGiaGoi(int giaGoi) {
        this.giaGoi = giaGoi;
    }

    public Date getNgayThucHien() {
        return ngayThucHien;
    }

    public void setNgayThucHien(Date ngayThucHien) {
        this.ngayThucHien = ngayThucHien;
    }

    public String getIdDiemDen() {
        return idDiemDen;
    }

    public void setIdDiemDen(String idDiemDen) {
        this.idDiemDen = idDiemDen;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }
}
