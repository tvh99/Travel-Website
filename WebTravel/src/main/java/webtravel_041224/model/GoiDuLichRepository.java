package webtravel_041224.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webtravel_041224.entities.GoiDuLich;

import java.util.List;

public interface GoiDuLichRepository extends JpaRepository<GoiDuLich, String> {


    @Query(value = "Select gd from GoiDuLich gd where " +
            " (:tuKhoa is null or length(:tuKhoa) = 0 or gd.tenGoi like %:tuKhoa%) and " +
            " (:idDiemDen is null or length(:idDiemDen) = 0 or gd.idDiemDen = :idDiemDen) ")

    public List<GoiDuLich> timKiemPackageTour(@Param("tuKhoa") String tuKhoa, @Param("idDiemDen") String idDiemDen);

    public List<GoiDuLich>findByPackageId(String tuKhoa);


}
