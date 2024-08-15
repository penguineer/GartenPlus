package com.penguineering.gartenplus.ui.appframe;

import com.penguineering.gartenplus.auth.user.UserDTO;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.function.Supplier;

public class AppFrameLayout extends VerticalLayout implements RouterLayout {
    private final Div content;

    public AppFrameLayout(@Qualifier("user") Supplier<UserDTO> currentUser) {
        setId("gartenplus-app");
        setPadding(false);
        setMargin(false);
        setSpacing(false);
        setSizeFull();

        if (!DeviceUtil.isMobile())
            getStyle()
                    .set("display", "flex")
                    .set("flex-direction", "column");

        GartenplusHeader header = new GartenplusHeader(currentUser);
        add(header);

        content = new Div();
        content.setId("gartenplus-content");
        content.setSizeFull();
        if (!DeviceUtil.isMobile())
            content.getStyle()
                    .set("flex-grow", "1")
                    .set("overflow", "auto")
                    .set("overflow-y", "scroll"); /* Always show vertical scrollbar */

        add(content);
    }

    private HorizontalLayout createHeader(Supplier<UserDTO> currentUser) {
        HorizontalLayout header = new HorizontalLayout();
        header.setId("gartenplus-header");
        header.setSizeFull();

        header.add(new LoggedUserView(currentUser));

        return header;
    }

    @Override
    public void showRouterLayoutContent(HasElement newContent) {
        // Previous content is automatically removed

        newContent.getElement()
                .getComponent()
                .ifPresent(content::add);
    }

}
