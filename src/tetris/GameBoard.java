package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.Timer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.*;

public class GameBoard extends JPanel {

  private Tetris next;
  private Tetris tetris;
  private int score = 0;
  private int lines = 0;
  private int level = 1;
  private Cell[][] wall = new Cell[Conf.ROW][Conf.COL];
  private boolean isRunning = true;


  public GameBoard() {
    tetris = Tetris.next();
    next = Tetris.next();

    this.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
          case KeyEvent.VK_RIGHT:
            moveRight();
            break;
          case KeyEvent.VK_LEFT:
            moveLeft();
            break;
          case KeyEvent.VK_DOWN:
            moveDown();
            break;
          case KeyEvent.VK_UP:
            moveBottom();
            break;
          case KeyEvent.VK_A:
            spinCell(tetris.spin(true));
            break;
          case KeyEvent.VK_D:
            spinCell(tetris.spin(false));
            break;
          case KeyEvent.VK_R:
            restart();
            break;
          case KeyEvent.VK_P:
            isRunning = false;
            break;
          case KeyEvent.VK_C:
            isRunning = true;
            break;
          case KeyEvent.VK_ESCAPE:
            System.exit(0);
            break;
          case KeyEvent.VK_EQUALS:
            level++;
            break;
          case KeyEvent.VK_MINUS:
            level--;
            break;
        }
        repaint();
      }
    });
    this.setFocusable(true);
    this.requestFocus();

    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      int count = 0;

      @Override
      public void run() {
        if (isRunning) {
          int speed = (Conf.GAME_MAXLVL + 1 - level) * 5;
          if (count == speed) {
            moveDown();
            count = 0;
          }
        }
        count++;
        repaint();
      }
    }, 100, Conf.GAME_INTERVAL);
  }

  private void restart() {
    wall = new Cell[Conf.ROW][Conf.COL];
    tetris = Tetris.next();
    next = Tetris.next();
    score = 0;
    lines = 0;
    level = 1;
  }

  private void spinCell(Cell[] cells) {
    if (cells == null) return;
    for (Cell c : cells) {
      if (c.getRow() < 0 || c.getRow() >= Conf.ROW ||
          c.getCol() < 0 || c.getCol() >= Conf.COL ||
          wall[c.getRow()][c.getCol()] != null) return;
    }
    tetris.cells = cells;
  }

  private void moveLeft() {
    if (canMoveLeft()) tetris.moveLeft();
  }

  private void moveRight() {
    if (canMoveRight()) tetris.moveRight();
  }

  private void moveDown() {
    if (tetris == null) return;
    if (notBottom()) tetris.moveDown();
  }

  private void moveBottom() {
    if (tetris == null) return;
    while (notBottom()) tetris.moveDown();
  }

  private void removeLine() {
    int count = 0;
    for (int row = 0; row < Conf.ROW; row++) {
      if (Arrays.stream(wall[row]).noneMatch(Objects::isNull)) {
        Arrays.fill(wall[row], null);
        count++;
        lines += 1;
        level = lines % Conf.GAME_UPGRADE == 0 ? level == Conf.GAME_MAXLVL ? level : level + 1 : level;
        for (int i = row; i > 0; i--) {
          System.arraycopy(wall[i - 1], 0, wall[i], 0, Conf.COL);
        }
      }
    }
    switch (count) {
      case 1:
        score += Conf.SCORE_1 * level;
      case 2:
        score += Conf.SCORE_2 * level;
      case 3:
        score += Conf.SCORE_3 * level;
      case 4:
        score += Conf.SCORE_4 * level;
    }
  }

  private boolean notBottom() {
    if (tetris == null) return true;
    for (Cell c : tetris.cells) {
      if (c.getRow() + 1 == Conf.ROW || wall[c.getRow() + 1][c.getCol()] != null) {
        for (Cell cell : tetris.cells) {
          wall[cell.getRow()][cell.getCol()] = cell;
        }
        removeLine();
        tetris = next;
        next = Tetris.next();
        return false;
      }
    }
    return true;
  }

  private boolean canMoveRight() {
    if (tetris == null) return false;
    return Arrays.stream(tetris.cells)
        .noneMatch(c -> c.getCol() + 1 == Conf.COL || wall[c.getRow()][c.getCol() + 1] != null);
  }

  private boolean canMoveLeft() {
    if (tetris == null) return false;
    return Arrays.stream(tetris.cells)
        .noneMatch(c -> c.getCol() == 0 || wall[c.getRow()][c.getCol() - 1] != null);
  }

  private boolean isGameOver() {
    return Arrays.stream(wall[0]).anyMatch(Objects::nonNull);
  }

  /*--------------------------------------------------------------------------------------------------------------------*/

  @Override
  public void paint(Graphics g) {
    paintWall(g);
    paintTetromino(g);
    paintNextone(g);
    paintTabs(g);
    paintGamePause(g);
    paintGameOver(g);
  }

  private void paintWall(Graphics g) {
    for (int row = 0; row < Conf.ROW; row++) {
      for (int col = 0; col < Conf.COL; col++) {
        Cell cell = wall[row][col];
        int rows = row * Conf.CELL_SIZE;
        int cols = col * Conf.CELL_SIZE;
        if (cell != null) {
          g.setColor(cell.getColor());
          g.fillRect(cols, rows, Conf.CELL_EDGE, Conf.CELL_EDGE);
        } else {
          g.clearRect(cols, rows, Conf.CELL_EDGE, Conf.CELL_EDGE);
        }
      }
    }
  }

  private void paintTetromino(Graphics g) {
    if (tetris == null) return;
    for (Cell c : tetris.cells) {
      g.setColor(c.getColor());
      g.fillRect(c.getCol() * Conf.CELL_SIZE, c.getRow() * Conf.CELL_SIZE,
          Conf.CELL_EDGE, Conf.CELL_EDGE);
    }
  }

  private void paintNextone(Graphics g) {
    if (next == null) return;
    g.clearRect(338, 0, 78, 104);
    for (Cell c : next.cells) {
      g.setColor(c.getColor());
      g.fillRect((c.getCol() + Conf.COL - 1) * Conf.CELL_SIZE, c.getRow() * Conf.CELL_SIZE,
          Conf.CELL_EDGE, Conf.CELL_EDGE);
    }
  }

  private void paintTabs(Graphics g) {
    int x = 310;
    int y = 160;
    g.clearRect(280, 130, 200, 160);
    g.setFont(Conf.FONT);
    g.setColor(Conf.FONT_COLOR);
    g.drawString("SCORE: " + score, x, y);
    y += 60;
    g.drawString("LINES: " + lines, x, y);
    y += 60;
    g.drawString("LEVEL: " + level, x, y);
  }

  private void paintGamePause(Graphics g) {
    if (!isRunning && !isGameOver()) {
      g.setColor(Conf.FONT_COLOR);
      g.drawString("PAUSED", 15, 15);
    }
  }

  private void paintGameOver(Graphics g) {
    if (isGameOver()) {
      tetris = null;
      g.setColor(Conf.FONT_COLOR);
      g.clearRect(0, 120, 500, 250);
      g.setFont(Conf.FONT);
      g.setColor(Color.BLACK);
      g.drawString("YOU DIED", 10, 250);
      g.drawString("SCORE: " + score, 200, 200);
      g.drawString("LINES: " + lines, 200, 250);
      g.drawString("LEVEL: " + level, 200, 300);
      isRunning = false;
    }
  }
}
