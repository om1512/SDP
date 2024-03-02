package com.local.sdp.Services.Repository;

import com.local.sdp.Services.Interface.PhaseCustomServiceInterface;
import com.local.sdp.Services.Interface.PhaseServiceInterface;
import org.springframework.stereotype.Service;


@Service
public class PhaseService implements PhaseCustomServiceInterface {
    @Override
    public String sayHi() {
        return "Hi from OG Om";
    }
}
