package tetris;

import java.awt.*;

public class Cell {
  private int row;
  private int col;
  private Color color;

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public Color getColor() {
    return color;
  }

  public Cell(int row, int col, Color color) {
    this.row = row;
    this.col = col;
    this.color = color;
  }

  public void moveDown() {
    row++;
  }

  public void moveLeft() {
    col--;
  }

  public void moveRight() {
    col++;
  }
}
