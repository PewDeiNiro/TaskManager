package com.pewde.taskmanager.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    @JsonProperty("id")
    private int userId;

    @JsonProperty("token")
    private String token;

}
