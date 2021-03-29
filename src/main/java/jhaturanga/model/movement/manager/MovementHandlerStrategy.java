package jhaturanga.model.movement.manager;

import java.util.Set;

import jhaturanga.model.board.BoardPosition;
import jhaturanga.model.movement.Movement;
import jhaturanga.model.piece.Piece;

public interface MovementHandlerStrategy {
    /**
     * Use this method to check if the passed movement is possible based on both the
     * specific MovementStrategy and GameController.
     * 
     * @param movement - the movement to check if it's possible or not.
     * @return true if the movement is possible, false otherwise.
     */
    boolean isMovementPossible(Movement movement);

    /**
     * Get the passed Piece's possible BoardPositions where to move.
     * 
     * @param piece
     * @return Set<BoardPosition> representing the BoardPositions where the selected
     *         Piece can Move
     */
    Set<BoardPosition> filterOnPossibleMovesBasedOnGameController(Piece piece);
}