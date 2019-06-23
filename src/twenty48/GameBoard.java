package twenty48;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class GameBoard extends JPanel {
  private static final Random random = new Random();
  private Cell[][] cells;
  private int score;
  private boolean moved = false;

  public GameBoard() {
    restart();

    this.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
          case KeyEvent.VK_UP -> moveUp();
          case KeyEvent.VK_DOWN -> moveDown();
          case KeyEvent.VK_LEFT -> moveLeft();
          case KeyEvent.VK_RIGHT -> moveRight();
          case KeyEvent.VK_R -> restart();
        }
        if (moved && !isGameOver()) set(next());
        repaint();
      }
    });
    this.setFocusable(true);
    this.requestFocus();
  }

  public void restart() {
    cells = new Cell[Conf.ROW][Conf.COL];
    set(next());
    set(next());
    score = 0;
  }

  public boolean isGameOver() {
    if (Arrays.stream(cells).flatMap(Arrays::stream).allMatch(Objects::nonNull)) {
      boolean cannotMerge = true;
      for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 3; j++) {
          if (cells[i][j].getValue() == cells[i][j + 1].getValue()) {
            cannotMerge = false;
            break;
          }
        }
      }
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 4; j++) {
          if (cells[i][j].getValue() == cells[i + 1][j].getValue()) {
            cannotMerge = false;
            break;
          }
        }
      }
      return cannotMerge;
    }
    return false;
  }

  public void moveUp() {
    for (int i = 0; i < Conf.ROW; i++) {
      for (int j = 0; j < Conf.COL; j++) {
        move(i, j, Direction.UP);
      }
    }
  }

  public void moveDown() {
    for (int i = Conf.ROW - 1; i >= 0; i--) {
      for (int j = 0; j < Conf.COL; j++) {
        move(i, j, Direction.DOWN);
      }
    }
  }

  public void moveLeft() {
    for (int i = 0; i < Conf.ROW; i++) {
      for (int j = 0; j < Conf.COL; j++) {
        move(i, j, Direction.LEFT);
      }
    }
  }

  public void moveRight() {
    for (int i = 0; i < Conf.ROW; i++) {
      for (int j = Conf.COL - 1; j >= 0; j--) {
        move(i, j, Direction.RIGHT);
      }
    }
  }

  private void move(int row, int col, Direction d) {
    while (canMove(cells[row][col], d)) {
      set(new Cell(cells[row][col], d));
      cells[row][col] = null;
      row += d.row;
      col += d.col;
      moved = true;
    }
    merge(row, col, d);
  }

  private void merge(int row, int col, Direction d) {
    if (canMerge(cells[row][col], d)) {
      score += cells[row + d.row][col + d.col].mergeValue();
      cells[row][col] = null;
      moved = true;
    }
  }

  private boolean canMove(Cell cell, Direction d) {
    if (cell == null) return false;
    int row = cell.getRow() + d.row;
    int col = cell.getCol() + d.col;
    return row >= 0 && row <= 3 && col >= 0 && col <= 3 && cells[row][col] == null;
  }

  private boolean canMerge(Cell cell, Direction d) {
    if (cell == null) return false;
    int row = cell.getRow() + d.row;
    int col = cell.getCol() + d.col;
    if (row < 0 || row > 3 || col < 0 || col > 3) return false;
    return cells[row][col].getValue() == cell.getValue();
  }

  private Cell next() {
    Cell next = Cell.next();
    while (cells[next.getRow()][next.getCol()] != null) {
      next = Cell.next();
    }
    return next;
  }

  private void set(Cell cell) {
    cells[cell.getRow()][cell.getCol()] = cell;
    moved = false;
  }

  /*--------------------------------------------------------------------------------------------------------------------*/

  @Override
  public void paint(Graphics g) {
    paintBack(g);
    paintCell(g);
    paintScore(g);
    paintGG(g);
  }

  private void paintBack(Graphics g) {
    g.setColor(Conf.BORDERLINE);
    g.fillRect(0, 0, Conf.WIDTH, Conf.HEIGHT);
  }

  private void paintCell(Graphics g) {
    for (int i = 0; i < Conf.ROW; i++) {
      for (int j = 0; j < Conf.COL; j++) {
        int x = (j + 1) * Conf.GAP_SIZE + j * Conf.CELL_SIZE;
        int y = (i + 1) * Conf.GAP_SIZE + i * Conf.CELL_SIZE;
        if (cells[i][j] == null) {
          g.setColor(Conf.BACKGROUND);
          g.fillRect(x, y, Conf.CELL_SIZE, Conf.CELL_SIZE);
        } else {
          g.setColor(Conf.getColor(cells[i][j]));
          g.fillRect(x, y, Conf.CELL_SIZE, Conf.CELL_SIZE);
          g.setFont(Conf.FONT);
          g.setColor(Conf.FONTCOLOR);
          g.drawString(cells[i][j].getValue() + "", x + Conf.CELL_SIZE / 3, y + Conf.CELL_SIZE / 2);
        }
      }
    }
  }

  private void paintScore(Graphics g) {
    g.clearRect(Conf.WIDTH / 3, Conf.HEIGHT, 150, 40);
    g.setFont(Conf.FONT);
    g.setColor(Conf.FONTCOLOR);
    g.drawString("Score: " + score, Conf.WIDTH / 3, Conf.HEIGHT + 30);
  }

  private void paintGG(Graphics g) {
    if (isGameOver()) {
      g.setFont(Conf.FONT);
      g.setColor(Color.WHITE);
      g.drawString("YOU DIED", Conf.WIDTH / 3, Conf.HEIGHT / 2);
    }
  }
}
