package com.local.sdp.Services.Repository;

import com.local.sdp.DAO.Interface.TechnologyDAO;
import com.local.sdp.Entity.Technologies;
import com.local.sdp.Services.Interface.TechnologyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyService implements TechnologyServiceInterface {
    TechnologyDAO technologyDAO;

    @Autowired
    public TechnologyService(TechnologyDAO technologyDAO) {
        this.technologyDAO = technologyDAO;
    }

    @Override
    public void save(Technologies technologies) {
        technologyDAO.save(technologies);
    }

    @Override
    public int delete(int id) {
        return technologyDAO.delete(id);
    }

    @Override
    public List<Technologies> getTechnology() {
        return technologyDAO.getTechnology();
    }

    @Override
    public Technologies getTechnologyById(int id) {
        return technologyDAO.getTechnologyById(id);
    }
}
