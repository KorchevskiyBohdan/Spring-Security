package com.spring.security.backend.api.transformer;

import static java.lang.Math.toIntExact;

import java.util.List;

import org.springframework.security.oauth2.jwt.Jwt;

import com.spring.security.backend.api.dto.employee.EmployeeDto;
import com.spring.security.backend.security.enums.authority.Role;
public class EmployeeDtoTransformer {

    public static EmployeeDto toEmployeeDto(Jwt jwt) {
        return toEmployeeDto(toIntExact(jwt.getClaim("emploee_id")), toIntExact(jwt.getClaim("tenant_id")),
                jwt.getSubject(), jwt.getClaim("roles"));
    }

    private static EmployeeDto toEmployeeDto(Integer id, Integer tenantId, String email, List<Role> authorities) {
        return EmployeeDto.builder()
            .id(id)
            .tenantId(tenantId)
            .email(email)
            .authorities(authorities)
            .build();
    }
}
