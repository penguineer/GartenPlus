package com.penguineering.gartenplus.ui.content.admin;


import com.penguineering.gartenplus.ui.appframe.AppFrameLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RoutePrefix;
import com.vaadin.flow.router.RouterLayout;
import jakarta.annotation.security.PermitAll;

@ParentLayout(AppFrameLayout.class)
@RoutePrefix(value = "admin")
public class AdminLayout extends Div implements RouterLayout {
}
