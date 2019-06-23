package snake;

import java.util.Random;

public class Cell {
  
  protected int row;
  protected int col;

  public Cell(int row, int col) {
    this.row = row;
    this.col = col;
  }
  
  public Cell() {
    this.row = 0;
    this.col = 0;
  }
  
  public int getRow() {
    return row;
  }
  public int getCol() {
    return col;
  }

  public boolean equals(Cell cell) {
    return this.row == cell.row && this.col == cell.col;
  }
}

class Food extends Cell {
  private static final Random random = new Random();

  public Food(Snake snake) {
    do {
      this.row = random.nextInt(Conf.ROW);
      this.col = random.nextInt(Conf.COL);
    } while (snake.stream().anyMatch(this::equals));
  }
}