package com.david.spring_auto_test.service;

import com.david.spring_auto_test.config.AppConfig;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuKoService extends BaseService<ChromeDriver, AppConfig> {
    private final ChromeDriver driver;
    private final AppConfig appConfig;

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

        step1();


    }

    private void step1() {
        //송금하기
        WebElement webElement = driver.findElement(By.cssSelector("body > div:nth-child(2) > header > div > a"));
        webElement.click();

        //구간공지닫기
        webElement = driver.findElement(By.cssSelector("#wb-tx-announceModal > div > div > button.buttons.btn-close"));
        webElement.click();


        //수취국 선택
        webElement = driver.findElement(By.cssSelector("#tx-step1 > div > div.col-md-10.col-sm-10.col-xs-12 > div:nth-child(2) > div > div > div.tx > div.receive.col-md-12 > div:nth-child(1) > div > div > button"));
        webElement.click();
        webElement = driver.findElement(By.cssSelector("#tx-step1 > div > div.col-md-10.col-sm-10.col-xs-12 > div:nth-child(2) > div > div > div.tx > div.receive.col-md-12 > div:nth-child(1) > div > div > div > ul > li:nth-child(12)"));
        webElement.click();


        //구간공지닫기
        webElement = driver.findElement(By.cssSelector("#wb-tx-announceModal > div > div > button.buttons.btn-close"));
        webElement.click();


        //수취방식 선택
        webElement = driver.findElement(By.cssSelector("#tx-step1 > div > div.col-md-10.col-sm-10.col-xs-12 > div:nth-child(2) > div > div > div.tx-optionnew > div.wrap_item > div.transfer.item.clearfix > div.select > div > div.border-underline-green.input-group > div.btn-group.bootstrap-select.input-group-btn.evt-change-option.fit-width > button"));
        webElement.click();
        webElement = driver.findElement(By.cssSelector("#tx-step1 > div > div.col-md-10.col-sm-10.col-xs-12 > div:nth-child(2) > div > div > div.tx-optionnew > div.wrap_item > div.transfer.item.clearfix > div.select > div > div.border-underline-green.input-group > div.btn-group.bootstrap-select.input-group-btn.evt-change-option.fit-width.open > div > ul > li:nth-child(2)"));
        webElement.click();

        //구간공지닫기
        webElement = driver.findElement(By.cssSelector("#wb-tx-announceModal > div > div > button.buttons.btn-close"));
        webElement.click();

        //다음
        webElement = driver.findElement(By.id("btn-submit"));
        webElement.click();
    }


}
