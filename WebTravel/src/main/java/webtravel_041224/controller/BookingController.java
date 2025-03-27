package webtravel_041224.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webtravel_041224.entities.Booking;
import webtravel_041224.entities.BookingDetails;
import webtravel_041224.entities.GoiDuLich;
import webtravel_041224.service.BookingDetailsService;
import webtravel_041224.service.BookingService;
import webtravel_041224.service.GoiDuLichService;

import java.util.*;

@Controller
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    BookingDetailsService bookingDetailsService;


    @RequestMapping(value = "/admin/booking", method = RequestMethod.GET)
    public String hienThiDanhSachBK(Model model) {
        List<Booking> lstBooking = bookingService.layDanhSach();

        model.addAttribute("lstBooking", lstBooking);

        return "admin/QuanLyBooking";
    }

    @RequestMapping(value = "/admin/booking/them")
    public String hienThiThemMoiBookingg(Model model ) {

        Booking objBook = new Booking();

        model.addAttribute("obj", objBook);


        return "admin/BookingUpdate";
    }


    @RequestMapping(value = "/admin/booking/sua/{id}")
    public String hienThiChiTietbookingaa(@PathVariable("id") int id, Model model) {

        Booking objBook = bookingService.layChiTiet(id);

        GoiDuLich objGoiDuLich = goiDuLichService.layChiTiet(objBook.getPackageID());

        List<BookingDetails> lstNguoiDiKem = bookingDetailsService.layDanhSachNguoiDiKem(objBook.getBookingID());


        model.addAttribute("booking", objBook);

        model.addAttribute("gdl", objGoiDuLich);

        model.addAttribute("lstNguoiDiKem", lstNguoiDiKem);

        return "admin/BookingUpdate";
    }

    @RequestMapping(value = "/admin/booking/themMoiBooking", method = RequestMethod.POST)
    public String themMoiHoacSuaBooking(
            @ModelAttribute("booking") @Valid Booking objBooking,
            @RequestParam(value = "id1", required = false, defaultValue = "0") int id1,
            @RequestParam(value = "id2", required = false, defaultValue = "0") int id2,
            @RequestParam(value = "id3", required = false, defaultValue = "0") int id3,
            @RequestParam(value = "id4", required = false, defaultValue = "0") int id4,
            @RequestParam(value = "tenKhachDuLich1", required = false, defaultValue = "") String tenKhachDuLich1,
            @RequestParam(value = "tuoi1", required = false, defaultValue = "0") int tuoi1,
            @RequestParam(value = "yeuCauDacBiet1", required = false, defaultValue = "") String yeuCauDacBiet1,
            @RequestParam(value = "tenKhachDuLich2", required = false, defaultValue = "") String tenKhachDuLich2,
            @RequestParam(value = "tuoi2", required = false, defaultValue = "0") int tuoi2,
            @RequestParam(value = "yeuCauDacBiet2", required = false, defaultValue = "") String yeuCauDacBiet2,
            @RequestParam(value = "tenKhachDuLich3", required = false, defaultValue = "") String tenKhachDuLich3,
            @RequestParam(value = "tuoi3", required = false, defaultValue = "0") int tuoi3,
            @RequestParam(value = "yeuCauDacBiet3", required = false, defaultValue = "") String yeuCauDacBiet3,
            @RequestParam(value = "tenKhachDuLich4", required = false, defaultValue = "") String tenKhachDuLich4,
            @RequestParam(value = "tuoi4", required = false, defaultValue = "0") int tuoi4,
            @RequestParam(value = "yeuCauDacBiet4", required = false, defaultValue = "") String yeuCauDacBiet4,
            BindingResult result, Model model){

        if(result.hasErrors()) {

            model.addAttribute("message", "Bạn cần phải nhập đủ thông tin");
            model.addAttribute("booking", objBooking);

            return "admin/BookingUpdate";
        } else {
            boolean isInsert = true;

            // Neu da co thi la sua
            Booking objBK01 = bookingService.layChiTiet(objBooking.getBookingID());

            if (objBK01 != null) {
                isInsert = false;
            }

            boolean ketQua = false;

            if (isInsert) {

                ketQua = bookingService.themMoi(objBooking);

                //Lay thong tin chi tiet tren giao dien va xu ly them vao Bookingdetail
                if (!tenKhachDuLich1.isEmpty() && tuoi1 > 0 && !yeuCauDacBiet1.isEmpty()) {


                        BookingDetails objNguoi1 = new BookingDetails();
                        objNguoi1.setTenKhachDuLich(tenKhachDuLich1);
                        objNguoi1.setTuoi(tuoi1);
                        objNguoi1.setYeuCauDacBiet(yeuCauDacBiet1);
                        objNguoi1.setBookingId(objBooking.getBookingID());

                        bookingDetailsService.themMoi(objNguoi1);

                }



                if (!tenKhachDuLich2.isEmpty() && tuoi2 >0 && !yeuCauDacBiet2.isEmpty()) {


                        BookingDetails objNguoi2 = new BookingDetails();
                        objNguoi2.setTenKhachDuLich(tenKhachDuLich2);
                        objNguoi2.setTuoi(tuoi2);
                        objNguoi2.setYeuCauDacBiet(yeuCauDacBiet2);
                        objNguoi2.setBookingId(objBooking.getBookingID());

                        bookingDetailsService.themMoi(objNguoi2);

                }


                if(!tenKhachDuLich3.isEmpty()  && tuoi3 >0 && !yeuCauDacBiet3.isEmpty()  )
                {
                    BookingDetails objNguoi3 = new BookingDetails();

                    objNguoi3.setTenKhachDuLich(tenKhachDuLich3);
                    objNguoi3.setTuoi(tuoi3);
                    objNguoi3.setYeuCauDacBiet(yeuCauDacBiet3);
                    objNguoi3.setBookingId(objBooking.getBookingID());



                    bookingDetailsService.themMoi(objNguoi3);
                }

                if(!tenKhachDuLich4.isEmpty()  && tuoi4 >0 && !yeuCauDacBiet4.isEmpty() )
                {
                    BookingDetails objNguoi4 = new BookingDetails();

                    objNguoi4.setTenKhachDuLich(tenKhachDuLich4);
                    objNguoi4.setTuoi(tuoi4);
                    objNguoi4.setYeuCauDacBiet(yeuCauDacBiet4);
                    objNguoi4.setBookingId(objBooking.getBookingID());


                    bookingDetailsService.themMoi(objNguoi4);
                }


            } else {

                ketQua = bookingService.capNhat(objBooking);

                // Cập nhật danh sách người đi cùng
                if(id1 > 0)
                {
                    BookingDetails objNguoi = bookingDetailsService.layChiTiet(id1);
                    if(objNguoi != null)
                    {
                        objNguoi.setTenKhachDuLich(tenKhachDuLich1);
                        objNguoi.setTuoi(tuoi1);
                        objNguoi.setYeuCauDacBiet(yeuCauDacBiet1);
                        bookingDetailsService.capNhat(objNguoi);
                    }
                }
                if(id2 > 0)
                {
                    BookingDetails objNguoi = bookingDetailsService.layChiTiet(id2);
                    if(objNguoi != null)
                    {
                        objNguoi.setTenKhachDuLich(tenKhachDuLich2);
                        objNguoi.setTuoi(tuoi2);
                        objNguoi.setYeuCauDacBiet(yeuCauDacBiet2);
                        bookingDetailsService.capNhat(objNguoi);
                    }
                }
                if(id3 > 0)
                {
                    BookingDetails objNguoi = bookingDetailsService.layChiTiet(id3);
                    if(objNguoi != null)
                    {
                        objNguoi.setTenKhachDuLich(tenKhachDuLich3);
                        objNguoi.setTuoi(tuoi3);
                        objNguoi.setYeuCauDacBiet(yeuCauDacBiet3);
                        bookingDetailsService.capNhat(objNguoi);
                    }
                }
                if(id4 > 0)
                {
                    BookingDetails objNguoi = bookingDetailsService.layChiTiet(id4);
                    if(objNguoi != null)
                    {
                        objNguoi.setTenKhachDuLich(tenKhachDuLich4);
                        objNguoi.setTuoi(tuoi4);
                        objNguoi.setYeuCauDacBiet(yeuCauDacBiet4);
                        bookingDetailsService.capNhat(objNguoi);
                    }
                }

            }

            if (ketQua) {
                return "redirect:/admin/booking";
            }

            return "admin/BookingUpdate";
        }
    }


    @RequestMapping(value = "/admin/booking/xoa/{id}")
    public String xoaThongTinBooking(@PathVariable("id") int id) {

        Booking objBooking = bookingService.layChiTiet(id);

        if (objBooking != null) {
            boolean ketQua = bookingService.xoa(id);

            if (ketQua) {
                return "redirect:/admin/booking";
            }
        }

        return "admin/QuanLyBooking";
    }
    @RequestMapping(value = "/admin/bookingvsbookingdetails/xoa/{id}")
    public String xoaThongTinBookingDTsTrongBooking(@PathVariable("id") int id) {
        BookingDetails objBookingD = bookingDetailsService.layChiTiet(id);

        if (objBookingD != null) {
            int bookingId = objBookingD.getBookingId(); // Lấy ID của Booking chứa BookingDetails này
            boolean ketQua = bookingDetailsService.xoa(id);

            if (ketQua) {
                return "redirect:/admin/booking/sua/" + bookingId; // Quay về trang sửa Booking
            }
        }

        return "admin/BookingUpdate"; // Trường hợp không tìm thấy BookingDetails
    }


    @Autowired
    GoiDuLichService goiDuLichService;

    @ModelAttribute("lstGDL")
    public List<GoiDuLich> hienThiDanhSachGDL()
    {
        //Lấy danh sách goi
        List<GoiDuLich> lstGDL = goiDuLichService.layDanhSach();

        return lstGDL;
    }

    @ModelAttribute("bkt")
    public List<BookingDetails> hienThiDanhSachBKDT()
    {
        //Lấy danh sách
        List<BookingDetails> bkt = bookingDetailsService.layDanhSach();

        return bkt;
    }


}
