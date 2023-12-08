package com.senac.web.reserve.model.dto;

import java.time.LocalDate;

public record ReserveDTO(LocalDate dtReserve, Long idCondominium, Long idCommonArea) {
}
