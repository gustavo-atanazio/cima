package com.totem_dasa;

import com.totem_dasa.tests.StockMovementTest;
import com.totem_dasa.tests.SupplyMovementTest;
import com.totem_dasa.tests.TotemTest;

public class App {
  public static void main(String[] args) {
    TotemTest.testAll();
    StockMovementTest.testAll();
    SupplyMovementTest.testAll();
  }
}