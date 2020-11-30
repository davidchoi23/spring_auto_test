package com.david.spring_auto_test;

import com.david.spring_auto_test.service.CorridoreAUToKOService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationExecRunner implements ApplicationRunner {

  private final CorridoreAUToKOService corridoreAUToKOService;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    corridoreAUToKOService.exec();
    log.info("Run!");
  }
}
