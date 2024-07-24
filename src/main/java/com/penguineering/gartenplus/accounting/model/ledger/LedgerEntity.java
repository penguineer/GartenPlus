package com.penguineering.gartenplus.accounting.model.ledger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LedgerEntity extends AbstractPersistable<UUID> {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private boolean closed = false;

    public void updateFromDTO(LedgerDTO ledgerDTO) {
        this.name = ledgerDTO.name();
        this.startDate = ledgerDTO.startDate();
        this.endDate = ledgerDTO.endDate();
        this.closed = ledgerDTO.closed();
    }

    public LedgerDTO toDTO() {
        return new LedgerDTO(this.getId(), this.name, this.startDate, this.endDate, this.closed);
    }
}
