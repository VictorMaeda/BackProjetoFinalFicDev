package main.DTOs;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;

public record DataDTO(int id, LocalDate data, long enfermeiros, long tecnicos, long profissionais) {
}