package com.penguineering.gartenplus.ui.appframe;

import com.penguineering.gartenplus.ApplicationContextProvider;
import com.penguineering.gartenplus.auth.user.UserDTO;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class GartenplusHeader extends Div {
    public GartenplusHeader(Supplier<UserDTO> currentUser) {
        setWidthFull();
        getStyle().set("display", "contents");

        if (DeviceUtil.isMobile()) {
            initMobileHeader(currentUser);
        } else {
            initBrowserHeader(currentUser);
        }
    }

    private Component createHeadline() {
        HorizontalLayout headlineLayout = new HorizontalLayout();
        headlineLayout.setPadding(false);
        headlineLayout.setMargin(false);
        headlineLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
        headlineLayout.getStyle()
                .set("gap", "16px");

        headlineLayout.add(new H1("GartenPlus"));

        var versionSpan =
                Optional.ofNullable(findVersion())
                        .or(() -> Optional.of("local"))
                        .map(Span::new).orElseThrow();
        versionSpan.getStyle()
                .set("font-size", "var(--lumo-font-size-xs)");
        headlineLayout.add(versionSpan);

        return headlineLayout;
    }

    private Component createLegalLinks() {
        VerticalLayout legalLayout = new VerticalLayout();
        legalLayout.setWidth("auto");
        legalLayout.setPadding(false);
        legalLayout.setMargin(false);
        legalLayout.setSpacing(false);
        legalLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
        legalLayout.getStyle()
                .set("font-size", "var(--lumo-font-size-xs)")
                .set("margin-right", "16px");

        legalLayout.add(new Anchor("/impressum", "Impressum"));
        legalLayout.add(new Anchor("/datenschutz", "Datenschutzerklärung"));

        return legalLayout;
    }

    private void initBrowserHeader(Supplier<UserDTO> currentUser) {
        HorizontalLayout headerLayout = new HorizontalLayout();
        headerLayout.setId("gartenplus-header");
        headerLayout.setWidthFull();
        headerLayout.getStyle()
                .set("gap", "32px")
                .set("padding", "16px 16px 8px 16px")
                .set("background-color", "var(--lumo-header-color)")
                .set("border-bottom", "1px solid var(--lumo-contrast-20pct)");

        headerLayout.add(new GartenplusLogo());
        headerLayout.add(createHeadline());

        Div spacer = new Div();
        spacer.setWidthFull();
        headerLayout.add(spacer);
        headerLayout.setFlexGrow(1, spacer);

        headerLayout.add(createLegalLinks());

        headerLayout.add(new LoggedUserView(currentUser));

        VerticalLayout fixedHeaderLayout = new VerticalLayout();
        fixedHeaderLayout.setWidthFull();
        fixedHeaderLayout.setPadding(false);
        fixedHeaderLayout.setMargin(false);
        fixedHeaderLayout.setSpacing(false);

        // fix to the top of the screen
        fixedHeaderLayout.getStyle()
                .set("position", "fixed")
                .set("top", "0")
                .set("left", "0")
                .set("right", "0")
                .set("z-index", "1000");

        fixedHeaderLayout.add(headerLayout);

        Div fader = new Div();
        fader.setWidthFull();
        fader.getStyle()
                .set("background", "linear-gradient(180deg, var(--lumo-base-color) 0%, rgba(255, 255, 255, 0) 100%)")
                .set("height", "32px");
        fixedHeaderLayout.add(fader);

        this.add(fixedHeaderLayout);
    }

    private void initMobileHeader(Supplier<UserDTO> currentUser) {
        // Implement a simplified or different layout for mobile devices
        VerticalLayout mobileHeaderLayout = new VerticalLayout();
        mobileHeaderLayout.setWidthFull();
        mobileHeaderLayout.setMargin(false);
        mobileHeaderLayout.getStyle()
                .set("padding", "8px")
                .set("background-color", "var(--lumo-header-color)");

        HorizontalLayout headlineLayout = new HorizontalLayout();
        headlineLayout.setWidthFull();
        headlineLayout.setPadding(false);
        headlineLayout.setMargin(false);
        headlineLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        headlineLayout.add(new GartenplusLogo());
        headlineLayout.add(createHeadline());
        mobileHeaderLayout.add(headlineLayout);

        HorizontalLayout menuLayout = new HorizontalLayout();
        menuLayout.setWidthFull();
        menuLayout.setPadding(false);
        menuLayout.setMargin(false);
        menuLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        menuLayout.add(createLegalLinks());
        menuLayout.add(new LoggedUserView(currentUser));

        mobileHeaderLayout.add(menuLayout);


        this.add(mobileHeaderLayout);
    }

    private static String findVersion() {
        // TODO better decoupling from the application context
        return ApplicationContextProvider.getApplicationContext()
                .map(c -> c.getBeansWithAnnotation(SpringBootApplication.class))
                .map(Map::entrySet)
                .flatMap(entries -> entries.stream().findFirst())
                .map(Map.Entry::getValue)
                .map(Object::getClass)
                .map(Class::getPackage)
                .map(Package::getImplementationVersion)
                .orElse(null);
    }
}
