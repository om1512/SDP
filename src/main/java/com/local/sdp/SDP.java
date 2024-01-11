package com.local.sdp;

import com.local.sdp.DAO.Interface.FacultyDAO;
import com.local.sdp.DAO.Interface.StudentDAO;
import com.local.sdp.DAO.Interface.UserDAO;
import com.local.sdp.Entity.Faculty;
import com.local.sdp.Entity.Student;
import com.local.sdp.Entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Date;
import java.util.Calendar;

@SpringBootApplication
public class SDP {

	public static void main(String[] args) {
		SpringApplication.run(SDP.class, args);
	}
}
