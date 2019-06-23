package twenty48;

import javax.swing.*;

public class test {

  public static void main(String[] args) {
    GameBoard g = new GameBoard();
    JFrame f = new JFrame();

    f.setSize(390,415);

    f.setLocationRelativeTo(null);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.add(g);

    f.setVisible(true);
  }
}
