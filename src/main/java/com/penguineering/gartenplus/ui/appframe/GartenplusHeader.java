package com.penguineering.gartenplus.ui.appframe;

import com.penguineering.gartenplus.ApplicationContextProvider;
import com.penguineering.gartenplus.auth.user.UserDTO;
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

        HorizontalLayout headerLayout = new HorizontalLayout();
        headerLayout.setId("gartenplus-header");
        headerLayout.setWidthFull();
        headerLayout.getStyle()
                .set("gap", "32px")
                .set("padding", "16px 16px 8px 16px")
                .set("background-color", "var(--lumo-header-color)")
                .set("border-bottom", "1px solid var(--lumo-contrast-20pct)");

        headerLayout.add(new GartenplusLogo());

        HorizontalLayout headlineLayout = new HorizontalLayout();
        headlineLayout.setPadding(false);
        headlineLayout.setMargin(false);
        headlineLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
        headlineLayout.getStyle()
                .set("gap", "16px");
        headerLayout.add(headlineLayout);

        headlineLayout.add(new H1("GartenPlus"));

        var versionSpan =
                Optional.ofNullable(findVersion())
                        .or(() -> Optional.of("local"))
                        .map(Span::new).orElseThrow();
        versionSpan.getStyle()
                .set("font-size", "var(--lumo-font-size-xs)");
        headlineLayout.add(versionSpan);

        Div spacer = new Div();
        spacer.setWidthFull();
        headerLayout.add(spacer);
        headerLayout.setFlexGrow(1, spacer);

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

        headerLayout.add(legalLayout);

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
