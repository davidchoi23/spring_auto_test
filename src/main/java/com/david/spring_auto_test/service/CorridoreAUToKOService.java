package com.david.spring_auto_test.service;

import com.david.spring_auto_test.config.AppConfig;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

@Component
public class CorridoreAUToKOService extends BaseService <ChromeDriver , AppConfig> {
  private final ChromeDriver driver;
  private final AppConfig appConfig;

  public CorridoreAUToKOService(ChromeDriver driver, AppConfig appConfig) {
    super(driver, appConfig);
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
    // 로그인
    runLogin();

  }




}
