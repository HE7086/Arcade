package snake;

import java.util.Random;

public class Cell {
  
  protected int col;
  protected int row;
  
  public Cell(int row, int col) {
    this.col = col;
    this.row = row;
  }
  
  public Cell() {
    this.col = 0;
    this.row = 0;
  }
  
  public int getCol() {
    return col;
  }
  public int getRow() {
    return row;
  }

  public boolean equals(Cell cell) {
    return this.col == cell.col && this.row == cell.row;
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