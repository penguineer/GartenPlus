package com.penguineering.gartenplus.ui.appframe;

import com.penguineering.gartenplus.auth.user.UserDTO;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import java.util.function.Supplier;

public class GartenplusHeader extends Div {
    public GartenplusHeader(Supplier<UserDTO> currentUser) {
        setWidthFull();
        getStyle().set("display", "contents");

        HorizontalLayout headerLayout = new HorizontalLayout();
        headerLayout.setId("gartenplus-header");
        headerLayout.setWidthFull();

        headerLayout.add(new GartenplusLogo());

        headerLayout.add(new H1("GartenPlus"));

        Div spacer = new Div();
        spacer.setWidthFull();
        headerLayout.add(spacer);

        headerLayout.add(new LoggedUserView(currentUser));

        this.add(headerLayout);
    }
}
