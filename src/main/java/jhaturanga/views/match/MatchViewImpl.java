package jhaturanga.views.match;

import java.io.IOException;

import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import jhaturanga.controllers.game.MatchController;
import jhaturanga.model.timer.ObservableTimer;
import jhaturanga.views.AbstractView;

public final class MatchViewImpl extends AbstractView implements MatchView {

    private static final int MINIMUM_SCALE = 100;

    @FXML
    private AnchorPane root;

    @FXML
    private BorderPane grid;

    @FXML
    private Text timerP1;

    @FXML
    private Text timerP2;

    @FXML
    public void initialize() {

    }

    private void onTimeChange() {
        timerP1.setText(this.getGameController().getWhiteReminingTime());
        timerP2.setText(this.getGameController().getBlackReminingTime());
    }

    private void onTimeFinish() {

    }

    @Override
    public void init() {

        this.getGameController().start();

        this.getStage().setMinWidth(MINIMUM_SCALE * this.getGameController().getBoardStatus().getColumns());
        this.getStage().setMinHeight(MINIMUM_SCALE * this.getGameController().getBoardStatus().getRows());

        final Node board = new BoardView(this.getGameController());

        this.grid.prefWidthProperty().bind(Bindings.min(root.widthProperty(), root.heightProperty()));
        this.grid.prefHeightProperty().bind(Bindings.min(root.widthProperty(), root.heightProperty()));

        this.grid.setCenter(board);

        final ObservableTimer timer = new ObservableTimer(this.getController().getModel().getTimer().get(),
                this::onTimeFinish, this::onTimeChange);
        timer.start();

    }

    @Override
    public MatchController getGameController() {
        return (MatchController) this.getController();
    }

    @FXML
    public void saveMatch(final Event event) throws IOException {
        this.getGameController().saveMatch();
    }

}