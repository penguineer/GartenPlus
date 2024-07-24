package com.penguineering.gartenplus.accounting.model.ledger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LedgerEntityService {

    private final LedgerRepository ledgerRepository;

    @Autowired
    public LedgerEntityService(LedgerRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    @Transactional(readOnly = true)
    public List<LedgerDTO> getAllLedgers() {
        return StreamSupport.stream(ledgerRepository.findAll().spliterator(), false)
                .map(LedgerEntity::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<LedgerDTO> getLedger(UUID id) {
        return ledgerRepository.findById(id)
                .map(LedgerEntity::toDTO);
    }

    @Transactional
    public LedgerDTO saveLedger(LedgerDTO ledgerDTO) {
        return Optional.ofNullable(ledgerDTO)
                // load or create entity
                .map(l -> Optional.of(l)
                        .map(LedgerDTO::id)
                        .flatMap(ledgerRepository::findById)
                        .orElseGet(LedgerEntity::new))
                // update entity
                .map(e -> {
                    e.updateFromDTO(ledgerDTO);
                    return e;
                })
                // save entity
                .map(ledgerRepository::save)
                .map(LedgerEntity::toDTO)
                .orElse(null);
    }

    @Transactional
    public void deleteLedger(UUID id) {
        ledgerRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<LedgerDTO> findAllByClosed(boolean closed) {
        return StreamSupport.stream(ledgerRepository.findAllByClosed(closed).spliterator(), false)
                .map(LedgerEntity::toDTO)
                .collect(Collectors.toList());
    }
}
