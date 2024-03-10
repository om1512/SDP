package com.local.sdp.Services.Interface;

import com.local.sdp.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskServiceInterface extends JpaRepository<Task, Integer> {

}
