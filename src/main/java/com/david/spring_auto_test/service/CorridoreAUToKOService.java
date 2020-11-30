package com.david.spring_auto_test.service;

import com.david.spring_auto_test.config.AppConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;
import sun.awt.SunToolkit.OperationTimedOut;

@Component
public class CorridoreAUToKOService extends BaseService <ChromeDriver> {
  private final ChromeDriver driver;
  private final AppConfig appConfig;


  public CorridoreAUToKOService(ChromeDriver driver, AppConfig appConfig) {
    super(driver);
    this.driver = driver;
    this.appConfig = appConfig;
  }

  @Override
  public void run() {
    // 탭 목록 가져오기
    List<String> tabs = new ArrayList<>(driver.getWindowHandles());

    // 첫번째 탭으로 전환
    driver.switchTo().window(tabs.get(0));
    // 웹페이지 요청
    driver.get(appConfig.getTargetUrl());


    runLogin();

  }

  private void runLogin() {

    // 로그인
    WebElement webElement = driver.findElement(By.cssSelector("body > div:nth-child(2) > header > div > div.header-menu-wrap > ul > li.d-pc > a"));
    webElement.click();

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    // 국가선택
    webElement = driver.findElement(By.cssSelector("#form-login > div.form-input.form-nationality > div > div.form-group.select-input > div > button"));
    webElement.click();

    webElement = driver.findElement(By.cssSelector("#form-login > div.form-input.form-nationality > div > div.form-group.select-input > div > div > ul > li:nth-child(2)"));
    webElement.click();


    webElement = driver.findElement(By.name("usernameText"));
    webElement.sendKeys(appConfig.getLoginId());

    //iframe 내부에서 pw 필드 탐색
    webElement = driver.findElement(By.name("password"));
    webElement.sendKeys(appConfig.getLoginPw());


    webElement = driver.findElement(By.id("btn-login"));
    webElement.click();
  }


}
