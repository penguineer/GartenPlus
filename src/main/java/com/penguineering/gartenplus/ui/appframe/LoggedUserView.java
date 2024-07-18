package com.penguineering.gartenplus.ui.appframe;

import com.penguineering.gartenplus.auth.user.UserDTO;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Optional;
import java.util.function.Supplier;

public class LoggedUserView extends Div {
    Logger logger = LoggerFactory.getLogger(LoggedUserView.class);

    final Avatar avatar;

    public LoggedUserView(Supplier<UserDTO> currentUser) {
        Optional<UserDTO> optUser = Optional.ofNullable(currentUser.get());

        HorizontalLayout avatarLayout = new HorizontalLayout();
        avatarLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        avatar = new Avatar();
        avatar.setId("logged-user-avatar");
        avatar.addThemeVariants(AvatarVariant.LUMO_LARGE);
        avatar.setTooltipEnabled(true);

        avatarLayout.add(avatar);
        add(avatarLayout);

        // Set the user's name
        optUser.
                map(UserDTO::displayName)
                .ifPresent(avatar::setName);

        // load the avatar image
        optUser
                .map(UserDTO::avatarUrl)
                .map(URI::toASCIIString)
                .ifPresent(avatar::setImage);
    }
}
