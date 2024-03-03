package com.local.sdp.REST;


import com.local.sdp.Config.EmailSenderService;
import com.local.sdp.Entity.Email;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/e-mail")
@CrossOrigin(origins = "http://localhost:4200")

public class EmailRESTController {
    @Autowired
    EmailSenderService emailSenderService;

    @PostMapping("")
    public String sendEmail(@RequestBody Email email) {
        try {
            for(String to:email.getTo()){
                emailSenderService.sendSimpleMessage(to, email.getSubject(), email.getBody());
            }
            return "saved successfully";
        } catch (MessagingException e) {
            return "Failed to send emails: " + e.getMessage();
        }
    }
}
