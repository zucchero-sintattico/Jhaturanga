package jhaturanga.model.match;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import jhaturanga.commons.Pair;
import jhaturanga.model.board.Board;
import jhaturanga.model.board.BoardPosition;
import jhaturanga.model.game.GameController;
import jhaturanga.model.game.MatchStatusEnum;
import jhaturanga.model.game.gametypes.GameType;
import jhaturanga.model.history.History;
import jhaturanga.model.history.HistoryImpl;
import jhaturanga.model.idgenerator.MatchIdGenerator;
import jhaturanga.model.movement.Movement;
import jhaturanga.model.movement.MovementImpl;
import jhaturanga.model.movement.MovementManager;
import jhaturanga.model.movement.MovementResult;
import jhaturanga.model.piece.Piece;
import jhaturanga.model.player.Player;
import jhaturanga.model.player.PlayerColor;
import jhaturanga.model.timer.Timer;

public class MatchImpl implements Match {

    private final String matchID;
    private final GameType gameType;
    private final Timer timer;
    private final Collection<Player> players;
    private final History history;

    public MatchImpl(final GameType gameType, final Timer timer) {
        this.matchID = MatchIdGenerator.getNewMatchId();
        this.gameType = gameType;
        this.timer = timer;
        this.players = gameType.getGameController().getPlayers();
        this.history = new HistoryImpl(this.getBoard());
    }

    @Override
    public final String getMatchID() {
        return this.matchID;
    }

    @Override
    public final void start() {
        Optional.ofNullable(this.timer).ifPresent(e -> e.start(this.getGameController().getPlayers().stream()
                .filter(plr -> plr.getColor().equals(PlayerColor.WHITE)).findFirst().get()));
    }

    @Override
    public final MovementResult move(final Movement movement) {
        final MovementResult result = this.gameType.getMovementManager().move(movement);
        if (!result.equals(MovementResult.INVALID_MOVE)) {
            this.history.addMoveToHistory(
                    new MovementImpl(movement.getPieceInvolved(), movement.getOrigin(), movement.getDestination()));
            this.updateTimerStatus(movement.getPieceInvolved().getPlayer());
        }
        return result;
    }

    private void updateTimerStatus(final Player playerForOptionalTimeGain) {
        Optional.ofNullable(this.timer).ifPresent(e -> e.getIncrement().ifPresent(x -> {
            e.addTimeToPlayer(playerForOptionalTimeGain, x);
        }));

        if (!this.matchStatus().equals(MatchStatusEnum.ACTIVE)) {
            Optional.ofNullable(this.timer).ifPresent(t -> t.stop());
        }
    }

    @Override
    public final MatchStatusEnum matchStatus() {
        if (Optional.ofNullable(this.timer).isPresent() && this.timer.getPlayerWithoutTime().isPresent()) {
            return MatchStatusEnum.ENDED_FOR_TIME;
        }
        return this.gameType.getGameController().checkGameStatus(this.getMovementManager().getPlayerTurn());
    }

    @Override
    public final Optional<Player> winner() {
        final Optional<Player> playerWonByCheckMate = this.players.stream()
                .filter(x -> this.gameType.getGameController().isWinner(x)).findAny();
        if (playerWonByCheckMate.isPresent()) {
            return playerWonByCheckMate;
        } else if (Optional.ofNullable(this.timer).isPresent() && this.timer.getPlayerWithoutTime().isPresent()) {
            return this.players.stream().filter(plr -> this.timer.getRemaningTime(plr) > 0).findAny();
        }
        return Optional.empty();
    }

    @Override
    public final Board getBoardAtIndexFromHistory(final int index) {
        return this.history.getBoardAtIndex(index);
    }

    @Override
    public final Board getBoard() {
        return this.gameType.getGameController().boardState();
    }

    @Override
    public final GameController getGameController() {
        return this.gameType.getGameController();
    }

    @Override
    public final Pair<Player, Integer> getPlayerTimeRemaining() {
        final Player player = this.gameType.getMovementManager().getPlayerTurn();
        final int timeRemaining = this.timer.getRemaningTime(player);
        return new Pair<>(player, timeRemaining);
    }

    @Override
    public final List<Board> getBoardFullHistory() {
        return this.history.getAllBoards();
    }

    @Override
    public final MovementManager getMovementManager() {
        return this.gameType.getMovementManager();
    }

    @Override
    public final Set<BoardPosition> getPiecePossibleMoves(final Piece piece) {
        return this.getMovementManager().filterOnPossibleMovesBasedOnGameController(piece);
    }

    @Override
    public final void uploadMatchHistory(final List<Board> boardHistory) {
        this.history.updateHistory(boardHistory);
    }

}
