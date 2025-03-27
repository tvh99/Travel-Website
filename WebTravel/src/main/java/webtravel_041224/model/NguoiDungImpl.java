package webtravel_041224.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import webtravel_041224.entities.NguoiDung;


import java.util.List;

@Repository
public class NguoiDungImpl implements NguoiDungDao{

    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<NguoiDung> getList() {
        return nguoiDungRepository.findAll();
    }

    @Override
    public NguoiDung getById(Integer id) {
        NguoiDung objND = null;
        try {
            objND = (NguoiDung) entityManager.createQuery("FROM NguoiDung  nd  where nd.id = :ma")
                    .setParameter("ma", id)
                    .getSingleResult();
        }
        catch(Exception ex)
        {
            objND = null;
        }
        return objND;
    }

    @Override
    public boolean add(NguoiDung objND) {

        nguoiDungRepository.save(objND);

        return false;
    }

    @Override
    public boolean update(NguoiDung obj) {

        NguoiDung objND = nguoiDungRepository.getReferenceById(obj.getId());

        if(objND != null)
        {
            objND.setTenDangNhap(obj.getTenDangNhap());
            objND.setMatKhau(obj.getMatKhau());
            objND.setDienThoai(obj.getDienThoai());
            objND.setHoTen(obj.getHoTen());
            objND.setEmail(obj.getEmail());
            objND.setDiaChi(obj.getDiaChi());


            nguoiDungRepository.save(objND);

            return true;
        }
        return  false;
    }

    @Override
    public boolean delete(Integer id) {

        NguoiDung objND = getById(id);

        if (objND != null) {
            nguoiDungRepository.delete(objND);

            return true;
        }

        return false;

    }

    @Override
    public NguoiDung layNguoiDungTheoTenDangNhap(String tenDangNhap) {
        return nguoiDungRepository.findByTenDangNhap(tenDangNhap);
    }
}
