package com.local.sdp.DAO.Repository;

import com.local.sdp.DAO.Interface.DomainDAO;
import com.local.sdp.Entity.Domain;
import com.local.sdp.Entity.Technologies;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
public class DomainImpl implements DomainDAO {

    EntityManager entityManager;
    @Autowired
    DomainImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(Domain domain) {
        entityManager.merge(domain);
    }

    @Override
    @Transactional
    public int delete(int id) {
        return entityManager.createQuery("delete from Domain where id=:id").setParameter("id",id).executeUpdate();

    }

    @Override
    public List<Domain> getDomain() {
        return entityManager.createQuery("from Domain", Domain.class).getResultList();
    }

    @Override
    public Domain getDomainById(int id) {
        return entityManager.find(Domain.class, id);
    }
}
