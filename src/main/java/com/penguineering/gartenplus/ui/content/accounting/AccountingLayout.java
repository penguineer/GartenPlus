package com.penguineering.gartenplus.ui.content.accounting;


import com.penguineering.gartenplus.ui.appframe.AppFrameLayout;
import com.penguineering.gartenplus.ui.appframe.TabbedLayoutBase;
import com.penguineering.gartenplus.ui.content.accounting.ledgers.LedgersSettingsPage;
import com.penguineering.gartenplus.ui.content.accounting.myfinances.MyFinancesPage;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RoutePrefix;

import java.util.LinkedHashMap;
import java.util.Map;

@ParentLayout(AppFrameLayout.class)
@RoutePrefix(value = "accounting")
public class AccountingLayout extends TabbedLayoutBase {
    private static final Map<String, Class<? extends Component>> targets = new LinkedHashMap<>();

    static {
        targets.put("Meine Finanzen", MyFinancesPage.class);
        targets.put("Hauptbücher", LedgersSettingsPage.class);
    }

    public AccountingLayout() {
        super(targets);
    }
}
