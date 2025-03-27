package webtravel_041224.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "diemden")
public class DiemDen {
    @Id
    @Column(name = "DiemDenId" , nullable = false, length = 10)
    private String diemDenId;
    @Column(name = "TenDiemDen" , nullable = false, length = 500)
    private String tenDiemDen;


    public String getDiemDenId() {
        return diemDenId;
    }

    public void setDiemDenId(String diemDenId) {
        this.diemDenId = diemDenId;
    }

    public String getTenDiemDen() {
        return tenDiemDen;
    }

    public void setTenDiemDen(String tenDiemDen) {
        this.tenDiemDen = tenDiemDen;
    }

}
