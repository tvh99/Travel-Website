package webtravel_041224.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webtravel_041224.entities.BookingDetails;
import webtravel_041224.service.BookingDetailsService;


import java.util.List;

@Controller
public class BookingDetailsController {

    @Autowired
    BookingDetailsService bookingDetailsService;


    @RequestMapping(value = "/admin/bookingdetails", method = RequestMethod.GET)
    public String hienThiDanhSach(Model model) {
        List<BookingDetails> lstBookingDT = bookingDetailsService.layDanhSach();

        model.addAttribute("lstBookingDT", lstBookingDT);

        return "admin/QuanLyBookingDetails";
    }

    @RequestMapping(value = "/admin/bookingdetails/them")
    public String hienThiThemMoi(Model model) {
        model.addAttribute("bookingdetails", new BookingDetails());

        return "admin/BookingDetailsAdd";
    }


    @RequestMapping(value = "/admin/bookingdetails/sua/{id}")
    public String hienThiChiTietbookingdetails(@PathVariable("id") int id, Model model) {

        BookingDetails objBookingDetails = bookingDetailsService.layChiTiet(id);

        model.addAttribute("bookingdetails", objBookingDetails);



        return "admin/BookingDetailsAdd";
    }

    @RequestMapping(value = "/admin/bookingdetails/themMoibookingdetails", method = RequestMethod.POST)
    public String themMoiHoacSuaBookingDTs(@ModelAttribute("bookingdetails") @Valid BookingDetails objBookingDetails,
                                           BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("message", "Bạn cần phải nhập đủ thông tin");
            model.addAttribute("bookingdetails", objBookingDetails);
            return "admin/BookingDetailsAdd";
        }

        boolean isInsert = true;

        // Kiểm tra nếu objBookingDetails hoặc bookingDetailsId là null
        BookingDetails objDT = null;
        if (objBookingDetails != null && objBookingDetails.getBookingDetailsId() > 0) {
             objDT = bookingDetailsService.layChiTiet(objBookingDetails.getBookingDetailsId());
        }


        if (objDT != null) {
                isInsert = false; // Nếu tìm thấy bản ghi, tức là đang cập nhật
            }
        else {
           objDT = new BookingDetails();
        }


        boolean ketQua = false;

        if (isInsert) {
            ketQua = bookingDetailsService.themMoi(objBookingDetails);
        } else {
            ketQua = bookingDetailsService.capNhat(objBookingDetails);
        }

        if (ketQua) {

            return "redirect:/admin/bookingdetails";
        }

        return "admin/BookingDetailsAdd";
    }

    @RequestMapping(value = "/admin/bookingdetails/xoa/{id}")
    public String xoaThongTinBookingDTs(@PathVariable("id") int id) {

        BookingDetails objBookingD = bookingDetailsService.layChiTiet(id);

        if (objBookingD != null) {
            boolean ketQua = bookingDetailsService.xoa(id);

            if (ketQua) {
                return "redirect:/admin/bookingdetails";
            }
        }

        return "admin/QuanLyBookingDetails";
    }


}
