package com.penguineering.gartenplus.ui.appframe;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;

import java.util.Objects;

/**
 * A page that forwards to another page.
 */
@Tag("div")
public abstract class ForwardingPage extends Component implements BeforeEnterObserver {
    private final Class<? extends Component> target;

    public ForwardingPage(Class<? extends Component> target) {
        Objects.requireNonNull(target, "target must not be null");
        this.target = target;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        event.forwardTo(this.target);
    }
}
