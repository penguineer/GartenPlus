package com.penguineering.gartenplus.ui.appframe;

import com.vaadin.flow.server.VaadinRequest;

public class DeviceUtil {
    public static boolean isMobile() {
        String userAgent = VaadinRequest.getCurrent().getHeader("User-Agent");
        return userAgent != null && userAgent.matches(".*(Mobi|Android|iPhone|iPad).*");
    }
}
