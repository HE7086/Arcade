package twenty48;

import java.util.Random;

public class Cell {

  private static final Random random = new Random();
  private int row;
  private int col;
  private int value;

  public Cell(int row, int col, int value) {
    this.row = row;
    this.col = col;
    this.value = value;
  }

  public Cell(Cell cell, Direction d) {
    this.row = cell.row + d.row;
    this.col = cell.col + d.col;
    this.value = cell.value;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getCol() {
    return col;
  }

  public void setCol(int col) {
    this.col = col;
  }

  public int getValue() {
    return value;
  }

  public int mergeValue() {
    return this.value <<= 1;
  }

  public static Cell next() {
    int row = random.nextInt(4);
    int col = random.nextInt(4);
    int value = random.nextInt(9) == 0 ? 4 : 2;
    return new Cell(row, col, value);
  }

  @Override
  public String toString() {
    return value + "";
  }
}
