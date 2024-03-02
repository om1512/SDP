package com.local.sdp.Services.Interface;

import com.local.sdp.DAO.Interface.PhaseDAO;
import com.local.sdp.Entity.Phase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhaseServiceInterface extends JpaRepository<Phase, Integer> {
}
