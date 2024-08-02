package com.penguineering.gartenplus.ui.content.accounting.ledgers;

import com.penguineering.gartenplus.accounting.model.ledger.LedgerDTO;
import com.penguineering.gartenplus.accounting.model.ledger.LedgerEntityService;
import com.penguineering.gartenplus.ui.appframe.GartenplusPage;
import com.penguineering.gartenplus.ui.content.accounting.AccountingLayout;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.util.Optional;
import java.util.UUID;

@Route(value = "ledgers", layout = AccountingLayout.class)
@RolesAllowed({"ADMINISTRATOR", "TREASURER"})
@PageTitle("GartenPlus | Buchhaltung | Ledger")
public class LedgersSettingsPage extends GartenplusPage {
    private final LedgerEntityService ledgerService;

    private final H3 editorHeadline;
    private final LedgerEditor editor;
    private final LedgersGrid ledgersGrid;

    public LedgersSettingsPage(LedgerEntityService ledgerService) {
        this.ledgerService = ledgerService;

        editorHeadline = new H3();
        add(editorHeadline);

        editor = new LedgerEditor(this::saveLedger, () -> editLedger(null));
        add(editor);

        add(new H3("Hauptbücher"));

        ledgersGrid = new LedgersGrid(this::editLedger, this::confirmDeleteLedger);
        add(ledgersGrid);

        loadData();
        editLedger(null);
    }

    private void loadData() {
        var ledgers = ledgerService.getAllLedgers();
        ledgersGrid.setItems(ledgers);
    }

    private void saveLedger(LedgerDTO ledgerDTO) {
        ledgerService.saveLedger(ledgerDTO);
        loadData();
    }

    private void editLedger(UUID id) {
        LedgerDTO ledger = Optional.ofNullable(id)
                .flatMap(ledgerService::getLedger)
                .orElse(null);

        editor.setLedger(ledger);

        editorHeadline.setText(ledger == null ? "Neues Hauptbuch" : "Hauptbuch bearbeiten");
    }

    private void confirmDeleteLedger(UUID id) {
        Optional.ofNullable(id)
                .flatMap(ledgerService::getLedger)
                .map(this::createDeleteConfirmDialog)
                .ifPresent(ConfirmDialog::open);
    }

    private ConfirmDialog createDeleteConfirmDialog(LedgerDTO ledger) {
        return new ConfirmDialog("Ledger " + ledger.name() + " löschen",
                "Soll der Ledger " + ledger.name() + " wirklich gelöscht werden?",
                "Ledger löschen",
                confirmed -> deleteLedger(ledger.id()),
                "Abbrechen",
                cancel -> {
                });
    }

    private void deleteLedger(UUID ledgerId) {
        ledgerService.deleteLedger(ledgerId);
        loadData();
    }
}
