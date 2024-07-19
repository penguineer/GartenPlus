package com.penguineering.gartenplus.ui.appframe;

import com.penguineering.gartenplus.ApplicationContextProvider;
import com.penguineering.gartenplus.auth.user.UserDTO;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;
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

        headerLayout.add(new GartenplusLogo());

        HorizontalLayout headlineLayout = new HorizontalLayout();
        headlineLayout.setPadding(false);
        headlineLayout.setMargin(false);
        headlineLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
        headerLayout.add(headlineLayout);

        headlineLayout.add(new H1("GartenPlus"));

        Optional.ofNullable(findVersion())
                .or(() -> Optional.of("local"))
                .map(Span::new)
                .ifPresent(headlineLayout::add);


        Div spacer = new Div();
        spacer.setWidthFull();
        headerLayout.add(spacer);
        headerLayout.setFlexGrow(1, spacer);

        headerLayout.add(new LoggedUserView(currentUser));

        this.add(headerLayout);
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
