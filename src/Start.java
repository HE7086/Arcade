import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Start {
  private static JFrame start = new JFrame();
  public static void main(String[] args) {
    start.setLayout(new GridLayout());
    start.setTitle("The Arcade Project v0.1");
    JLabel label = new JLabel("Choose One To Play!", JLabel.CENTER);
    JButton runSnake = new JButton("Snake");
    JButton runTetris = new JButton("Tetris");
    JButton run2048 = new JButton("2048");
    start.add(label);
    start.add(runSnake);
    start.add(runTetris);
    start.add(run2048);
    start.setLocationRelativeTo(null);
    start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    runSnake.addActionListener(e -> {
      JPanel panel = new snake.GameBoard();
      JFrame frame = new JFrame();
      frame.add(panel);
      frame.setTitle("The Snake");
      frame.setSize(snake.Conf.FRAME_WIDTH, snake.Conf.FRAME_HEIGHT);
      play(frame);
    });
    runTetris.addActionListener(e -> {
      JPanel panel = new tetris.GameBoard();
      JFrame frame = new JFrame();
      frame.setTitle("The Tetris");
      frame.setSize(tetris.Conf.FRAME_WIDTH, tetris.Conf.FRAME_HEIGHT);
      frame.add(panel);
      play(frame);
    });
    run2048.addActionListener(e -> {
      JPanel panel = new twenty48.GameBoard();
      JFrame frame = new JFrame();
      frame.setTitle("2048");
      frame.setSize(twenty48.Conf.FRAME_WIDTH, twenty48.Conf.FRAME_HEIGHT);
      frame.add(panel);
      play(frame);
    });
    start.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          System.exit(0);
        }
      }
    });
    start.pack();
    start.setVisible(true);
  }

  private static void play(JFrame frame) {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          System.exit(0);
        }
      }
    });
    frame.setVisible(true);
    start.dispose();
  }
}
