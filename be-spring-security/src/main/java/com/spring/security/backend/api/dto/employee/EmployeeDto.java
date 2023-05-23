package com.spring.security.backend.api.dto.employee;

import java.util.ArrayList;
import java.util.List;

import com.spring.security.backend.security.enums.authority.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Integer id;
    private Integer tenantId;

    @Builder.Default
    private List<Role> authorities = new ArrayList<>();
}
