package main.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public record DataDTO(int id, LocalDate data, long enfermeiros, long tecnicos, long profissionais) {
}