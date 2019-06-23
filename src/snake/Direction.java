package snake;

public enum Direction {
  UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);

  public final int row;
  public final int col;

  public boolean notRevert(Direction d) {
    switch (this) {
      case UP:
        return d != DOWN;
      case DOWN:
        return d != UP;
      case LEFT:
        return d != RIGHT;
      case RIGHT:
        return d != LEFT;
      default:
        return false;
    }
  }

  Direction(int row, int col) {
    this.row = row;
    this.col = col;
  }
}
