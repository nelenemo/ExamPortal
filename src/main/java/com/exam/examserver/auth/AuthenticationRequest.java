package com.exam.examserver.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"username","password"})
public class AuthenticationRequest {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}
