package jhaturanga.views.home;

import jhaturanga.controllers.home.HomeController;
import jhaturanga.views.View;

/**
 * The view of the home page.
 */
public interface HomeView extends View {

    /**
     * Get the home controller instance.
     * 
     * @return the home controller
     */
    HomeController getHomeController();

}