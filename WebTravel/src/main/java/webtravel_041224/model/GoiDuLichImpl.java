package webtravel_041224.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import webtravel_041224.entities.GoiDuLich;

import java.util.List;


@Repository
public class GoiDuLichImpl implements GoiDuLichDao {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    GoiDuLichRepository goiDuLichRepository;

    @Override
    public List<GoiDuLich> timKiemPackageTour(String tuKhoa, String idDiemDen, int giaTu, int giaDen) {
        String strSQL = "SELECT gd FROM GoiDuLich gd WHERE 1=1";

        if (tuKhoa != null && !tuKhoa.isEmpty()) {
            strSQL += " AND (gd.packageId LIKE :tuKhoa OR gd.tenGoi LIKE :tuKhoa OR gd.moTa LIKE :tuKhoa)";
        }

        if (idDiemDen != null && !idDiemDen.isEmpty()) {
            strSQL += " AND gd.idDiemDen = :idDiemDen";
        }

        if (giaTu > 0) {
            strSQL += " AND gd.giaGoi >= :giaTu";
        }

        if (giaDen > 0) {
            strSQL += " AND gd.giaGoi <= :giaDen";
        }

        TypedQuery<GoiDuLich> query = entityManager.createQuery(strSQL, GoiDuLich.class);

        if (tuKhoa != null && !tuKhoa.isEmpty()) {
            query.setParameter("tuKhoa", "%" + tuKhoa + "%");
        }

        if (idDiemDen != null && !idDiemDen.isEmpty()) {
            query.setParameter("idDiemDen", idDiemDen);
        }

        if (giaTu > 0) {
            query.setParameter("giaTu", giaTu);
        }

        if (giaDen > 0) {
            query.setParameter("giaDen", giaDen);
        }

        return query.getResultList();
    }


    @Override
    public List<GoiDuLich> getList() {

        return goiDuLichRepository.findAll();
    }

    @Override
    public GoiDuLich getById(String id) {

        GoiDuLich objGoiDuLich = null;
        try {
            objGoiDuLich = (GoiDuLich) entityManager.createQuery("FROM GoiDuLich gd WHERE gd.packageId = :ma")
                    .setParameter("ma", id)
                    .getSingleResult();
        }
        catch(Exception ex)
        {
            objGoiDuLich = null;
        }
        return objGoiDuLich;
    }

    @Override
    public boolean add(GoiDuLich objGDL) {

        GoiDuLich objGoiDuLich = goiDuLichRepository.save(objGDL);

        if(objGoiDuLich != null)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(GoiDuLich obj) {
        try {
            GoiDuLich objGoiDuLich = getById(obj.getPackageId());

            if (objGoiDuLich == null) {
                System.out.println("Không tìm thấy gói du lịch với ID: " + obj.getPackageId());
                return false;
            }

            // Cập nhật thông tin
            objGoiDuLich.setTenGoi(obj.getTenGoi());
            objGoiDuLich.setMoTa(obj.getMoTa());
            objGoiDuLich.setGiaGoi(obj.getGiaGoi());
            objGoiDuLich.setNgayThucHien(obj.getNgayThucHien());
            objGoiDuLich.setNgayCapNhat(obj.getNgayCapNhat());
            objGoiDuLich.setNgayTao(obj.getNgayTao());
            objGoiDuLich.setAnh(obj.getAnh());
            objGoiDuLich.setIdDiemDen(obj.getIdDiemDen());
            objGoiDuLich.setDiemKhoiHanh(obj.getDiemKhoiHanh());

            goiDuLichRepository.save(objGoiDuLich);

            System.out.println("Cập nhật thành công gói du lịch: " + obj.getPackageId());
            return true;
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật gói du lịch: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean delete(String id) {
        GoiDuLich objGoiDuLich = getById(id);
        if(objGoiDuLich != null)
        {
            goiDuLichRepository.delete(objGoiDuLich);
            return true;
        }

        return false;
    }
}
