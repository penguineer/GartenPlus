package com.penguineering.gartenplus.ui.content.admin.settings;

import com.penguineering.gartenplus.ui.appframe.ForwardingPage;
import com.penguineering.gartenplus.ui.content.admin.settings.users.UsersSettingsPage;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "", layout = SettingsLayout.class)
@RolesAllowed("ADMINISTRATOR")
@PageTitle("GartenPlus | Einstellungen")
public class SettingsPage extends ForwardingPage {
    public SettingsPage() {
        super(UsersSettingsPage.class);
    }
}
