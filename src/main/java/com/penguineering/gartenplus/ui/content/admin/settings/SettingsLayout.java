package com.penguineering.gartenplus.ui.content.admin.settings;

import com.penguineering.gartenplus.ui.appframe.TabbedLayoutBase;
import com.penguineering.gartenplus.ui.content.accounting.ledgers.LedgersSettingsPage;
import com.penguineering.gartenplus.ui.content.admin.AdminLayout;
import com.penguineering.gartenplus.ui.content.admin.settings.groups.GroupSettingsPage;
import com.penguineering.gartenplus.ui.content.admin.settings.users.UsersSettingsPage;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RoutePrefix;

import java.util.LinkedHashMap;
import java.util.Map;

@ParentLayout(AdminLayout.class)
@RoutePrefix(value = "settings")
public class SettingsLayout extends TabbedLayoutBase {
    private static final Map<String, Class<? extends Component>> targets = new LinkedHashMap<>();

    static {
        targets.put("Benutzer", UsersSettingsPage.class);
        targets.put("Gruppen", GroupSettingsPage.class);
    }

    public SettingsLayout() {
        super(targets);
    }
}
