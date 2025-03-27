package webtravel_041224.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import webtravel_041224.entities.DiemDen;
import webtravel_041224.entities.GoiDuLich;
import webtravel_041224.entities.GoiDuLichModel;
import webtravel_041224.service.DiemDenService;
import webtravel_041224.service.GoiDuLichService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GoiDuLichController {

    @Autowired
    GoiDuLichService goiDuLichService;
    @Autowired
    DiemDenService diemDenService;




    @RequestMapping(value = "/admin/goi")
    public String hienThiDanhSachGoiDuLich(@ModelAttribute("goi") GoiDuLichModel objGoiDuLich, Model model) {

        model.addAttribute("goi", objGoiDuLich);
        //Lấy danh sách thông tin goi du lich
        List<GoiDuLich> lstGoi = goiDuLichService.timKiemThongTinPackageTour(objGoiDuLich.getTuKhoa(),objGoiDuLich.getIdDiemDen(),objGoiDuLich.getGiaTu(),objGoiDuLich.getGiaDen());

        //Đưa vào model để hiển thị ra view

        model.addAttribute("lstGoi", lstGoi);

        return "admin/QuanLyGoiDuLich";
    }

    @RequestMapping(value = "/admin/goi/them")
    public String hienThiThemMoiGoiDuLich(Model model) {

        model.addAttribute("goi", new GoiDuLich());

        return "admin/GoiDuLichAdd";
    }

    @RequestMapping(value = "/admin/goi/sua/{id}")
    public String hienThiChiTietGoiDuLichaa(@PathVariable("id") String id, Model model) {

        GoiDuLich objGoiDuLich = goiDuLichService.layChiTiet(id);

        model.addAttribute("goi", objGoiDuLich);


        return "admin/GoiDuLichAdd";
    }


    //Lay duong dan upload anh
    @Value("${fileupload.path}")
    private String fileUploadPath;

    @RequestMapping(value = "/admin/goi/themMoiGoi", method = RequestMethod.POST)
    public String themMoiHoacSuaGoiDuLich( @Valid @ModelAttribute("goi")  GoiDuLich objGoiDuLich, BindingResult result, @RequestParam("fUpload") MultipartFile fUpload, HttpServletRequest request, Model model)
    {


        if (result.hasErrors()) {

            model.addAttribute("message", "Bạn cần phải nhập đủ thông tin");
            model.addAttribute("goi", objGoiDuLich);

            return "admin/GoiDuLichAdd";
        }

        else
        {
            boolean isInsert = true;

            String tenAnh = "";

            // Neu da co  thi la sua
            GoiDuLich objGDz = goiDuLichService.layChiTiet(objGoiDuLich.getPackageId());

            if (objGDz != null) {
                isInsert = false;

                tenAnh = objGDz.getAnh();
                System.out.println("Ten anh: " + tenAnh);
            }

            //Xử lý upload file
            if(fUpload != null && !fUpload.isEmpty())
            {
                //Lấy tên ảnh
                tenAnh = fUpload.getOriginalFilename();
                String strPath = fileUploadPath;


                try
                {
                    //Tạo file
                    File file = new File(strPath, tenAnh);
                    System.out.println("Đường dẫn upload: " + strPath);
                    fUpload.transferTo(file);
                }
                catch (IOException ex)
                {
                    System.out.println("Có lỗi xảy ra khi upload file: " + ex.getMessage());
                }
            }

            //Gán ảnh vào đối tượng để lưu  sb
            objGoiDuLich.setAnh(tenAnh);

            boolean ketQua = false;

            if (isInsert) {

                ketQua = goiDuLichService.themMoi(objGoiDuLich);

            } else {
                ketQua = goiDuLichService.capNhat(objGoiDuLich);
            }

            if (ketQua) {

                return "redirect:/admin/goi";
            }

            return "admin/QuanLyGoiDuLich";
        }
    }

    @RequestMapping(value = "/admin/goi/xoa/{id}")
    public String xoaThongTinGoiDuLich(@PathVariable("id") String id) {

        GoiDuLich objGoiDuLich = goiDuLichService.layChiTiet(id);

        if(objGoiDuLich != null)
        {
            boolean ketQua = goiDuLichService.xoa(id);

            if(ketQua)
            {
                return "redirect:/admin/goi";
            }
        }

        return "admin/QuanLyGoiDuLich";
    }

    @ModelAttribute("lstDiemDen")
    public List<DiemDen> hienThiDanhSachDiemDen()
    {
        //Lấy danh sách
        List<DiemDen> lstDiemDen = new ArrayList<>();

        lstDiemDen = diemDenService.layDanhSach();

        return lstDiemDen;
    }
}
