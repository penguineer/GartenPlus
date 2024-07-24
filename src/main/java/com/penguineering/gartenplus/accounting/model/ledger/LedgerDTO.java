package com.penguineering.gartenplus.accounting.model.ledger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record LedgerDTO(
        @JsonProperty("id") UUID id,
        @JsonProperty("name") String name,
        @JsonProperty("start_date") LocalDate startDate,
        @JsonProperty("end_date") LocalDate endDate,
        @JsonProperty("closed") boolean closed) {

    @Override
    public String toString() {
        return String.format(
                "LedgerDTO{id=%s, name='%s', start_date=%s, end_date=%s, closed=%s}",
                id, name, startDate, endDate, closed);
    }
}
