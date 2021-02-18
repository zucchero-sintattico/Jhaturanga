package jhaturanga.views.gmaetypemenu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import jhaturanga.controllers.gametypemenu.GameTypeMenuController;
import jhaturanga.model.game.gametypes.GameTypesEnum;
import jhaturanga.views.AbstractView;

public final class GameTypeMenuViewImpl extends AbstractView implements GameTypeMenuView {

    @FXML
    private AnchorPane gameGrid;

    private final GridPane grid = new GridPane();

    @FXML
    void inizialize() {

    }

    @Override
    public void init() {
        gameGrid.getChildren().add(grid);

        int enumCounter = 0;
        for (int y = 0; y < this.getGameTypeMenuController().getNumberOfColumn(); y++) {
            for (int x = 0; x < this.getGameTypeMenuController().getNumberOfRow(); x++) {

                final Button button = new Button();

                button.setText(GameTypesEnum.values()[enumCounter].toString());
                enumCounter++;

                grid.add(button, x, y, 1, 1);

            }

        }
        if (enumCounter < this.getGameTypeMenuController().getnNumbersOfGameTipes()) {
            final Button button = new Button();
            button.setText(GameTypesEnum.values()[enumCounter].toString());
            grid.add(button, this.getGameTypeMenuController().getNumberOfColumn() + 1, 0);
        }

        grid.setGridLinesVisible(true);
        grid.prefWidthProperty().bind(gameGrid.widthProperty());
        grid.prefHeightProperty().bind(gameGrid.heightProperty());

    }

    @Override
    public GameTypeMenuController getGameTypeMenuController() {

        return (GameTypeMenuController) this.getController();
    }

}
