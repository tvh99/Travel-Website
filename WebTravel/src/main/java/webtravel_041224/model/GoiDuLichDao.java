package webtravel_041224.model;

import webtravel_041224.entities.GoiDuLich;

import java.util.List;

public interface GoiDuLichDao extends IHanhDong<GoiDuLich,String> {
    List<GoiDuLich> timKiemPackageTour(String tuKhoa, String idDiemDen , int giaTu, int giaDen );


}
