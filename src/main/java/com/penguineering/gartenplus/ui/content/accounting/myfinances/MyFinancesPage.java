package com.penguineering.gartenplus.ui.content.accounting.myfinances;

import com.penguineering.gartenplus.ui.appframe.GartenplusPage;
import com.penguineering.gartenplus.ui.content.accounting.AccountingLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

@Route(value = "myfinances", layout = AccountingLayout.class)
@PermitAll
@PageTitle("GartenPlus | Buchhaltung | Meine Finanzen")
public class MyFinancesPage extends GartenplusPage {
    public MyFinancesPage() {
        add(new Paragraph("Diese Seite wächst noch."));
    }
}
