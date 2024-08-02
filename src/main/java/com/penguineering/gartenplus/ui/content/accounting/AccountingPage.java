package com.penguineering.gartenplus.ui.content.accounting;

import com.penguineering.gartenplus.ui.appframe.GartenplusPage;
import com.penguineering.gartenplus.ui.content.accounting.myfinances.MyFinancesPage;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "", layout = AccountingLayout.class)
@PermitAll
@PageTitle("GartenPlus | Buchhaltung")
public class AccountingPage extends GartenplusPage implements BeforeEnterObserver {
    @Override
    public void beforeEnter(BeforeEnterEvent event) {

        event.getUI().navigate(MyFinancesPage.class);
    }
}
