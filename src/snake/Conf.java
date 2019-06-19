package snake;

import java.awt.*;

public class Conf {
  public static final int FRAME_WIDTH = 416;
  public static final int FRAME_HEIGHT = 355;

  public static final int WIDTH = 400;
  public static final int HEIGHT = 300;

  public static final int CELL_WIDTH = 16;
  public static final int CELL_HEIGHT = 16;

  public static final int ROW = 15;
  public static final int COL = 20;
  public static final int X = (WIDTH - COL * CELL_WIDTH) / 2;
  public static final int Y = (HEIGHT - ROW * CELL_HEIGHT) / 2;

  public static final int INTERVAL = 150;

  public static final Font FONT = new Font(Font.SERIF, Font.BOLD, 30);

  public static final Color BODY_COLOR = Color.BLACK;
  public static final Color HEAD_COLOR = Color.GRAY;
  public static final Color BORDER_COLOR = Color.BLACK;
  public static final Color FOOD_COLOR = Color.RED;
  public static final Color FONT_COLOR = Color.BLACK;
}