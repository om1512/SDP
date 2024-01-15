package com.local.sdp.Services.Interface;

import com.local.sdp.Entity.Domain;

import java.util.List;

public interface DomainServiceInterface {
    void save(Domain domain);
    int delete(int id);

    List<Domain> getDomain();

    Domain getDomainById(int id);
}
