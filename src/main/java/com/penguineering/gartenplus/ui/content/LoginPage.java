package com.penguineering.gartenplus.ui.content;

import com.penguineering.gartenplus.ui.appframe.AppFrameLayout;
import com.penguineering.gartenplus.ui.appframe.GartenplusPage;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Route(value = "login", layout = AppFrameLayout.class)
@PageTitle("GartenPlus | Login")
@AnonymousAllowed
public class LoginPage extends GartenplusPage {
    private record OIDCProvider(
            String handle,
            String name,
            Supplier<Image> logo) {
    }

    private static final List<OIDCProvider> OIDC_PROVIDERS = List.of(
            new OIDCProvider("github", "GitHub", () -> new Image("assets/github-mark-white.svg", "GitHub logo"))
    );

    public LoginPage() {
        setWidthFull();
        setAlignItems(Alignment.CENTER);

        add(new Paragraph("Folgende OIDC-Provider stehen für den Login zur Verfügung:"));

        VerticalLayout buttons = new VerticalLayout();
        buttons.setWidth("unset");
        buttons.getStyle()
                .set("min-width", "50%")
                .set("max-width", "100%");
        OIDC_PROVIDERS.stream()
                .map(this::createButton)
                .forEach(buttons::add);
        add(buttons);
    }

    private Button createButton(OIDCProvider provider) {
        Button loginButton = new Button(provider.name(),
                event -> UI.getCurrent().getPage().setLocation("/oauth2/authorization/" + provider.handle()));

        loginButton.addClassName("login-button");
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Optional.ofNullable(provider.logo)
                .map(Supplier::get)
                .ifPresent(loginButton::setIcon);

        return loginButton;
    }
}
