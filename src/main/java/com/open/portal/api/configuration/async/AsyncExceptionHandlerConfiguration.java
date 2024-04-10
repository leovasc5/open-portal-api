package com.open.portal.api.configuration.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

@Slf4j
public class AsyncExceptionHandlerConfiguration implements AsyncUncaughtExceptionHandler {

  @Override
  public void handleUncaughtException(Throwable exception, Method method, Object... params) {

    log.error("[ASYNC EXCEPTION] - {}", exception.getMessage());
    exception.printStackTrace();
    log.error("[METHOD] - {}", method.getName());

    for (Object param : params) {
      log.error("[PARAM] - {}", param.toString());
    }
  }
}