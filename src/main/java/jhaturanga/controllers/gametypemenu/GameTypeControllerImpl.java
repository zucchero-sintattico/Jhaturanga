package jhaturanga.controllers.gametypemenu;

import java.util.Optional;

import jhaturanga.controllers.AbstractController;
import jhaturanga.model.game.gametypes.GameTypesEnum;
import jhaturanga.model.player.Player;

public final class GameTypeControllerImpl extends AbstractController implements GameTypeMenuController {

    @Override
    public int getNumberOfRow() {
        return getNumberOfGameTypes() / 2;
    }

    @Override
    public int getNumberOfColumn() {
        return getNumberOfGameTypes() / 2;
    }

    @Override
    public int getNumberOfGameTypes() {
        return GameTypesEnum.values().length;
    }

    @Override
    public void setGameType(final GameTypesEnum gameType) {
        this.getModel().setGameType(gameType);
    }

    @Override
    public Optional<Player> getWhitePlayer() {
        return this.getModel().getWhitePlayer();
    }

    @Override
    public Optional<Player> getBlackPlayer() {
        return this.getModel().getBlackPlayer();
    }

}