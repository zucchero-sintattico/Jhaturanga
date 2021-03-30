package jhaturanga.views.online.join;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import jhaturanga.controllers.online.join.OnlineJoinController;
import jhaturanga.views.AbstractJavaFXView;
import jhaturanga.views.View;
import jhaturanga.views.pages.PageLoader;
import jhaturanga.views.pages.Pages;

public final class OnlineJoinView extends AbstractJavaFXView implements View {

    @FXML
    private TextField matchID;

    @Override
    public void init() {
    }

    private void onReady() {
        System.out.println("READYY");
        Platform.runLater(() -> PageLoader.switchPage(this.getStage(), Pages.ONLINE_MATCH,
                this.getController().getApplicationInstance()));
    }

    @FXML
    public void onJoinClick(final ActionEvent event) {
        final String matchID = this.matchID.getText();
        this.getOnlineJoinController().join(matchID, this::onReady);
    }

    @FXML
    public void onBackClick(final ActionEvent event) {
        PageLoader.switchPage(this.getStage(), Pages.ONLINE, this.getController().getApplicationInstance());
    }

    private OnlineJoinController getOnlineJoinController() {
        return (OnlineJoinController) this.getController();
    }
}
