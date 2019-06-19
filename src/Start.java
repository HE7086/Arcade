import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Start {
  private static JFrame start = new JFrame();
  public static void main(String[] args) {
    start.setLayout(new GridLayout());
    start.setTitle("The Arcade Project v0.1");
    JButton runSnake = new JButton("Snake");
    JLabel label = new JLabel("Choose One To Play!", JLabel.CENTER);
    JButton runTetris = new JButton("Tetris");
    start.add(runSnake);
    start.add(label);
    start.add(runTetris);
    start.setLocationRelativeTo(null);
    start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
      frame.setSize(tetris.Conf.FRAME_WIDTH, tetris.Conf.FRAME_HIGHT);
      frame.add(panel);
      play(frame);
    });
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
