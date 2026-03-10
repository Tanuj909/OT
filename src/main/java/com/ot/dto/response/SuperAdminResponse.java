package com.ot.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SuperAdminResponse {
    private Long id;
    private String name;
    private String email;
    private Boolean active;        // 👈 Added active status
    private LocalDateTime createdAt;
}