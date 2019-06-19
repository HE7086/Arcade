package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class GameBoard extends JPanel {

  private Snake snake;
  private int score = 0;

  public GameBoard() {
    snake = new Snake();
    snake.setFood(new Food(snake));

    this.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        snake.setHead(switch (e.getKeyCode()) {
          case KeyEvent.VK_UP -> Direction.UP;
          case KeyEvent.VK_DOWN -> Direction.DOWN;
          case KeyEvent.VK_LEFT -> Direction.LEFT;
          case KeyEvent.VK_RIGHT -> Direction.RIGHT;
          default -> snake.getHead();
        });
        repaint();
      }
    });
    this.setFocusable(true);
    this.requestFocus();

    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        snake.move();
        score = snake.getBody().size();
        repaint();
      }
    }, 100, Conf.INTERVAL);
  }

/*--------------------------------------------------------------------------------------------------------------------*/

  @Override
  public void paint(Graphics g) {
    g.clearRect(Conf.X, Conf.Y, Conf.COL * Conf.CELL_WIDTH, Conf.ROW * Conf.CELL_HEIGHT);
    paintBorderline(g);
    paintHead(g);
    paintBody(g);
    paintFood(g);
    paintScore(g);
  }

  private void paintBorderline(Graphics g) {
    g.setColor(Conf.BORDER_COLOR);
    g.drawRect(Conf.X, Conf.Y, Conf.COL * Conf.CELL_WIDTH, Conf.ROW * Conf.CELL_HEIGHT);
  }

  private void paintHead(Graphics g) {
    g.setColor(Conf.HEAD_COLOR);
    g.fillRect(snake.getBody().getFirst().getCol() * Conf.CELL_WIDTH + Conf.X + 1,
        snake.getBody().getFirst().getRow() * Conf.CELL_HEIGHT + Conf.Y + 1,
        Conf.CELL_WIDTH - 2, Conf.CELL_HEIGHT - 2);
  }

  private void paintBody(Graphics g) {
    snake.stream().skip(1).forEach(sb -> {
      g.setColor(Conf.BODY_COLOR);
      g.fillRect(sb.getCol() * Conf.CELL_WIDTH + Conf.X + 1, sb.getRow() * Conf.CELL_HEIGHT + Conf.Y + 1,
          Conf.CELL_WIDTH - 2, Conf.CELL_HEIGHT - 2);
    });
  }

  private void paintFood(Graphics g) {
    Food food = snake.getFood();
    g.setColor(Conf.FOOD_COLOR);
    g.fillOval(food.getCol() * Conf.CELL_WIDTH + Conf.X, food.getRow() * Conf.CELL_HEIGHT + Conf.Y,
        Conf.CELL_WIDTH, Conf.CELL_HEIGHT);
  }

  private void paintScore(Graphics g) {
    g.clearRect(Conf.X, 275, 150, 30);
    g.setColor(Conf.FONT_COLOR);
    g.setFont(Conf.FONT);
    g.drawString("score: " + score, Conf.X, 300);
  }
}
