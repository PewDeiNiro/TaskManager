package com.pewde.taskmanager.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithTokenRequest {

    private int userId;

    private String token;

}
