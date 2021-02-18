package jhaturanga.text.game.matchturns;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jhaturanga.model.board.BoardPositionImpl;
import jhaturanga.model.game.gametypes.GameTypesEnum;
import jhaturanga.model.match.Match;
import jhaturanga.model.match.builder.MatchBuilder;
import jhaturanga.model.match.builder.MatchBuilderImpl;
import jhaturanga.model.movement.MovementImpl;
import jhaturanga.model.player.Player;
import jhaturanga.model.player.PlayerColor;
import jhaturanga.model.player.PlayerImpl;

class MatchTurnsTest {

    private Player whitePlayer;
    private Player blackPlayer;

    @BeforeEach
    public void init() {
        this.whitePlayer = new PlayerImpl(PlayerColor.WHITE);
        this.blackPlayer = new PlayerImpl(PlayerColor.BLACK);
    }

    @Test
    void testMatchPlayersTurns() {
        final MatchBuilder matchBuilder = new MatchBuilderImpl();

        final Match match = matchBuilder
                .gameType(GameTypesEnum.CLASSIC_GAME.getNewGameType(this.whitePlayer, this.blackPlayer)).build();

        /**
         * So at this point White player should be starting
         */

        assertTrue(match.move(new MovementImpl(match.getBoard().getPieceAtPosition(new BoardPositionImpl(0, 1)).get(),
                new BoardPositionImpl(0, 3))));
        /**
         * I try moving a piece of the same player (in this case White Player's), it
         * should return false and not let me do the move
         */

        assertFalse(match.move(new MovementImpl(match.getBoard().getPieceAtPosition(new BoardPositionImpl(1, 1)).get(),
                new BoardPositionImpl(1, 3))));

    }
}