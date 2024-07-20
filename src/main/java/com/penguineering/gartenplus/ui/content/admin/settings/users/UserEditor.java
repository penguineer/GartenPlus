package com.penguineering.gartenplus.ui.content.admin.settings.users;

import com.penguineering.gartenplus.auth.user.UserDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

public class UserEditor extends VerticalLayout {
    private final Consumer<UserDTO> saveAction;
    private final Runnable cancelAction;

    private final Binder<UserBean> binder = new Binder<>(UserBean.class);
    private UserBean user;

    public UserEditor(
            Consumer<UserDTO> saveAction,
            Runnable cancelAction
    ) {
        this.saveAction = saveAction;
        this.cancelAction = cancelAction;

        setWidthFull();

        VerticalLayout fieldsLayout = new VerticalLayout();
        fieldsLayout.setWidthFull();
        fieldsLayout.setPadding(false);
        fieldsLayout.setMargin(false);

        TextField id = new TextField("ID");
        id.setWidthFull();
        id.setReadOnly(true);

        TextField displayName = new TextField("Anzeigename");
        displayName.addValueChangeListener(e -> binder.validate());
        displayName.setWidthFull();

        TextField email = new TextField("E-Mail");
        email.setWidthFull();

        TextField avatarUrl = new TextField("Avatar-URL");
        avatarUrl.setWidthFull();

        fieldsLayout.add(id, displayName, email, avatarUrl);

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setWidthFull();
        buttonsLayout.setPadding(false);
        buttonsLayout.setMargin(false);
        buttonsLayout.setJustifyContentMode(JustifyContentMode.END);
        Button cancel = new Button(
                Objects.nonNull(saveAction)
                        ? "Abbrechen"
                        : "Schließen");
        cancel.addClickListener(e -> cancel());
        buttonsLayout.add(cancel);
        if (Objects.nonNull(saveAction)) {
            Button save = new Button("Speichern");
            save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            save.addClickListener(e -> save());
            buttonsLayout.add(save);
        } else
            cancel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(fieldsLayout, buttonsLayout);

        binder.forField(id).bind(UserBean::getId, null);
        binder.forField(displayName)
                .bind(UserBean::getDisplayName, null);
        binder.forField(email).bind(UserBean::getEmail, null);
        binder.forField(avatarUrl).bind(UserBean::getAvatarUrl, null);

        setUser(null);
    }

    public void setUser(UserDTO userDTO) {
        this.user = Objects.isNull(userDTO)
                ? new UserBean(null, "", "", "")
                : new UserBean(userDTO.id().toString(), userDTO.displayName(), userDTO.email(), userDTO.avatarUrl().toASCIIString());

        binder.readBean(user);
    }

    private void save() {
        try {
            binder.writeBean(user);
            if (Objects.nonNull(this.saveAction)) {
                final UserDTO newUser = new UserDTO(
                        user.getId() == null ? null : UUID.fromString(user.getId()),
                        user.getDisplayName(), user.getEmail(),
                        user.getAvatarUrl() == null ? null : URI.create(user.getAvatarUrl()));
                this.saveAction.accept(newUser);
            }
        } catch (ValidationException e) {
            // Handle validation errors
        }
    }

    private void cancel() {
        binder.readBean(user);
        if (Objects.nonNull(this.cancelAction))
            this.cancelAction.run();
    }

    @Setter
    @Getter
    private static class UserBean {
        private String id;
        private String displayName;
        private String email;
        private String avatarUrl;

        public UserBean(String id, String displayName, String email, String avatarUrl) {
            this.id = id;
            this.displayName = displayName;
            this.email = email;
            this.avatarUrl = avatarUrl;
        }
    }
}
