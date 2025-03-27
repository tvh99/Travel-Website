package webtravel_041224.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import webtravel_041224.entities.NguoiDung;
import webtravel_041224.service.NguoiDungService;

@Controller
public class DangNhapController {
    @Autowired
    NguoiDungService nguoiDungService;

    @RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
    public String hienThiDangNhap(Model model)
    {
        model.addAttribute("user", new NguoiDung());

        return "DangNhap";
    }

    @RequestMapping(value = "/thucHienDangNhap", method = RequestMethod.POST)
    public String xuLyDangNhap(@ModelAttribute("user") NguoiDung objUser, Model model, HttpSession session)
    {
        model.addAttribute("user", objUser);

        if(objUser.getTenDangNhap().isEmpty())
        {
            model.addAttribute("thongBao", "Bạn cần phải nhập tên đăng nhập");
            return "DangNhap";
        }

        if(objUser.getMatKhau().isEmpty())
        {
            model.addAttribute("thongBao", "Bạn cần phải nhập mật khẩu đăng nhập");
            return "DangNhap";
        }

        if(!objUser.getTenDangNhap().isEmpty() && !objUser.getMatKhau().isEmpty())
        {
            NguoiDung objUser2 = nguoiDungService.layThongTinDangNhap(objUser.getTenDangNhap());

            if(objUser2 != null)
            {
                if(objUser.getMatKhau().equals(objUser2.getMatKhau()))
                {
                    session.setAttribute("userOnline", objUser.getTenDangNhap());
                    return "redirect:/admin/goi";
                }
                else
                {
                    model.addAttribute("thongBao", "Mật khẩu đăng nhập không chính xác");
                    return "DangNhap";
                }
            }
            else
            {
                model.addAttribute("thongBao", "Tài khoản không tồn tại. Bạn vui lòng kiểm tra lại thông tin");
                return "DangNhap";
            }
        }

        return "DangNhap";
    }

    @RequestMapping(value = "/dang-xuat", method = RequestMethod.GET)
    public String hienThiDangXuat(Model model, HttpSession session)
    {
        session.removeAttribute("userOnline");

        return "redirect:/trang-chu";
    }
}
