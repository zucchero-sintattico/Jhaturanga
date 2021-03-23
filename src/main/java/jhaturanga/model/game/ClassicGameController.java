package jhaturanga.model.game;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

import jhaturanga.model.board.Board;
import jhaturanga.model.board.BoardPosition;
import jhaturanga.model.board.BoardPositionImpl;
import jhaturanga.model.piece.Piece;
import jhaturanga.model.piece.PieceType;
import jhaturanga.model.piece.movement.PieceMovementStrategyFactory;
import jhaturanga.model.player.Player;

public class ClassicGameController implements GameController {

    private final Board board;
    private final PieceMovementStrategyFactory pieceMovementStrategies;
    private final List<Player> players;

    public ClassicGameController(final Board board, final PieceMovementStrategyFactory pieceMovementStrategies,
            final List<Player> players) {
        this.board = board;
        this.pieceMovementStrategies = pieceMovementStrategies;
        this.players = players;
    }

    @Override
    public final synchronized MatchStatusEnum checkGameStatus(final Player playerTurn) {
        if (this.isDraw(playerTurn)) {
            return MatchStatusEnum.DRAW;
        } else if (this.players.stream().filter(x -> this.isWinner(x)).findAny().isPresent()) {
            return MatchStatusEnum.CHECKMATE;
        } else {
            return MatchStatusEnum.ACTIVE;
        }
    }

    private boolean isDraw(final Player playerTurn) {
        return this.insufficientMaterialToWin() || this.isBlocked(playerTurn) && !this.isInCheck(playerTurn);
    }

    /**
     * Here are the piece combinations that lead to a draw by insufficient material:
     * [King vs. King] or [King and Bishop vs. King] or [King and Knight vs. King]
     * or [King and Bishop vs. King and Bishop]
     * 
     * @return True if the board doesn't contain enough pieces to legally let one of
     *         the two players win
     */
    protected boolean insufficientMaterialToWin() {
        final Supplier<Stream<Piece>> boardPieceStreamWithoutKings = () -> this.board.getBoardState().stream()
                .filter(i -> !i.getType().equals(PieceType.KING));

        return boardPieceStreamWithoutKings.get().count() == 0
                || this.areThereLessThanOrEqualTwoNonKingPieces(boardPieceStreamWithoutKings)
                        && (this.isThereOnlyOneKnight(boardPieceStreamWithoutKings)
                                || this.isThereOnlyOneBishop(boardPieceStreamWithoutKings)
                                || this.areThereTwoOppositeBishops(boardPieceStreamWithoutKings));
    }

    private boolean areThereTwoOppositeBishops(final Supplier<Stream<Piece>> boardStreamWithoutKings) {
        return boardStreamWithoutKings.get().allMatch(i -> i.getType().equals(PieceType.BISHOP))
                && boardStreamWithoutKings.get().filter(i -> i.getType().equals(PieceType.BISHOP)).map(Piece::getPlayer)
                        .distinct().count() == 2;
    }

    private boolean isThereOnlyOneKnight(final Supplier<Stream<Piece>> boardStreamWithoutKings) {
        return boardStreamWithoutKings.get().count() == 1
                && boardStreamWithoutKings.get().allMatch(i -> i.getType().equals(PieceType.KNIGHT));
    }

    private boolean isThereOnlyOneBishop(final Supplier<Stream<Piece>> boardStreamWithoutKings) {
        return boardStreamWithoutKings.get().count() == 1
                && boardStreamWithoutKings.get().allMatch(i -> i.getType().equals(PieceType.BISHOP));
    }

    private boolean areThereLessThanOrEqualTwoNonKingPieces(final Supplier<Stream<Piece>> boardStreamWithoutKings) {
        return boardStreamWithoutKings.get().count() <= 2;
    }

    @Override
    public final boolean isInCheck(final Player player) {
        final Optional<Piece> king = this.board.getBoardState().stream()
                .filter(i -> i.getPlayer().equals(player) && i.getType().equals(PieceType.KING)).findAny();
        /**
         * Apart from having a king, if it's position is present any of the enemies'
         * movementStrategy, then it means that the king is under check.
         */
        return king.isPresent()
                && this.board.getBoardState().stream().filter(i -> !i.getPlayer().equals(player))
                        .filter(x -> this.pieceMovementStrategies.getPieceMovementStrategy(x)
                                .getPossibleMoves(this.board).contains(king.get().getPiecePosition()))
                        .findAny().isPresent();
    }

    @Override
    public final boolean isWinner(final Player player) {
        return this.players.stream().filter(x -> !x.equals(player)).filter(this::isInCheck).filter(this::isBlocked)
                .findAny().isPresent();
    }

    private boolean isBlocked(final Player player) {
        final Set<Piece> supportBoard = new HashSet<>(this.board.getBoardState());

        return supportBoard.stream().filter(i -> i.getPlayer().equals(player)).filter(pieceToCheck -> {

            final BoardPosition oldPiecePosition = new BoardPositionImpl(pieceToCheck.getPiecePosition());
            final Set<BoardPosition> piecePossibleDestinations = this.pieceMovementStrategies
                    .getPieceMovementStrategy(pieceToCheck).getPossibleMoves(this.board);

            for (final BoardPosition pos : piecePossibleDestinations) {

                final Optional<Piece> oldPiece = this.board.getPieceAtPosition(pos);

                oldPiece.ifPresent(this.board::remove);

                pieceToCheck.setPosition(pos);

                if (!this.isInCheck(player)) {
                    pieceToCheck.setPosition(oldPiecePosition);
                    oldPiece.ifPresent(this.board::add);
                    return true;
                }

                pieceToCheck.setPosition(oldPiecePosition);

                oldPiece.ifPresent(this.board::add);
            }
            return false;

        }).findAny().isEmpty();

    }

    @Override
    public final Board boardState() {
        return this.board;
    }

    @Override
    public final List<Player> getPlayers() {
        return this.players;
    }

    @Override
    public final PieceMovementStrategyFactory getPieceMovementStrategyFactory() {
        return this.pieceMovementStrategies;
    }

}