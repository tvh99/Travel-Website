package webtravel_041224.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import webtravel_041224.entities.*;
import webtravel_041224.service.BookingDetailsService;
import webtravel_041224.service.BookingService;
import webtravel_041224.service.DiemDenService;
import webtravel_041224.service.GoiDuLichService;

import java.util.List;

@Controller
public class TrangChuController {

    @Autowired
    GoiDuLichService goiDuLichService;
    @Autowired
    private BookingDetailsService bookingDetailsService;


    @RequestMapping(value = "/trang-chu")
    public String hienThiTrangChu(@ModelAttribute("goiDL") GoiDuLichModel objGoiDuLich, Model model)
    {

        model.addAttribute("goiDL", objGoiDuLich);
        //Lấy danh sách thông tin goi du lich
        List<GoiDuLich> lstGoidl = goiDuLichService.timKiemThongTinPackageTour(objGoiDuLich.getTuKhoa(),objGoiDuLich.getIdDiemDen(),objGoiDuLich.getGiaTu(),objGoiDuLich.getGiaDen());

        //Đưa vào model để hiển thị ra view

        model.addAttribute("lstGoidl", lstGoidl);



        return "TrangChu";
    }
    @RequestMapping(value = "/about")
    public String hienThiAbout(Model model) {

        return "About";
    }

    @RequestMapping(value = "/service")
    public String hienThiService(Model model) {

        return "Service";
    }

    @RequestMapping(value = "/tourpackage")
    public String hienThiTourPackage(@ModelAttribute("goidl") GoiDuLichModel objGoiDuLich, Model model) {

        model.addAttribute("goidl", objGoiDuLich);
        //Lấy danh sách thông tin goi du lich
        List<GoiDuLich> lstGoi = goiDuLichService.timKiemThongTinPackageTour(objGoiDuLich.getTuKhoa(),objGoiDuLich.getIdDiemDen(),objGoiDuLich.getGiaTu(),objGoiDuLich.getGiaDen());

        //Đưa vào model để hiển thị ra view

        model.addAttribute("lstGoi", lstGoi);


        return "TourPackage";
    }

    @Autowired
    BookingService bookingService;

    @RequestMapping(value = "/gddt")
    public String hienThiGiaoDienDatTouraaa(Model model ) {

        model.addAttribute("booking", new Booking());

        return "GiaoDienDatTour";
    }


    @RequestMapping(value = "/chitiet/{ma}")
    public String hienThiChiTietTourPzxa(Model model, @PathVariable("ma") String ma)
    {
        GoiDuLich objGoiDuLich = goiDuLichService.layChiTiet(ma);

        model.addAttribute("gdl", objGoiDuLich);

        return "ChiTiet";
    }

    @RequestMapping(value = "/blog")
    public String hienThiTourBlogg(Model model) {

        return "Blog";
    }

    @Autowired
    DiemDenService diemDenService;

    @ModelAttribute("lstDiemDen")
    public List<DiemDen> hienThiDanhSachDiemDen()
    {
        //Lấy danh sách chủ đề
        List<DiemDen> lstDiemDen = diemDenService.layDanhSach();

        return lstDiemDen;
    }

    @ModelAttribute("lstGDL")
    public List<GoiDuLich> hienThiDanhSachGDL()
    {
        //Lấy danh sách chủ đề
        List<GoiDuLich> lstGDL = goiDuLichService.layDanhSach();

        return lstGDL;
    }

    @ModelAttribute("lstBTS")
    public List<BookingDetails> hienThiDanhSachBTS()
    {
        //Lấy danh sách chủ đề
        List<BookingDetails> lstBTS = bookingDetailsService.layDanhSach();

        return lstBTS;
    }

}


