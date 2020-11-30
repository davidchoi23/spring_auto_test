package com.david.spring_auto_test.service;

import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;


public abstract class BaseService <D extends ChromeDriver> {

    D driver;

    public  BaseService(D driver) {
        this.driver = driver;
    }

    @PreDestroy
    private void destroy(){
        driver.quit();
    }

    private void init(){
        // 실행하려는 코드 작성
        driver.getLocalStorage();
        // 빈 탭 생성
        driver.executeScript("window.open('about:blank','_blank');");
    }

    private void fini(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // 탭 종료
        driver.close();
    }

    protected abstract void run();

    public void exec() {
        init();
        run();
        fini();
    }
}
