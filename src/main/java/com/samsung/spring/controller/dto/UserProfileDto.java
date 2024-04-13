package com.samsung.spring.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
    private long id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String photoUrl;
}
