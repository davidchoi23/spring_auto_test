package com.david.spring_auto_test.config;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Slf4j
@Configuration
public class ChromeDriverConfig {

    @PostConstruct
    public void postConstruct() {
        // 현재 package의 workspace 경로, Windows는 [ chromedriver.exe ]
        Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/chromedriver");  // 현재 package의
        // WebDriver 경로 설정
        System.setProperty("webdriver.chrome.driver", path.toString());
        //System.setProperty("webdriver.chrome.verboseLogging", "true");
        log.info("webdriver.chrome.driver : {}", path.toString());
    }

    @Bean
    public ChromeDriver chromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");            // 전체화면으로 실행
        options.addArguments("--disable-popup-blocking");    // 팝업 무시
        options.addArguments("--disable-default-apps");     // 기본앱 사용안함

        return new ChromeDriver(options);
    }
}
