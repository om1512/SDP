package com.local.sdp.Entity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Email {
    private List<String> to;
    private String subject;
    private String body;
}
