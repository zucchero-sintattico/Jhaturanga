package jhaturanga.model.timer;

import java.util.stream.Collectors;

import jhaturanga.model.player.pair.PlayerPair;

public enum DefaultTimers {

    /**
     * one minutes.
     */
    ONE_MINUTE("One Minute", 60),
    /**
     * two minutes.
     */
    THREE_MINUTES("Three Minutes", 180),
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
    TEN_SECONDS_PLUS_ONE("Ten Seconds + One", 10, 1),

    /**
     * Infinity timer.
     */
    NO_LIMIT("No limit", Double.POSITIVE_INFINITY);

    private final String stringify;
    private final double seconds;
    private final int increment;

    DefaultTimers(final String stringify, final double seconds) {
        this(stringify, seconds, 0);
    }

    DefaultTimers(final String stringify, final double seconds, final Integer increment) {
        this.stringify = stringify;
        this.seconds = seconds;
        this.increment = increment;
    }

    public double getSeconds() {
        return this.seconds;
    }

    public Timer getTimer(final PlayerPair players) {
        final Timer timer = new TimerFactoryImpl().equalTimer(players.stream().collect(Collectors.toList()),
                this.seconds);
        timer.setIncrement(this.increment);
        return timer;
    }

    @Override
    public String toString() {
        return this.stringify;
    }

}