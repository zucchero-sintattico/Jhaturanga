package jhaturanga.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import jhaturanga.model.game.gametypes.GameType;
import jhaturanga.model.match.Match;
import jhaturanga.model.match.MatchImpl;
import jhaturanga.model.player.Player;
import jhaturanga.model.timer.Timer;
import jhaturanga.model.user.User;

public final class ModelImpl implements Model {

    private final List<User> users = new ArrayList<>();
    private final List<Match> matches = new ArrayList<>();
    private Player whitePlayer;
    private Player blackPlayer;
    private Timer timer;
    private GameType selectedType;

    @Override
    public Optional<Match> getActualMatch() {
        if (!this.matches.isEmpty()) {
            return Optional.of(this.matches.get(this.matches.size() - 1));
        }
        return Optional.empty();
    }

    @Override
    public Match createMatch() {
        final Match match = new MatchImpl(this.getGameType().get(), this.getTimer());
        this.matches.add(match);
        return match;
    }

    @Override
    public void addLoggedUser(final User user) {
        this.users.add(user);
    }

    @Override
    public void removeLoggedUser(final User user) {
        this.users.remove(user);
    }

    @Override
    public List<User> getLoggedUsers() {
        return Collections.unmodifiableList(this.users);
    }

    @Override
    public void setGameType(final GameType gameType) {
        this.selectedType = gameType;
    }

    @Override
    public void setTimer(final Timer timer) {
        this.timer = timer;
    }

    @Override
    public Optional<Timer> getTimer() {
        return Optional.ofNullable(this.timer);
    }

    @Override
    public Optional<GameType> getGameType() {
        return Optional.ofNullable(this.selectedType);
    }

    @Override
    public void setWhitePlayer(final Player player) {
        this.whitePlayer = player;
    }

    @Override
    public void setBlackPlayer(final Player player) {
        this.blackPlayer = player;
    }

    @Override
    public Player getWhitePlayer() {
        return this.whitePlayer;
    }

    @Override
    public Player getBlackPlayer() {
        return this.blackPlayer;
    }

}