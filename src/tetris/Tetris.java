package tetris;

import java.util.Random;

public class Tetris {

  protected Cell[] cells;
  private static final Random random = new Random();

  public void moveDown() {
    for (Cell cell : cells) cell.moveDown();
  }

  public void moveLeft() {
    for (Cell cell : cells) cell.moveLeft();
  }

  public void moveRight() {
    for (Cell cell : cells) cell.moveRight();
  }

  public static Tetris next() {
    switch (random.nextInt(7)) {
      case 0:
        return new J();
      case 1:
        return new L();
      case 2:
        return new O();
      case 3:
        return new Z();
      case 4:
        return new S();
      case 5:
        return new I();
      case 6:
        return new T();
      default:
        return null;
    }
  }

  public Cell[] spin(boolean clockwise) {
    if (this.getClass().equals(O.class)) return null;
    Cell[] spined = cells.clone();
    int row = cells[0].getRow();
    int col = cells[0].getCol();
    for (int i = 1; i < cells.length; i++) {
      int newRow = cells[i].getRow();
      int newCol = cells[i].getCol();
      if (clockwise) {
        spined[i] = new Cell(row - col + newCol, row + col - newRow, cells[i].getColor());
      } else {
        spined[i] = new Cell(row + col - newCol, col - row + newRow, cells[i].getColor());
      }
    }
    return spined;
  }
}

class J extends Tetris {
  public J() {
    this.cells = new Cell[]{
        new Cell(2, 6, Conf.J_COLOR),
        new Cell(2, 5, Conf.J_COLOR),
        new Cell(0, 6, Conf.J_COLOR),
        new Cell(1, 6, Conf.J_COLOR)
    };

  }
}

class L extends Tetris {

  public L() {
    this.cells = new Cell[]{
        new Cell(2, 5, Conf.L_COLOR),
        new Cell(2, 6, Conf.L_COLOR),
        new Cell(0, 5, Conf.L_COLOR),
        new Cell(1, 5, Conf.L_COLOR)
    };
  }
}

class O extends Tetris {
  public O() {
    this.cells = new Cell[]{
        new Cell(1, 5, Conf.O_COLOR),
        new Cell(0, 5, Conf.O_COLOR),
        new Cell(0, 6, Conf.O_COLOR),
        new Cell(1, 6, Conf.O_COLOR)
    };
  }
}

class Z extends Tetris {
  public Z() {
    this.cells = new Cell[]{
        new Cell(1, 5, Conf.Z_COLOR),
        new Cell(0, 4, Conf.Z_COLOR),
        new Cell(0, 5, Conf.Z_COLOR),
        new Cell(1, 6, Conf.Z_COLOR)
    };
  }
}

class S extends Tetris {
  public S() {
    this.cells = new Cell[]{
        new Cell(0, 5, Conf.S_COLOR),
        new Cell(1, 4, Conf.S_COLOR),
        new Cell(1, 5, Conf.S_COLOR),
        new Cell(0, 6, Conf.S_COLOR)
    };
  }
}

class I extends Tetris {
  public I() {
    this.cells = new Cell[]{
        new Cell(2, 5, Conf.I_COLOR),
        new Cell(0, 5, Conf.I_COLOR),
        new Cell(1, 5, Conf.I_COLOR),
        new Cell(3, 5, Conf.I_COLOR)
    };
  }
}

class T extends Tetris {
  public T() {
    this.cells = new Cell[]{
        new Cell(0, 5, Conf.T_COLOR),
        new Cell(0, 4, Conf.T_COLOR),
        new Cell(0, 6, Conf.T_COLOR),
        new Cell(1, 5, Conf.T_COLOR)
    };
  }
}

