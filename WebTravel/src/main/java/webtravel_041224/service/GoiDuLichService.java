package webtravel_041224.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webtravel_041224.entities.GoiDuLich;
import webtravel_041224.model.GoiDuLichDao;

import java.util.List;

@Service
public class GoiDuLichService {

    @Autowired
    GoiDuLichDao goiDuLichDao;

    public List<GoiDuLich> timKiemThongTinPackageTour(String tuKhoa, String idDiemDen, int giaTu, int giaDen )

    {
        return goiDuLichDao.timKiemPackageTour(tuKhoa,idDiemDen,giaTu,giaDen);
    }


    public List<GoiDuLich> layDanhSach()
    {
        return  goiDuLichDao.getList();
    }

    public GoiDuLich layChiTiet(String id) {return  goiDuLichDao.getById(id);}

    public boolean themMoi(GoiDuLich objGoiDuLich)
    {
        return goiDuLichDao.add(objGoiDuLich);
    }

    public boolean capNhat(GoiDuLich objGoiDuLich)
    {
        return goiDuLichDao.update(objGoiDuLich);
    }

    public boolean xoa(String packageTourId)
    {
        return goiDuLichDao.delete(packageTourId);
    }

}
