package com.penguineering.gartenplus.ui.content.admin.settings.users;

import com.penguineering.gartenplus.auth.user.UserEntity;
import com.penguineering.gartenplus.auth.user.UserEntityService;
import com.penguineering.gartenplus.ui.appframe.GartenplusPage;
import com.penguineering.gartenplus.ui.content.admin.settings.SettingsLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.util.UUID;
import java.util.function.Consumer;

@Route(value = "users", layout = SettingsLayout.class)
@PermitAll
@PageTitle("GartenPlus | Einstellungen | Benutzer")

public class UsersSettingsPage extends GartenplusPage {
    private final UserEntityService userEntityService;

    private final Consumer<Boolean> userEditorVisibility;
    private final UserEditor userEditor;

    private final UsersGrid usersGrid;

    public UsersSettingsPage(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;

        Div userEditorDiv = new Div();
        userEditorDiv.getStyle()
                .set("display", "contents");
        userEditorDiv.setVisible(false);
        this.userEditorVisibility = userEditorDiv::setVisible;
        add(userEditorDiv);

        userEditorDiv.add(new H3("Benutzer bearbeiten"));

        userEditor = new UserEditor(null, this::closeUserEditor);
        userEditorDiv.add(userEditor);


        add(new H3("Benutzer"));

        usersGrid = new UsersGrid(this::editUser);
        add(usersGrid);

        loadData();
    }

    private void loadData() {
        var users = userEntityService.getAllUsers();
        usersGrid.setItems(users);
    }

    private void editUser(UUID userId) {
        var user = userEntityService.getUser(userId)
                .map(UserEntity::toDTO)
                .orElse(null);
        this.userEditor.setUser(user);

        this.userEditorVisibility.accept(true);
    }

    private void closeUserEditor() {
        this.userEditorVisibility.accept(false);

        this.userEditor.setUser(null);
    }


}
