package jhaturanga.model.board;

import java.util.HashSet;
import java.util.Set;

import jhaturanga.model.piece.Piece;

public class BoardBuilderImpl implements BoardBuilder {

    private int rows;
    private int columns;
    private final Set<Piece> piecesOnBoard = new HashSet<>();

    @Override
    public final BoardBuilder rows(final int rows) {
        this.rows = rows;
        return this;
    }

    @Override
    public final BoardBuilder columns(final int columns) {
        this.columns = columns;
        return this;
    }

    @Override
    public final BoardBuilder addPiece(final Piece piece) {
        this.piecesOnBoard.add(piece);
        return this;
    }

    @Override
    public final Board build() {
        return new BoardImpl(this.piecesOnBoard, this.columns, this.rows);
    }

}