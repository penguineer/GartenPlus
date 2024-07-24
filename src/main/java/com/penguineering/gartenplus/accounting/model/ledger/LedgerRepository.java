package com.penguineering.gartenplus.accounting.model.ledger;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LedgerRepository extends CrudRepository<LedgerEntity, UUID> {
    LedgerEntity getOne(UUID uuid);

    Iterable<LedgerEntity> findAllByClosed(boolean closed);
}
