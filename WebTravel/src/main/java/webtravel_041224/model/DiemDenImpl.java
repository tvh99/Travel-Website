package webtravel_041224.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import webtravel_041224.entities.DiemDen;



import java.util.ArrayList;
import java.util.List;


@Repository
public class DiemDenImpl implements DiemDenDao{

    @Override
    public List<DiemDen> timKiemDiemDen(String tuKhoa) {

        String strSQL = "Select dd from DiemDen dd where 1=1";

        if(tuKhoa!= null && !tuKhoa.isEmpty())
        {
            strSQL += " and (dd.diemDenId like :tuKhoa or dd.tenDiemDen like :tuKhoa )";
        }



        //Khai báo danh sách
        List<DiemDen> lstDiemDen = new ArrayList<>();

        TypedQuery<DiemDen> query = entityManager.createQuery(strSQL, DiemDen.class);

        if(tuKhoa!= null && !tuKhoa.isEmpty())
        {
            query.setParameter("tuKhoa","%" + tuKhoa + "%");
        }


        lstDiemDen = query.getResultList();

        return lstDiemDen;
    }

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    DiemDenRepository diemDenRepository;


    @Override
    public List<DiemDen> getList() {
        return diemDenRepository.findAll();
    }

    @Override
    public DiemDen getById(String id) {

        DiemDen objDD = null;
        try {
            objDD = (DiemDen) entityManager.createQuery("FROM DiemDen  dd  where dd.diemDenId = :ma")
                    .setParameter("ma", id)
                    .getSingleResult();
        }
        catch(Exception ex)
        {
            objDD = null;
        }
        return objDD;
    }

    @Override
    public boolean add(DiemDen objDiemDen) {

        diemDenRepository.save(objDiemDen);

        return false;
    }

    @Override
    public boolean update(DiemDen obj) {
        DiemDen objDiemDen = diemDenRepository.getReferenceById(obj.getDiemDenId());

        if(objDiemDen != null)
        {
            objDiemDen.setTenDiemDen(obj.getTenDiemDen());


            diemDenRepository.save(objDiemDen);

            return true;
        }
        return  false;
    }

    @Override
    public boolean delete(String id) {

        DiemDen objDD = getById(id);

        if (objDD != null) {
            diemDenRepository.delete(objDD);

            return true;
        }

        return false;
    }
}
