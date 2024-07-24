package com.penguineering.gartenplus.ui.content.admin.settings.ledgers;

import com.penguineering.gartenplus.accounting.model.ledger.LedgerDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class LedgerEditor extends VerticalLayout {
    private final Consumer<LedgerDTO> saveAction;
    private final Runnable cancelAction;

    private final Binder<LedgerBean> binder = new Binder<>(LedgerBean.class);

    public LedgerEditor(Consumer<LedgerDTO> saveAction, Runnable cancelAction) {
        this.saveAction = saveAction;
        this.cancelAction = cancelAction;

        setWidthFull();
        setPadding(true);
        setSpacing(true);

        // Fields Layout
        VerticalLayout fieldsLayout = new VerticalLayout();
        fieldsLayout.setWidthFull();
        fieldsLayout.setPadding(true);
        fieldsLayout.setSpacing(true);

        TextField id = new TextField("ID");
        id.setWidthFull();
        id.setReadOnly(true);

        DatePicker startDate = new DatePicker("Erster Geschäftstag");
        startDate.setWidthFull();

        DatePicker endDate = new DatePicker("Letzter Geschäftstag");
        endDate.setWidthFull();

        TextField name = new TextField("Name");
        name.setWidthFull();

        Checkbox closed = new Checkbox("Abgeschlossen");

        fieldsLayout.add(id, name, startDate, endDate, closed);

        // Buttons Layout
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setWidthFull();
        buttonsLayout.setJustifyContentMode(JustifyContentMode.END);
        buttonsLayout.setSpacing(true);

        Button cancel = new Button("Abbrechen", e -> cancel());
        Button save = new Button("Speichern", e -> save());
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


        buttonsLayout.add(cancel, save);

        // Adding layouts to the main layout
        add(fieldsLayout, buttonsLayout);

        binder.forField(id).bind(LedgerBean::getId, LedgerBean::setId);
        binder.bind(name, LedgerBean::getName, LedgerBean::setName);
        binder.bind(startDate, LedgerBean::getStartDate, LedgerBean::setStartDate);
        binder.bind(endDate, LedgerBean::getEndDate, LedgerBean::setEndDate);
        binder.forField(closed).bind(LedgerBean::isClosed, LedgerBean::setClosed);
    }

    public void setLedger(LedgerDTO ledger) {
        LedgerBean bean = Optional.ofNullable(ledger)
                .map(l -> new LedgerBean(
                        l.id() == null ? null : l.id().toString(),
                        l.name(), l.startDate(), l.endDate(), l.closed()))
                .orElseGet(LedgerBean::new);

        binder.readBean(bean);
    }

    private void save() {
        Optional.of(new LedgerBean())
                .filter(binder::writeBeanIfValid)
                .map(LedgerBean::toDTO)
                .ifPresent(saveAction);
        setLedger(null);
    }

    private void cancel() {
        binder.readBean(null);
        cancelAction.run();
    }

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LedgerBean {
        private String id;
        private String name;
        private LocalDate startDate;
        private LocalDate endDate;
        private boolean closed;

        public LedgerDTO toDTO() {
            return new LedgerDTO(
                    id == null ? null : UUID.fromString(id),
                    name, startDate, endDate, closed);
        }
    }
}
