package jhaturanga.model.timer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jhaturanga.model.player.Player;

public final class TimerFactoryImpl implements TimerFactory {

    @Override
    public Timer defaultTimer(final List<Player> players) {
        return this.equalTimer(players, DefaultsTimers.TEN_MINUTES.getSeconds());
    }

    @Override
    public Timer equalTimer(final List<Player> players, final int duration) {
        final Map<Player, Integer> playerTimerMap = new HashMap<>();
        players.forEach((elem) -> playerTimerMap.put(elem, duration));
        return this.fromTimerMap(playerTimerMap);
    }

    @Override
    public Timer incrementableTimer(final List<Player> players, final int duration, final int increment) {
        final Map<Player, Integer> playerTimerMap = new HashMap<>();
        players.forEach((elem) -> playerTimerMap.put(elem, duration));
        final Timer timer = this.fromTimerMap(playerTimerMap);
        timer.setModifiable(true);
        timer.setIncrement(Optional.of(increment));
        return timer;
    }

    @Override
    public Timer fromTimerMap(final Map<Player, Integer> durations) {
        return new TimerImpl(durations);
    }

}