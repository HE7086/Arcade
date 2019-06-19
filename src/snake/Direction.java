package snake;

public enum Direction {
  UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);

  public final int row;
  public final int col;

  public boolean notRevert(Direction d) {
    return switch (this) {
      case UP -> d != DOWN;
      case DOWN -> d != UP;
      case LEFT -> d != RIGHT;
      case RIGHT -> d != LEFT;
    };
  }

  Direction(int row, int col) {
    this.row = row;
    this.col = col;
  }
}
