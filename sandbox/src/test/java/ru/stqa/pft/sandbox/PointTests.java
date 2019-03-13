package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance1(){
    Point p1 = new Point(1, 7);
    Point p2 = new Point(7, 15);
    Assert.assertEquals(p1.distance(p2),10.0);
  }

  @Test
  public void testDistance2(){
    Point p1 = new Point(0, 5);
    Point p2 = new Point(0, 11);
    Assert.assertEquals(p1.distance(p2),6.0);
  }

  @Test
  public void testDistance3(){
    Point p1 = new Point(-3,-10);
    Point p2 = new Point(-3,-5);
    Assert.assertEquals(p1.distance(p2),5.0);
  }
}
