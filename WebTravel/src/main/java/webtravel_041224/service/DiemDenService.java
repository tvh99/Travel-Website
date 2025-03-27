package webtravel_041224.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webtravel_041224.entities.DiemDen;
import webtravel_041224.model.DiemDenDao;

import java.util.List;

@Service
public class DiemDenService {

    @Autowired
    DiemDenDao diemDenDao;

    public List<DiemDen> timKiemDiemDen(String tuKhoa)

    {
        return diemDenDao.timKiemDiemDen(tuKhoa);
    }

    public List<DiemDen> layDanhSach()
    {
        return  diemDenDao.getList();
    }

    public DiemDen layChiTiet(String diemDenId) {
        return diemDenDao.getById(diemDenId);
    }

    public boolean themMoi(DiemDen objDiemDen)
    {
        return diemDenDao.add(objDiemDen);
    }

    public boolean capNhat(DiemDen objDiemDen)
    {
        return diemDenDao.update(objDiemDen);
    }

    public boolean xoa(String maChuDe)
    {
        return diemDenDao.delete(maChuDe);
    }



}
