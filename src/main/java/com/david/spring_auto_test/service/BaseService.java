package com.david.spring_auto_test.service;

import com.david.spring_auto_test.config.AppConfig;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;


public abstract class BaseService<D extends ChromeDriver, A extends AppConfig> {

    D driver;
    A appConfig;

    @Autowired
    public final void setDriver(D driver) {
        this.driver = driver;
    }

    @Autowired
    public final void setAppConfig(A appConfig) {
        this.appConfig = appConfig;
    }

    @PreDestroy
    private void destroy() {
        driver.quit();
    }

    protected abstract void run();

    public void exec() {
        init();
        run();
        fini();
    }

    private void init() {
        // 실행하려는 코드 작성
        driver.getLocalStorage();
        // 빈 탭 생성
        driver.executeScript("window.open('about:blank','_blank');");
    }

    private void fini() {

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        // 탭 종료
        driver.close();
    }


    protected void runLogin() {
        // 로고를 클릭해서 메인 화면 이동
        WebElement webElement = driver.findElement(By.cssSelector("body > div:nth-child(2) > header > div > h1 > a"));
        webElement.click();

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
        webElement.sendKeys(appConfig.getLoginId());

        //iframe 내부에서 pw 필드 탐색
        webElement = driver.findElement(By.name("password"));
        webElement.sendKeys(appConfig.getLoginPw());


        webElement = driver.findElement(By.id("btn-login"));
        webElement.click();
    }
}
