package com.david.spring_auto_test.service;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class BaseService<T extends ChromeDriver> {

  @Autowired
  protected T chromeDriver;


  private void init() {

    chromeDriver.getLocalStorage();

    // 빈 탭 생성
    chromeDriver.executeScript("window.open('about:blank','_blank');");
  }

  private void fini() {
    chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    // 탭 종료
    chromeDriver.close();
  }

  // 실행하려는 코드 작성
  public abstract void runTest();

  public void exec() {
    init();
    runTest();
    fini();
  }
}
