package ru.stqa.pft.sandbox;
import java.util.Random;

public class DistanceCalculation {

  public static void main(String[] args) {
    Random randNumber = new Random();

    for(int n = 0; n < 10; n++){
      System.out.println("=====Пример " + (n+1) + "=====");
      int x1 = -10 + randNumber.nextInt(20);
      int x2 = -10 + randNumber.nextInt(20);
      int y1 = -10 + randNumber.nextInt(20);
      int y2 = -10 + randNumber.nextInt(20);
      Point p1 = new Point(x1, y1);
      Point p2 = new Point(x2, y2);
      System.out.println("Расстояние между точками p1(" + p1.x + "," + p1.y + ") и p2(" + p2.x + "," + p2.y + ") = " + p1.distance(p2));

    }
  }

}
