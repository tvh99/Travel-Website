package webtravel_041224.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webtravel_041224.entities.NguoiDung;
import webtravel_041224.model.NguoiDungDao;

import java.util.List;

@Service
public class NguoiDungService {

    @Autowired
    NguoiDungDao nguoiDungDao;

    public NguoiDung layThongTinDangNhap(String tenDangNhap)
    {
        return nguoiDungDao.layNguoiDungTheoTenDangNhap(tenDangNhap);
    }

    public List<NguoiDung> layDanhSach() {
        return nguoiDungDao.getList();
    }

    public NguoiDung layChiTiet(Integer id) {
        return nguoiDungDao.getById(id);
    }

    public boolean themMoi(NguoiDung objNguoiDung) {
        try {
            nguoiDungDao.add(objNguoiDung);
            return true;
        } catch (Exception e) {
            System.out.println("Thêm không thành công! Lỗi chi tiết: " + e.getMessage());
            return false;
        }
    }

    public boolean capNhat(NguoiDung objNguoiDung) {
        return nguoiDungDao.update(objNguoiDung);
    }

    public boolean xoa(Integer id) {
        return nguoiDungDao.delete(id);
    }
}
