package com.local.sdp.Services.Repository;


import com.local.sdp.DAO.Interface.DomainDAO;
import com.local.sdp.Entity.Domain;
import com.local.sdp.Services.Interface.DomainServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainService implements DomainServiceInterface {

    DomainDAO domainDAO;
    @Autowired
    DomainService(DomainDAO domainDAO){
        this.domainDAO= domainDAO;
    }
    @Override
    public void save(Domain domain) {
        domainDAO.save(domain);
    }

    @Override
    public int delete(int id) {
        return domainDAO.delete(id);
    }

    @Override
    public List<Domain> getDomain() {
        return domainDAO.getDomain();
    }

    @Override
    public Domain getDomainById(int id) {
        return domainDAO.getDomainById(id);
    }
}
