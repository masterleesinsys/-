package com.wechat.common.api;

import com.wechat.common.exception.WxErrorException;

/**
 * WxErrorException处理器.
 */
public interface WxErrorExceptionHandler {

  void handle(WxErrorException e);

}
