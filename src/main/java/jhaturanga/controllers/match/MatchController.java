package jhaturanga.controllers.match;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import jhaturanga.controllers.Controller;
import jhaturanga.model.board.Board;
import jhaturanga.model.board.BoardPosition;

/**
 * The controller for the game page.
 */
public interface MatchController extends Controller {

    /**
     * Move a piece.
     * 
     * @param origin      - the origin's position
     * @param destination - the destination's position
     * @return ActionType representing the actionType resulting from the action just
     *         performed
     */
    MovementResult move(BoardPosition origin, BoardPosition destination);

    /**
     * Get the actual board status.
     * 
     * @return the status of the most recent board
     */
    Board getBoardStatus();

    /**
     * Get the board state at the previous movement.
     * 
     * @return the board state
     */
    Optional<Board> getPrevBoard();

    /**
     * Get the board state at the next movement.
     * 
     * @return the board state
     */
    Optional<Board> getNextBoard();

    /**
     * Check if the current game is not sync with the last move, in this case we are
     * navigation through the movement and we don't have to make any movement from
     * the GUI.
     * 
     * @return true if we are in navigation mode, false otherwise
     */
    boolean isInNavigationMode();

    /**
     * white remain time in minutes.
     * 
     * @return white remain time in minutes
     */
    String getWhiteReminingTime();

    /**
     * white remain time in minutes.
     * 
     * @return white remain time in minutes
     */
    String getBlackReminingTime();

    /**
     * start match.
     */
    void start();

    /**
     * get the status of the match.
     * 
     * @return true if the match is over
     */
    boolean isOver();

    /**
     * save the match in a file.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     */
    void saveMatch() throws IOException;
}
