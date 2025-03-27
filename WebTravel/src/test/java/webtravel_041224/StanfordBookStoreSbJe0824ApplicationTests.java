package webtravel_041224;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import webtravel_041224.entities.DiemDen;
import webtravel_041224.entities.GoiDuLich;
import webtravel_041224.entities.NguoiDung;
import webtravel_041224.model.GoiDuLichDao;
import webtravel_041224.service.DiemDenService;


import java.util.List;

@SpringBootTest
class StanfordBookStoreSbJe0824ApplicationTests {

    @Autowired
    GoiDuLichDao goiDuLichDao;

    @Autowired
    DiemDenService diemDenService;

    @Test
    void contextLoads() {
    }

    @Test
    void layDanhSachGoi() {

        List<GoiDuLich> lstGoi = goiDuLichDao.getList();

        System.out.println("Danh sach goi du lich: ");
        for (GoiDuLich p : lstGoi)
        {
            System.out.println(p.getPackageId() + "\t" + p.getTenGoi());
        }
    }

    @Test
    void layDanhSachDiemDen() {

        List<DiemDen> lstDiemDen = diemDenService.layDanhSach();

        System.out.println("Danh sach chu de: ");
        for (DiemDen d : lstDiemDen)
        {
            System.out.println(d.getDiemDenId() + "\t" + d.getTenDiemDen());
        }
    }

}
