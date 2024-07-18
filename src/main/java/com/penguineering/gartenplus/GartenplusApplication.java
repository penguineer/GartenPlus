package com.penguineering.gartenplus;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.AppShellSettings;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaRepositories
@Push
@EnableAsync
@Theme(value = "gartenplus", variant = Lumo.DARK)
public class GartenplusApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(GartenplusApplication.class, args);
    }

    @Override
    public void configurePage(AppShellSettings settings) {
        settings.addFavIcon("icon", "favicon.ico", "256x256");
        settings.addLink("shortcut icon", "favicon.ico");
    }
}
