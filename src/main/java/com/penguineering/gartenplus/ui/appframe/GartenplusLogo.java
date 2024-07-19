package com.penguineering.gartenplus.ui.appframe;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;

public class GartenplusLogo extends Div {
    private static final String PATH = "assets/GartenPlusLogoRounded.png";
    private static final String ALT = "GartenPlus Logo";
    private static final int DIMENSIONS = 48;

    public GartenplusLogo() {
        getStyle().set("display", "contents");

        Image logo = new Image(PATH, ALT);
        logo.setWidth(DIMENSIONS, Unit.PIXELS);
        logo.setHeight(DIMENSIONS, Unit.PIXELS);
        Anchor link = new Anchor("", logo);

        add(link);
    }
}
