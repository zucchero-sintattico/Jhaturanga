package jhaturanga.model.board;

public class BoardPositionImpl implements BoardPosition {

    /**
     * The serial version.
     */
    private static final long serialVersionUID = -7518041140044999585L;
    private final int xPosition;
    private final int yPosition;

    public BoardPositionImpl(final int xPosition, final int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public BoardPositionImpl(final BoardPosition pos) {
        this.xPosition = pos.getX();
        this.yPosition = pos.getY();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getX() {
        return this.xPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getY() {
        return this.yPosition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return "BoardPositionImpl [xPosition=" + xPosition + ", yPosition=" + yPosition + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + xPosition;
        result = prime * result + yPosition;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BoardPositionImpl other = (BoardPositionImpl) obj;
        if (xPosition != other.xPosition) {
            return false;
        }
        return yPosition == other.yPosition;
    }

}
