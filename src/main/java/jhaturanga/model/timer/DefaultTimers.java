package jhaturanga.model.timer;

import java.util.List;
import java.util.Optional;

import jhaturanga.model.player.Player;

public enum DefaultTimers {
    /**
     * one minutes.
     */
    ONE_MINUTE("One Minute", 60),
    /**
     * two minutes.
     */
    THREE_MINUTE("Three Minutes", 180),
    /**
     * ten minutes.
     */
    TEN_MINUTES("Ten Minutes", 600),

    /**
     * 10 seconds.
     */
    TEN_SECONDS("Ten Seconds", 10),

    /**
     * 10 seconds with 1 second of increment.
     */
    TEN_SECONDS_PLUS_ONE("Ten Seconds + One", 10, 1);

    private final String stringify;
    private final int seconds;
    private final int increment;

    DefaultTimers(final String stringify, final int seconds) {
        this(stringify, seconds, 0);
    }

    DefaultTimers(final String stringify, final int seconds, final Integer increment) {
        this.stringify = stringify;
        this.seconds = seconds;
        this.increment = increment;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public Optional<Integer> getIncrement() {
        return this.increment != 0 ? Optional.of(this.increment) : Optional.empty();
    }

    public Timer getTimer(final Player whitePlayer, final Player blackPlayer) {
        return new TimerFactoryImpl().equalTimer(List.of(whitePlayer, blackPlayer), this.increment);
    }

    @Override
    public String toString() {
        return this.stringify;
    }

}
