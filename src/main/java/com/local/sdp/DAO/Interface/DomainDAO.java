package com.local.sdp.DAO.Interface;

import com.local.sdp.Entity.Domain;
import com.local.sdp.Entity.Technologies;

import java.util.List;

public interface DomainDAO {
    void save(Domain domain);
    int delete(int id);

    List<Domain> getDomain();

    Domain getDomainById(int id);
}
