package webtravel_041224.model;

import org.springframework.data.jpa.repository.JpaRepository;
import webtravel_041224.entities.NguoiDung;


public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {

    NguoiDung findByTenDangNhap(String ten);
}
