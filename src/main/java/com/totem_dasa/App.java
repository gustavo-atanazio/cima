package com.totem_dasa;

import com.totem_dasa.tests.StockMovementTest;
import com.totem_dasa.tests.TotemTest;

public class App {
  public static void main(String[] args) {
    StockMovementTest.testAll();
    TotemTest.testAll();
  }
}