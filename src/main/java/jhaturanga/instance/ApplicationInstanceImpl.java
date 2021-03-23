package jhaturanga.instance;

import java.util.Optional;

import jhaturanga.model.match.Match;
import jhaturanga.model.user.User;

public final class ApplicationInstanceImpl implements ApplicationInstance {

    private User firstUser;
    private User secondUser;

    private Match match;

    @Override
    public void setFirstUser(final User user) {
        this.firstUser = user;
    }

    @Override
    public void setSecondUser(final User user) {
        this.secondUser = user;
    }

    public Optional<User> getFirstUser() {
        return Optional.ofNullable(this.firstUser);
    }

    @Override
    public Optional<User> getSecondUser() {
        return Optional.ofNullable(this.secondUser);
    }

    @Override
    public void setMatch(final Match match) {
        this.match = match;
    }

    @Override
    public Optional<Match> getActualMatch() {
        return Optional.ofNullable(this.match);
    }

//  private final List<Match> matches = new ArrayList<>();
//  private Player whitePlayer;
//  private Player blackPlayer;
//  private Timer timer;
//  private GameTypesEnum selectedType;
//  private StringBoard customizedStartingBoard;
//
//  private DefaultTimers selectedTimer;

//    @Override
//    public void clearMatchInfo() {
//        this.customizedStartingBoard = null;
//        this.selectedType = null;
//    }

//  @Override
//  public void createMatch() {
//      if (this.selectedType.equals(GameTypesEnum.CUSTOM_BOARD_VARIANT)) {
//          this.matches.add(new MatchImpl(this.selectedType.getDynamicGameType(this.whitePlayer, this.blackPlayer,
//                  this.customizedStartingBoard), this.timer));
//      } else {
//          this.matches
//                  .add(new MatchImpl(this.selectedType.getGameType(this.whitePlayer, this.blackPlayer), this.timer));
//      }
//  }
//
//  @Override
//  public void setDynamicGameTypeStartingBoard(final StringBoard startingBoardInfo) {
//      this.customizedStartingBoard = startingBoardInfo;
//  }
//
//  @Override
//  public void setGameType(final GameTypesEnum gameType) {
//      this.selectedType = gameType;
//  }
//
//  @Override
//
//  public void setTimer(final DefaultTimers timer) {
//
//      this.selectedTimer = timer;
//      /*
//       * timer.getIncrement().ifPresentOrElse(increment -> { this.timer = new
//       * TimerFactoryImpl().incrementableTimer(List.of(this.whitePlayer,
//       * this.blackPlayer), timer.getSeconds(), increment); }, () -> { this.timer =
//       * new TimerFactoryImpl().equalTimer(List.of(this.whitePlayer,
//       * this.blackPlayer), timer.getSeconds()); });
//       */
//  }
//
//  @Override
//  public Optional<String> getSettedGameTypeName() {
//      if (Optional.ofNullable(this.selectedType).isPresent()) {
//          return Optional.ofNullable(this.selectedType.toString());
//      }
//      return Optional.empty();
//  }
//
//  @Override
//  public boolean isDynamicGameTypeSet() {
//      return Optional.ofNullable(this.customizedStartingBoard).isPresent();
//  }
//
//  @Override
//  public Optional<Timer> getTimer() {
//      return Optional.ofNullable(this.timer);
//  }
//
//  @Override
//  public Optional<GameTypesEnum> getGameType() {
//      return Optional.ofNullable(this.selectedType);
//  }
//
//  @Override
//  public void setWhitePlayer(final Player player) {
//      this.whitePlayer = player;
//  }
//
//  @Override
//  public void setBlackPlayer(final Player player) {
//      this.blackPlayer = player;
//  }
//
//  @Override
//  public Optional<Player> getWhitePlayer() {
//      return Optional.ofNullable(this.whitePlayer);
//  }
//
//  @Override
//  public Optional<Player> getBlackPlayer() {
//      return Optional.ofNullable(this.blackPlayer);
//  }

}
