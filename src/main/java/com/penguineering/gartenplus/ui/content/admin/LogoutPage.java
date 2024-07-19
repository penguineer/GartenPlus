package com.penguineering.gartenplus.ui.content.admin;

import com.penguineering.gartenplus.ui.appframe.GartenplusPage;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import jakarta.annotation.security.PermitAll;

import java.util.Arrays;

@Route(value = "logout", layout = AdminLayout.class)
@PermitAll
@PageTitle("GartenPlus | Abmelden")
public class LogoutPage extends GartenplusPage {
    private static final String[] descriptionText = {
            "Auf dieser Seite kann man sich abmelden.",
            "Hinweis: Durch SSO kommt es sofort zur erneuten Anmeldung.",
            "Die Abmeldung setzt die Session zurück und lädt Daten vom OIDC-Provider neu."
    };

    public LogoutPage() {
        VerticalLayout description = new VerticalLayout();
        description.setMargin(false);
        description.setPadding(false);
        description.setSpacing(false);
        description.getStyle()
                .set("margin", "0px auto");

        Arrays.stream(descriptionText).map(Paragraph::new).forEach(description::add);
        add(description);

        var button = new Button("Abmelden", VaadinIcon.SIGN_OUT.create(),
                e -> logout());

        add(button);
    }

    private void logout() {
        VaadinSession.getCurrent().close();

        getUI()
                .map(UI::getPage)
                .ifPresent(page -> page.setLocation("/login"));
    }

}
