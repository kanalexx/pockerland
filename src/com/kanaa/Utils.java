package com.kanaa;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {

  /** Погрешность вычисления */
  public static final double EXP = 0.01;
  /** Порядок округления */
  public static final int SCALE = 2;


  public static double roundTo(double num) {
    return BigDecimal.valueOf(num).setScale(SCALE, RoundingMode.HALF_UP).doubleValue();
  }
}
