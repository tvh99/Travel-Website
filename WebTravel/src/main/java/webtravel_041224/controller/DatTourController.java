package webtravel_041224.controller;


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

import java.util.List;

@Controller
public class DatTourController {

    @Autowired
    BookingService bookingService;

    @Autowired
    BookingDetailsService bookingDetailsService;




    @RequestMapping(value = "/gddt/{id}")
    public String hienThiThemMoiBKAz(Model model, @PathVariable("id") String ma) {
        GoiDuLich objGoiDuLich = goiDuLichService.layChiTiet(ma);

        Booking objBook = new Booking();

        model.addAttribute("booking", objBook);
        model.addAttribute("gdl", objGoiDuLich);

        return "GiaoDienDatTour";
    }


    @RequestMapping(value = "/gddt/chiitietdat/{id}")
    public String hienThiChiTietTourDaDatz(@PathVariable("id") int id, Model model) {

        Booking objBook = bookingService.layChiTiet(id);

        GoiDuLich objGoiDuLich = goiDuLichService.layChiTiet(objBook.getPackageID());

        List<BookingDetails> lstNguoiDiKem = bookingDetailsService.layDanhSachNguoiDiKem(objBook.getBookingID());


        model.addAttribute("booking", objBook);

        model.addAttribute("gdl", objGoiDuLich);

        model.addAttribute("lstNguoiDiKem", lstNguoiDiKem);


        return "ChiTietHoaDonDat";
    }

    @RequestMapping(value = "/gddt/themMoiBooking", method = RequestMethod.POST)
    public String themMoiDatTour(@ModelAttribute("booking") @Valid Booking objBooking,
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
                                         BindingResult result, Model model) {

        if(result.hasErrors()) {

            model.addAttribute("message", "Bạn cần phải nhập đủ thông tin");
            model.addAttribute("booking", objBooking);

            return "/GiaoDienDatTour";
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
                if(!tenKhachDuLich1.isEmpty())
                {
                    BookingDetails objNguoi1 = new BookingDetails();
                    objNguoi1.setTenKhachDuLich(tenKhachDuLich1);
                    objNguoi1.setTuoi(tuoi1);
                    objNguoi1.setYeuCauDacBiet(yeuCauDacBiet1);
                    objNguoi1.setBookingId(objBooking.getBookingID());


                    bookingDetailsService.themMoi(objNguoi1);
                }

                if(!tenKhachDuLich2.isEmpty() )
                {
                    BookingDetails objNguoi2 = new BookingDetails();

                    objNguoi2.setTenKhachDuLich(tenKhachDuLich2);
                    objNguoi2.setTuoi(tuoi2);
                    objNguoi2.setYeuCauDacBiet(yeuCauDacBiet2);
                    objNguoi2.setBookingId(objBooking.getBookingID());


                    bookingDetailsService.themMoi(objNguoi2);
                }

                if(!tenKhachDuLich3.isEmpty() )
                {
                    BookingDetails objNguoi3 = new BookingDetails();

                    objNguoi3.setTenKhachDuLich(tenKhachDuLich3);
                    objNguoi3.setTuoi(tuoi3);
                    objNguoi3.setYeuCauDacBiet(yeuCauDacBiet3);
                    objNguoi3.setBookingId(objBooking.getBookingID());


                    bookingDetailsService.themMoi(objNguoi3);
                }

                if(!tenKhachDuLich4.isEmpty() )
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
            }

            if (ketQua) {
                return "redirect:/gddt/chiitietdat/" + objBooking.getBookingID();
            }

            return "GiaoDienDatTour";
        }
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
