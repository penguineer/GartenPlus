package com.penguineering.gartenplus.ui.appframe;

import com.penguineering.gartenplus.auth.user.UserDTO;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.IconFactory;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Optional;
import java.util.function.Supplier;

public class LoggedUserView extends Div {
    Logger logger = LoggerFactory.getLogger(LoggedUserView.class);

    final Avatar avatar;
    final MenuBar userMenu;

    public LoggedUserView(Supplier<UserDTO> currentUser) {
        Optional<UserDTO> optUser = Optional.ofNullable(currentUser.get());

        avatar = new Avatar();
        avatar.setId("logged-user-avatar");
        avatar.addThemeVariants(AvatarVariant.LUMO_LARGE);
        avatar.setTooltipEnabled(true);

        // Set the user's name
        optUser.
                map(UserDTO::displayName)
                .ifPresent(avatar::setName);

        // load the avatar image
        optUser
                .map(UserDTO::avatarUrl)
                .map(URI::toASCIIString)
                .ifPresent(avatar::setImage);


        // Set up the menu
        userMenu = new MenuBar();
        userMenu.addThemeVariants(MenuBarVariant.LUMO_ICON);
        userMenu.setHeightFull();

        MenuItem menuItem = userMenu.addItem(avatar);
        SubMenu subMenu = menuItem.getSubMenu();

        subMenu.addItem(createMenuItemWithIcon("Profil", VaadinIcon.USER),
                e -> navigateTo("/admin/profile"));
        subMenu.addSeparator();
        subMenu.addItem(createMenuItemWithIcon("Einstellungen", VaadinIcon.COG),
                e -> navigateTo("/admin/settings"));
        subMenu.addSeparator();
        subMenu.addItem(createMenuItemWithIcon("Abmelden", VaadinIcon.SIGN_OUT),
                e -> navigateTo("/admin/logout"));

        add(userMenu);

    }

    private Component createMenuItemWithIcon(String text, IconFactory icon) {
        HorizontalLayout menuItem = new HorizontalLayout();

        menuItem.setWidthFull();
        menuItem.setPadding(false);
        menuItem.setMargin(false);
        // menuItem.setSpacing(false);

        var iconComponent = icon.create();
        iconComponent.setSize("var(--lumo-icon-size-s)");
        menuItem.add(iconComponent);
        menuItem.add(text);

        return menuItem;
    }

    private void navigateTo(String path) {
        getUI().ifPresent(ui -> ui.navigate(path));
    }
}
