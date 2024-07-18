package com.penguineering.gartenplus.ui.appframe;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;

public class GartenplusPage extends VerticalLayout implements RouterLayout {
    public GartenplusPage() {
        setId("gartenplus-app");
        setPadding(false);
        setMargin(false);
    }
}
