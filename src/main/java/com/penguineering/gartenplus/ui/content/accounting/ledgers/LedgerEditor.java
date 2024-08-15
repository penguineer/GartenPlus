package com.penguineering.gartenplus.ui.content.accounting.ledgers;

import com.penguineering.gartenplus.accounting.model.ledger.LedgerDTO;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

public class LedgerEditor extends VerticalLayout {
    private final Consumer<LedgerDTO> saveAction;
    private final Runnable cancelAction;

    private final Binder<LedgerBean> binder = new Binder<>(LedgerBean.class);

    private final TextField fiscalYear;
    private final DatePicker startDate;
    private final DatePicker endDate;

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
        fieldsLayout.add(id);
        id.setWidthFull();
        id.setReadOnly(true);

        // Date selection
        var datesLayout = new HorizontalLayout();
        fieldsLayout.add(datesLayout);
        datesLayout.setWidthFull();
        datesLayout.setPadding(false);
        datesLayout.getStyle()
                .set("gap", "16px");

        startDate = new DatePicker("Erster Geschäftstag");
        datesLayout.add(startDate);
        startDate.addValueChangeListener(this::fiscalYearDatesToEntry);

        endDate = new DatePicker("Letzter Geschäftstag");
        datesLayout.add(endDate);
        endDate.addValueChangeListener(this::fiscalYearDatesToEntry);

        var spacer = new Div();
        datesLayout.add(spacer);
        datesLayout.setFlexGrow(1, spacer);

        fiscalYear = new TextField("Geschäftsjahr setzen");
        datesLayout.add(fiscalYear);
        fiscalYear.addValueChangeListener(this::fiscalYearEntryToDates);


        TextField name = new TextField("Name");
        fieldsLayout.add(name);
        name.setWidthFull();

        Checkbox closed = new Checkbox("Abgeschlossen");
        fieldsLayout.add(closed);


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

    private void fiscalYearEntryToDates(
            AbstractField.ComponentValueChangeEvent<TextField, String> e) {
        if (e.getValue() != null && !e.getValue().isBlank()) {
            try {
                var year = Integer.parseInt(e.getValue());
                startDate.setValue(LocalDate.of(year, 1, 1));
                endDate.setValue(LocalDate.of(year, 12, 31));
            } catch (NumberFormatException ex) {
                fiscalYear.setInvalid(true);
            }
        }
    }

    private void fiscalYearDatesToEntry(
            AbstractField.ComponentValueChangeEvent<DatePicker, LocalDate> e) {
        fiscalYear.clear(); // Clear the fiscalYear text field first

        LocalDate start = startDate.getValue();
        LocalDate end = endDate.getValue();

        if (Objects.isNull(start) || Objects.isNull(end))
            return;

        if (isFullYear(start, end))
            fiscalYear.setValue(String.valueOf(start.getYear()));
    }

    private boolean isFullYear(LocalDate start, LocalDate end) {
        return start.getMonthValue() == 1 && start.getDayOfMonth() == 1 &&
                end.getMonthValue() == 12 && end.getDayOfMonth() == 31
                && start.getYear() == end.getYear();
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
                    id == null || id.isBlank() ? null : UUID.fromString(id),
                    name, startDate, endDate, closed);
        }
    }
}
