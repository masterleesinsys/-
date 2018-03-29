package com.wechat.common.util;

import com.wechat.common.api.WxErrorExceptionHandler;
import com.wechat.common.exception.WxErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogExceptionHandler implements WxErrorExceptionHandler {

  private Logger log = LoggerFactory.getLogger(WxErrorExceptionHandler.class);

  @Override
  public void handle(WxErrorException e) {

    this.log.error("Error happens", e);

  }

}
