package jhaturanga.model.timer;

import java.util.Map;

import jhaturanga.model.player.Player;

public final class TimerImpl implements Timer {

    private final Map<Player, Integer> playersTimers;

    private boolean isModifiable = true;

    private Player actualPlayerTimer;
    private long initialUnixTime;

    public TimerImpl(final Map<Player, Integer> playersTimers) {
        this.playersTimers = playersTimers;
    }

    @Override
    public int getRemaningTime(final Player player) {
        if (player.equals(this.actualPlayerTimer)) {
            final int numberOfSecondsUsed = (int) (System.currentTimeMillis() / 1000L - initialUnixTime);
            int remainingSecond = playersTimers.get(actualPlayerTimer) - numberOfSecondsUsed;
            if (remainingSecond < 0) {
                remainingSecond = 0;
            }
            return remainingSecond;
        }
        return playersTimers.get(player);
    }

    @Override
    public void start(final Player player) {
        this.actualPlayerTimer = player;
        this.initialUnixTime = System.currentTimeMillis() / 1000L;

    }

    @Override
    public void switchPlayer(final Player player) {
        this.playersTimers.replace(actualPlayerTimer, getRemaningTime(actualPlayerTimer));
        start(player);

    }

    @Override
    public boolean isModifiable() {
        return isModifiable;
    }

    public void setModifiable(final boolean modifiable) {
        this.isModifiable = modifiable;
    }

    @Override
    public boolean updatePlayerTime(final Player player, final int second) {
        if (this.isModifiable) {
            this.playersTimers.replace(player, second);
            return true;
        }
        return false;
    }

    @Override
    public boolean addTimeToPlayer(final Player player, final int seconds) {
        return updatePlayerTime(player, seconds + this.playersTimers.get(player));
    }

}