package com.penguineering.gartenplus.ui.content.admin.settings.users;

import com.penguineering.gartenplus.auth.user.UserDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;

import java.util.UUID;
import java.util.function.Consumer;

public class UsersGrid extends Grid<UserDTO> {

    public UsersGrid(Consumer<UUID> editAction) {
        setWidthFull();
        setAllRowsVisible(true);

        addColumn(UserDTO::id)
                .setHeader("ID")
                .setAutoWidth(true)
                .setFlexGrow(0);

        addColumn(UserDTO::displayName)
                .setHeader("Anzeigename")
                .setAutoWidth(true)
                .setFlexGrow(0);

        addColumn(UserDTO::email)
                .setHeader("E-Mail")
                .setAutoWidth(true)
                .setFlexGrow(0);

        addColumn(UserDTO::avatarUrl)
                .setHeader("Avatar-URL")
                .setAutoWidth(true)
                .setFlexGrow(1);

        addComponentColumn(user -> {
            Button actionButton = new Button(VaadinIcon.ELLIPSIS_DOTS_V.create());
            actionButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);

            ContextMenu contextMenu = new ContextMenu(actionButton);
            contextMenu.setOpenOnClick(true);
            contextMenu.addItem("Bearbeiten", e -> editAction.accept(user.id()));
            return actionButton;
        })
                .setHeader("Aktionen")
                .setAutoWidth(true)
                .setFlexGrow(0);

        this.addItemClickListener(e -> editAction.accept(e.getItem().id()));
    }
}
