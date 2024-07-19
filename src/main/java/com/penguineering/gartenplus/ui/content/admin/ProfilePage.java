package com.penguineering.gartenplus.ui.content.admin;

import com.penguineering.gartenplus.auth.user.UserDTO;
import com.penguineering.gartenplus.ui.appframe.GartenplusPage;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Route(value = "profile", layout = AdminLayout.class)
@PermitAll
@PageTitle("GartenPlus | Benutzerprofil")
public class ProfilePage extends GartenplusPage {
    public ProfilePage(@Qualifier("user") Supplier<UserDTO> currentUser) {
        Optional<UserDTO> userOpt = Optional.of(currentUser).map(Supplier::get);

        add(new H2("Benutzerprofil"));
        add(new Paragraph("Folgende Daten wurden vom OIDC-Provider übernommen:"));

        Grid<Map.Entry<String, String>> grid = new Grid<>();
        grid.setAllRowsVisible(true);

        grid.addColumn(Map.Entry::getKey).setHeader("Bezeichnung").setAutoWidth(true).setFlexGrow(0);
        grid.addColumn(Map.Entry::getValue).setHeader("Wert").setAutoWidth(true).setFlexGrow(1);

        List<Map.Entry<String, String>> items = new ArrayList<>();
        userOpt.ifPresent(userDTO -> {
            items.add(Map.entry("Name", userDTO.displayName()));
            items.add(Map.entry("E-Mail-Adresse", userDTO.email()));
            items.add(Map.entry("Avatar-URL", userDTO.avatarUrl().toASCIIString()));
        });

        grid.setItems(items);

        add(grid);

        add(new Paragraph("Der Avatar wird oben rechts angezeigt."));

        add(new Paragraph("Die Daten können beim OIDC-Provider geändert werden."));
    }
}