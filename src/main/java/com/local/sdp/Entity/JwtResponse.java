package com.local.sdp.Entity;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JwtResponse {
    private String jwtToken;
    private String userName;
    private String role;
}
