package com.david.spring_auto_test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;
import sun.awt.SunToolkit.OperationTimedOut;

@Component
@RequiredArgsConstructor
public class CorridoreAUToKOService {
  private final ChromeDriver driver;
  private WebElement webElement;

  @PreDestroy
  private void destroy(){
    driver.quit();
  }

  public void runTest() {

    // 실행하려는 코드 작성
    driver.getLocalStorage();

    // 빈 탭 생성
    driver.executeScript("window.open('about:blank','_blank');");

    // 탭 목록 가져오기
    List<String> tabs = new ArrayList<>(driver.getWindowHandles());

    // 첫번째 탭으로 전환
    driver.switchTo().window(tabs.get(0));
    // 웹페이지 요청
    driver.get("http://127.0.0.1:9080/");

    // 로그인
    webElement = driver.findElement(By.cssSelector("body > div:nth-child(2) > header > div > div.header-menu-wrap > ul > li.d-pc > a"));
    webElement.click();

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    // 국가선택
    webElement = driver.findElement(By.cssSelector("#form-login > div.form-input.form-nationality > div > div.form-group.select-input > div > button"));
    webElement.click();

    webElement = driver.findElement(By.cssSelector("#form-login > div.form-input.form-nationality > div > div.form-group.select-input > div > div > ul > li:nth-child(2)"));
    webElement.click();


    webElement = driver.findElement(By.name("usernameText"));
    String daum_id ="test@wirebarley.com";
    webElement.sendKeys(daum_id);

    //iframe 내부에서 pw 필드 탐색
    webElement = driver.findElement(By.name("password"));
    String daum_pw ="1q2w3e4r5t!";
    webElement.sendKeys(daum_pw);


    webElement = driver.findElement(By.id("btn-login"));
    webElement.click();

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    // 탭 종료
    driver.close();
  }



}
