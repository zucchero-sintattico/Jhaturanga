package jhaturanga.model.game.gametypes;

import jhaturanga.model.board.Board;
import jhaturanga.model.game.GameController;
import jhaturanga.model.movement.MovementManager;

public class GameTypeImpl implements GameType {

    private final String gameTypeName;
    private final GameController gameController;
    private final MovementManager movementManager;

    public GameTypeImpl(final String gameTypeName, final GameController gameController,
            final MovementManager movementManager) {
        this.gameTypeName = gameTypeName;
        this.gameController = gameController;
        this.movementManager = movementManager;
    }

    @Override
    public final String getGameName() {
        return this.gameTypeName;
    }

    @Override
    public final GameController getGameController() {
        return this.gameController;
    }

    @Override
    public final Board getStartingBoard() {
        return this.gameController.boardState();
    }

    @Override
    public final MovementManager getMovementManager() {
        return this.movementManager;
    }

}