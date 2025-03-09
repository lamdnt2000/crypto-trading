package com.lamdnt.cryptotrading.utils;

import java.util.function.Supplier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionHelper {

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public <T> T newTransaction(Supplier<T> supplier) {
    return supplier.get();
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void newTransaction(Runnable runnable) {
    runnable.run();
  }
}
