package com.penguineering.gartenplus.ui.content.admin.settings;

import com.penguineering.gartenplus.auth.SecurityUtils;
import com.penguineering.gartenplus.ui.content.admin.AdminLayout;
import com.penguineering.gartenplus.ui.content.admin.settings.groups.GroupSettingsPage;
import com.penguineering.gartenplus.ui.content.admin.settings.ledgers.LedgersSettingsPage;
import com.penguineering.gartenplus.ui.content.admin.settings.users.UsersSettingsPage;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@ParentLayout(AdminLayout.class)
@RoutePrefix(value = "settings")
public class SettingsLayout extends VerticalLayout implements RouterLayout, BeforeEnterObserver {
    private static final Map<String, Class<? extends Component>> targets = new LinkedHashMap<>();

    static {
        targets.put("Benutzer", UsersSettingsPage.class);
        targets.put("Gruppen", GroupSettingsPage.class);
        targets.put("Hauptbücher", LedgersSettingsPage.class);
    }

    private final Div content;
    private final Tabs menu;

    public SettingsLayout() {
        super();

        Set<String> userRoles = SecurityUtils.getCurrentUserRoles();

        menu = new Tabs();
        targets.entrySet().stream()
                .filter(e -> SecurityUtils.isUserAuthorizedForComponent(e.getValue(), userRoles))
                .map(Map.Entry::getKey)
                .map(Tab::new)
                .forEach(menu::add);
        menu.addSelectedChangeListener(this::navigateToTab);
        add(menu);

        content = new Div();
        content.setSizeFull();
        content.getStyle()
                .set("margin", "0")
                .set("padding", "0");
        add(content);
    }

    private void navigateToTab(Tabs.SelectedChangeEvent event) {
        Optional.of(event.getSelectedTab())
                .map(Tab::getLabel)
                .map(targets::get)
                .ifPresent(target -> UI.getCurrent().navigate(target));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        String currentPath = event.getLocation().getPath();
        targets.forEach((label, target) -> {
            Route route = target.getAnnotation(Route.class);
            if (route != null && currentPath.contains(route.value())) {
                menu.setSelectedTab(menu.getChildren()
                        .filter(component -> component instanceof Tab)
                        .map(component -> (Tab) component)
                        .filter(tab -> tab.getLabel().equals(label))
                        .findFirst()
                        .orElse(null));
            }
        });
    }

    @Override
    public void showRouterLayoutContent(HasElement newContent) {
        // Previous content is automatically removed

        newContent.getElement()
                .getComponent()
                .ifPresent(content::add);
    }
}
