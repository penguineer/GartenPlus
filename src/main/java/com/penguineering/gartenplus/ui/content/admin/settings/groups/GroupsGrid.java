package com.penguineering.gartenplus.ui.content.admin.settings.groups;

import com.penguineering.gartenplus.auth.group.GroupDTO;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;

import java.util.UUID;
import java.util.function.Consumer;

public class GroupsGrid extends Grid<GroupDTO> {

    public GroupsGrid(
            Consumer<UUID> editAction,
            Consumer<UUID> deleteAction) {

        setWidthFull();
        setAllRowsVisible(true);

        addColumn(GroupDTO::id)
                .setHeader("ID")
                .setAutoWidth(true)
                .setFlexGrow(0);

        addColumn(GroupDTO::name)
                .setHeader("Name")
                .setAutoWidth(true)
                .setFlexGrow(0);

        addColumn(GroupDTO::description)
                .setHeader("Beschreibung")
                .setAutoWidth(true)
                .setFlexGrow(1);


        addComponentColumn(group -> {
            Button actionButton = new Button(VaadinIcon.ELLIPSIS_DOTS_V.create());
            actionButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);

            ContextMenu contextMenu = new ContextMenu(actionButton);
            contextMenu.setOpenOnClick(true);
            contextMenu.addItem("Bearbeiten", e -> editAction.accept(group.id()));
            contextMenu.addItem("Löschen", e -> deleteAction.accept(group.id()));
            return actionButton;
        })
                .setHeader("Aktionen")
                .setAutoWidth(true)
                .setFlexGrow(0);
    }
}
