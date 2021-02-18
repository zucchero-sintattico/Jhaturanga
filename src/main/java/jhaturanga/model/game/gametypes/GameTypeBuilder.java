package jhaturanga.model.game.gametypes;

import jhaturanga.model.game.GameController;
import jhaturanga.model.movement.MovementManager;

public interface GameTypeBuilder {
    /**
     * Sets the GameTypeName.
     * 
     * @param gameTypeName the name of the GameType being created
     * @return this
     */
    GameTypeBuilderImpl gameTypeName(String gameTypeName);

    /**
     * Sets the GameController.
     * 
     * @param gameController the gameController of the gameType being created
     * @return this
     */
    GameTypeBuilderImpl gameController(GameController gameController);

    /**
     * Sets the MovemementManager.
     * 
     * @param movementManager the MovementManager of the GameType being created
     * @return this
     */
    GameTypeBuilderImpl movementManager(MovementManager movementManager);

    /**
     * Used to create the GameType.
     * 
     * @return GameType is the GameType created from the builder
     */
    GameType build();
}