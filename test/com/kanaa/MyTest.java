package com.kanaa;

import org.apache.log4j.PropertyConfigurator;
import org.junit.BeforeClass;

public class MyTest {
  @BeforeClass
  public static void init() throws Exception {
    PropertyConfigurator.configure("log4Tests.properties");
  }

}
