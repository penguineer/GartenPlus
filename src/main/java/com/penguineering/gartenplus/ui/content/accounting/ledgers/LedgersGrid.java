package com.penguineering.gartenplus.ui.content.accounting.ledgers;

import com.penguineering.gartenplus.accounting.model.ledger.LedgerDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;

import java.util.UUID;
import java.util.function.Consumer;

public class LedgersGrid extends Grid<LedgerDTO> {

    public LedgersGrid(Consumer<UUID> editAction,
                       Consumer<UUID> deleteAction) {
        setWidthFull();
        setAllRowsVisible(true);

        addColumn(ledger -> ledger.startDate().toString())
                .setHeader("Startdatum")
                .setAutoWidth(true)
                .setFlexGrow(0);

        addColumn(ledger -> ledger.endDate().toString())
                .setHeader("Enddatum")
                .setAutoWidth(true)
                .setFlexGrow(0);

        addColumn(LedgerDTO::name)
                .setHeader("Name")
                .setAutoWidth(true)
                .setFlexGrow(1);

        addComponentColumn(ledger -> {
            Checkbox closedCheckbox = new Checkbox();
            closedCheckbox.setValue(ledger.closed());
            closedCheckbox.setReadOnly(true);
            return closedCheckbox;
        }).setHeader("Abgeschlossen")
                .setAutoWidth(true)
                .setFlexGrow(0);

        addColumn(LedgerDTO::id)
                .setHeader("ID")
                .setAutoWidth(true)
                .setFlexGrow(0);

        addComponentColumn(ledger -> {
            Button actionButton = new Button(VaadinIcon.ELLIPSIS_DOTS_V.create());
            actionButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);

            ContextMenu contextMenu = new ContextMenu(actionButton);
            contextMenu.setOpenOnClick(true);
            contextMenu.addItem("Bearbeiten", e -> editAction.accept(ledger.id()));
            contextMenu.addItem("Löschen", e -> deleteAction.accept(ledger.id()));
            return actionButton;
        })
                .setHeader("Aktion")
                .setAutoWidth(true)
                .setFlexGrow(0);

        this.addItemClickListener(e -> editAction.accept(e.getItem().id()));
    }
}
