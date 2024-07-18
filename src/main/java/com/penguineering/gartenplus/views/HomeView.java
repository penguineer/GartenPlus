package com.penguineering.gartenplus.views;

import com.penguineering.gartenplus.ui.appframe.AppFrameLayout;
import com.penguineering.gartenplus.ui.appframe.GartenplusPage;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import jakarta.annotation.security.PermitAll;

@Route(value = "", layout = AppFrameLayout.class)
@PermitAll
public class HomeView extends GartenplusPage {

    public HomeView() {
        add(new Paragraph("Der Garten wächst noch …"));
    }
}
