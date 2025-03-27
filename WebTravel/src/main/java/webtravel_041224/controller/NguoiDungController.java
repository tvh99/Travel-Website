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
import webtravel_041224.entities.NguoiDung;
import webtravel_041224.service.NguoiDungService;

import java.util.List;

@Controller
public class NguoiDungController {

    @Autowired
    NguoiDungService nguoiDungService;


    @RequestMapping(value = "/admin/nguoidung", method = RequestMethod.GET)
    public String hienThiDanhSachNDung(Model model) {
        List<NguoiDung> lstND = nguoiDungService.layDanhSach();

        model.addAttribute("lstND", lstND);

        return "admin/QuanLyNguoiDung";
    }

    @RequestMapping(value = "/admin/nguoidung/them")
    public String hienThiThemMoiNdung(Model model ) {


        model.addAttribute("nguoidung", new  NguoiDung());


        return "admin/NguoiDungAdd";
    }

    @RequestMapping(value = "/admin/nguoidung/sua/{id}")
    public String hienThiChiTietNDung(@PathVariable("id") Integer id, Model model) {

         NguoiDung objND = nguoiDungService.layChiTiet(id);

        model.addAttribute("nguoidung", objND);

        return "admin/NguoiDungAdd";
    }


    @RequestMapping(value = "/admin/nguoidung/themMoiND", method = RequestMethod.POST)
    public String themMoiHoacSuaDiemDen(@ModelAttribute("nguoidung") @Valid NguoiDung objND, BindingResult result, Model model) {
        if (result.hasErrors()) {

            model.addAttribute("message", "Bạn cần phải nhập đủ thông tin");
            model.addAttribute("nguoidung", objND);

            return "admin/NguoiDungAdd";
        } else {
            boolean isInsert = true;

            // Neu da co  thi la sua
            NguoiDung objND1 = nguoiDungService.layChiTiet(objND.getId());

            if (objND1 != null) {
                isInsert = false;
            }

            boolean ketQua = false;

            if (isInsert) {
                ketQua = nguoiDungService.themMoi(objND);
            } else {
                ketQua = nguoiDungService.capNhat(objND);
            }

            if (ketQua) {
                return "redirect:/admin/nguoidung";
            }

            return "admin/NguoiDungAdd";
        }
    }

    @RequestMapping(value = "/admin/nguoidung/xoa/{id}")
    public String xoaThongTinDiemDen(@PathVariable("id") Integer id) {

        NguoiDung objND = nguoiDungService.layChiTiet(id);

        if (objND != null) {
            boolean ketQua = nguoiDungService.xoa(id);

            if (ketQua) {
                return "redirect:/admin/nguoidung";
            }
        }

        return "admin/QuanLyNguoiDung";
    }

}
