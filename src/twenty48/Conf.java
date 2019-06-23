package twenty48;

import java.awt.*;

public class Conf {

  public static final int ROW = 4;
  public static final int COL = 4;
  public static final int FRAME_WIDTH = 390;
  public static final int FRAME_HEIGHT = 450;
  public static final int CELL_SIZE = 75;
  public static final int GAP_SIZE = 15;
  public static final int WIDTH = ROW * CELL_SIZE + (ROW + 1) * GAP_SIZE;
  public static final int HEIGHT = COL * CELL_SIZE + (COL + 1) * GAP_SIZE;

  public static final Font FONT = new Font(Font.SERIF, Font.BOLD, 30);
  public static final Color FONTCOLOR = Color.BLACK;

  public static final Color BACKGROUND = new Color(80, 75, 70);
  public static final Color BORDERLINE = new Color(73, 68, 63);
  public static final Color C2 = new Color(93, 89, 86);
  public static final Color C4 = new Color(93, 88, 78);
  public static final Color C8 = new Color(95, 69, 48);
  public static final Color C16 = new Color(93, 55, 33);
  public static final Color C32 = new Color(96, 49, 37);
  public static final Color C64 = new Color(91, 35, 22);
  public static final Color C128 = new Color(95, 82, 29);
  public static final Color COther = Color.GRAY;
//  public static final Color C256
//  public static final Color C512
//  public static final Color C1024
//  public static final Color C2048

  public static Color getColor(Cell cell) {
    return switch (cell.getValue()) {
      case 2 -> C2;
      case 4 -> C4;
      case 8 -> C8;
      case 16 -> C16;
      case 32 -> C32;
      case 64 -> C64;
      case 128, 256, 512, 1024, 2048 -> C128;
      default -> COther;
    };
  }
}
