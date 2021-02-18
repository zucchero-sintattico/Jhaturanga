package jhaturanga.pages;

import java.io.IOException;
import java.util.function.Supplier;

import jhaturanga.controllers.Controller;
import jhaturanga.controllers.game.GameControllerImpl;
import jhaturanga.controllers.home.HomeControllerImpl;
import jhaturanga.controllers.login.LoginControllerImpl;
import jhaturanga.controllers.splash.SplashControllerImpl;
import jhaturanga.controllers.settings.SettingsControllerImpl;

public enum Pages {

    /**
     * Splash page.
     */
    SPLASH("splash", () -> new SplashControllerImpl()),

    /**
     * Login page.
     */
    LOGIN("login", () -> {
        try {
            return new LoginControllerImpl();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }),

    /**
     * Register page.
     */
    REGISTER("register", () -> null),

    /**
     * Home page.
     */
    HOME("home", () -> new HomeControllerImpl()),

    /**
     * Game page.
     */
    GAME("game", () -> new GameControllerImpl()),

    /**
     * Settings page.
     */
    SETTINGS("settings", () -> new SettingsControllerImpl());

    private final String name;
    private final Supplier<Controller> controllerGenerator;

    Pages(final String name, final Supplier<Controller> controllerGenerator) {
        this.name = name;
        this.controllerGenerator = controllerGenerator;
    }

    public String getName() {
        return this.name;
    }

    public Controller getNewControllerInstance() {
        return this.controllerGenerator.get();
    }

}