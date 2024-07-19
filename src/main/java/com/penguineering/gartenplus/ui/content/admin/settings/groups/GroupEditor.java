package com.penguineering.gartenplus.ui.content.admin.settings.groups;

import com.penguineering.gartenplus.auth.group.GroupDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class GroupEditor extends VerticalLayout {
    private final Consumer<GroupDTO> saveAction;
    private final Runnable cancelAction;
    private final BiFunction<String, UUID, Boolean> nameValidation;

    private final Binder<GroupBean> binder = new Binder<>(GroupBean.class);
    private GroupBean group;

    public GroupEditor(
            Consumer<GroupDTO> saveAction,
            Runnable cancelAction,
            BiFunction<String, UUID, Boolean> nameValidation
    ) {
        this.saveAction = saveAction;
        this.cancelAction = cancelAction;
        this.nameValidation = Objects.requireNonNullElse(nameValidation, (n, u) -> true);

        setWidthFull();

        VerticalLayout fieldsLayout = new VerticalLayout();
        fieldsLayout.setWidthFull();
        fieldsLayout.setPadding(false);
        fieldsLayout.setMargin(false);

        TextField id = new TextField("ID");
        id.setWidthFull();
        id.setReadOnly(true);

        TextField name = new TextField("Name");
        name.addValueChangeListener(e -> binder.validate());
        name.setWidthFull();

        TextField description = new TextField("Beschreibung");
        description.setWidthFull();

        fieldsLayout.add(id, name, description);


        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setWidthFull();
        buttonsLayout.setPadding(false);
        buttonsLayout.setMargin(false);
        buttonsLayout.setJustifyContentMode(JustifyContentMode.END);
        Button save = new Button("Save");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button cancel = new Button("Cancel");
        buttonsLayout.add(cancel, save);

        add(fieldsLayout, buttonsLayout);

        binder.forField(id).bind(GroupBean::getId, null);
        binder.forField(name)
                .withValidator(this::validateName)
                .asRequired()
                .bind(GroupBean::getName, GroupBean::setName);
        binder.forField(description).bind(GroupBean::getDescription, GroupBean::setDescription);

        save.addClickListener(e -> save());
        cancel.addClickListener(e -> cancel());

        setGroup(null);
    }

    public void setGroup(GroupDTO groupDTO) {
        this.group = Objects.isNull(groupDTO)
                ? new GroupBean(null, "", "")
                : new GroupBean(groupDTO.id().toString(), groupDTO.name(), groupDTO.description());

        binder.readBean(group);
    }

    private void save() {
        try {
            binder.writeBean(group);
            if (Objects.nonNull(this.saveAction)) {
                final GroupDTO newGroup = new GroupDTO(
                        group.getId() == null ? null : UUID.fromString(group.getId()),
                        group.getName(), group.getDescription());
                this.saveAction.accept(newGroup);
            }
        } catch (ValidationException e) {
            // Handle validation errors
        }
    }

    private void cancel() {
        binder.readBean(group);
        if (Objects.nonNull(this.cancelAction))
            this.cancelAction.run();
    }

    private ValidationResult validateName(String name, ValueContext context) {
        if (name.isBlank())
            return ValidationResult.error("Name darf nicht leer sein.");

        return this.nameValidation.apply(name, group.getId() == null ? null : UUID.fromString(group.getId()))
                ? ValidationResult.ok()
                : ValidationResult.error("Name ist doppelt oder ungültig.");
    }


    @Setter
    @Getter
    private static class GroupBean {
        private String id;
        private String name;
        private String description;

        public GroupBean(String id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }
    }
}