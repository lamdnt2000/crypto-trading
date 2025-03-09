package com.lamdnt.cryptotrading.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "scheduler.task.fetch-symbols-price")
@NoArgsConstructor
public class FetchSymbolsPriceSchedulerProperties {
  private String cron;
  private String name;
  private LockConfig lock;

  @Getter
  @Setter
  @NoArgsConstructor
  public static class LockConfig {
    private String atMostFor;
    private String atLeastFor;
  }
}
