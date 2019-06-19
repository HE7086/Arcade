package snake;

import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Stream;

public class Snake {

  private Direction dir;
  private Direction head;
  private static final Random random = new Random();
  private Food food;
  private LinkedList<Cell> body= new LinkedList<>();

  public Snake() {
    reset();
  }

  public Direction getHead() {
    return head;
  }

  public void setHead(Direction dir) {
    this.head = dir;
  }

  public Food getFood() {
    return food;
  }

  public void setFood(Food food) {
    this.food = food;
  }

  public LinkedList<Cell> getBody() {
    return body;
  }

  public Stream<Cell> stream() {
    return this.getBody().stream();
  }

  public void reset() {
    body.clear();
    Cell newHead = new Cell(random.nextInt(Conf.ROW - 3), random.nextInt(Conf.COL - 3));
    body.add(newHead);
    body.add(new Cell(newHead.getRow() + 1, newHead.getCol()));
    body.add(new Cell(newHead.getRow() + 2, newHead.getCol()));
    setHead(dir = Direction.UP);
  }

  public void move() {
    if (head != null && dir.notRevert(head)) dir = head;
    Cell next = new Cell(body.getFirst().getRow() + dir.row, body.getFirst().getCol() + dir.col);

    if (food.equals(next)) {
      setFood(new Food(this));
    } else {
      body.removeLast();
    }
    if (collide(next)) {
      reset();
    } else {
      body.addFirst(next);
    }
  }

  private boolean collide(Cell next) {
    return next.getRow() < 0 || next.getRow() > Conf.ROW - 1
        || next.getCol() < 0 || next.getCol() > Conf.COL - 1
        || body.stream().anyMatch(sb -> sb.equals(next));
  }

}