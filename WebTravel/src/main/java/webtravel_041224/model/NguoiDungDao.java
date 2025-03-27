package webtravel_041224.model;


import webtravel_041224.entities.NguoiDung;

public interface NguoiDungDao extends IHanhDong<NguoiDung, Integer>{

    NguoiDung layNguoiDungTheoTenDangNhap(String tenDangNhap);

}
