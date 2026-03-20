package br.com.rss.notificationengine.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@Profile("local") // Só abre na sua máquina, não no servidor!
public class BrowserLauncher {

    @EventListener(ApplicationReadyEvent.class)
    public void openSwaggerAfterStartup() {
        String url = "http://localhost:8080/swagger-ui/index.html";
        System.out.println("Aplicação pronta! Abrindo Swagger em: " + url);

        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                new ProcessBuilder("rundll32", "url.dll,FileProtocolHandler", url).start();
            }
        } catch (IOException | URISyntaxException e) {
            System.err.println("Não foi possível abrir o navegador automaticamente: " + e.getMessage());
        }
    }
}