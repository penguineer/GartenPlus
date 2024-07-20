package com.penguineering.gartenplus.ui.content.admin.settings;

import com.penguineering.gartenplus.ui.appframe.GartenplusPage;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "", layout = SettingsLayout.class)
@RolesAllowed("ADMINISTRATOR")
@PageTitle("GartenPlus | Einstellungen")
public class SettingsPage extends GartenplusPage implements BeforeEnterObserver {
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        event.getUI().navigate(event.getLocation().getPath() + "/users");
    }
}
