package webtravel_041224.model;


import webtravel_041224.entities.DiemDen;


import java.util.List;

public interface DiemDenDao extends IHanhDong<DiemDen,String>{
    List<DiemDen> timKiemDiemDen(String tuKhoa);

}
