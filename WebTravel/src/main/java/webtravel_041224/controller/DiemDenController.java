package webtravel_041224.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import webtravel_041224.entities.DiemDen;
import webtravel_041224.service.DiemDenService;
import java.util.List;

@Controller
public class DiemDenController {

    @Autowired
    DiemDenService diemDenService;


    @RequestMapping(value = "/admin/diemden", method = RequestMethod.GET)
    public String hienThiDanhSachDiemDen(Model model) {
        List<DiemDen> lstDiemDen = diemDenService.layDanhSach();

        model.addAttribute("lstDiemDen", lstDiemDen);

        return "admin/QuanLyDiemDen";
    }

    @RequestMapping(value = "/admin/diemden/them")
    public String hienThiThemMoiDiemDen(Model model) {



        model.addAttribute("diemden", new DiemDen());

        return "admin/DiemDenAdd";
    }

    @RequestMapping(value = "/admin/diemden/sua/{id}")
    public String hienThiChiTietDiemDen(@PathVariable("id") String id, Model model) {

        DiemDen objDiemDen = diemDenService.layChiTiet(id);

        model.addAttribute("diemden", objDiemDen);

        return "admin/DiemDenAdd";
    }

    @RequestMapping(value = "/admin/diemden/themMoiDD", method = RequestMethod.POST)
    public String themMoiHoacSuaDiemDen(@ModelAttribute("diemden") @Valid DiemDen objDiemDen, BindingResult result, Model model) {
        if (result.hasErrors()) {

            model.addAttribute("message", "Bạn cần phải nhập đủ thông tin");
            model.addAttribute("diemden", objDiemDen);

            return "admin/DiemDenAdd";
        } else {
            boolean isInsert = true;

            // Neu da co  thi la sua
            DiemDen objDD01 = diemDenService.layChiTiet(objDiemDen.getDiemDenId());

            if (objDD01 != null) {
                isInsert = false;
            }

            boolean ketQua = false;

            if (isInsert) {
                ketQua = diemDenService.themMoi(objDiemDen);
            } else {
                ketQua = diemDenService.capNhat(objDiemDen);
            }

            if (ketQua) {
                return "redirect:/admin/diemden";
            }

            return "admin/DiemDenAdd";
        }
    }

    @RequestMapping(value = "/admin/diemden/xoa/{id}")
    public String xoaThongTinDiemDen(@PathVariable("id") String id) {

        DiemDen objDiemDen = diemDenService.layChiTiet(id);

        if (objDiemDen != null) {
            boolean ketQua = diemDenService.xoa(id);

            if (ketQua) {
                return "redirect:/admin/diemden";
            }
        }

        return "admin/QuanLyDiemDen";
    }
}
