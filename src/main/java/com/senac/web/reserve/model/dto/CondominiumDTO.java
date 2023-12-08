package com.senac.web.reserve.model.dto;

import com.senac.web.reserve.model.entities.UserRole;

import java.time.LocalDate;

public record CondominiumDTO(String name, String password, String cpf, LocalDate birth, UserRole role) {
}
