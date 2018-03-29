package com.wechat.mp.bean;


import lombok.Data;

import java.io.Serializable;

import com.wechat.common.util.ToStringUtils;

/**
 * 微信卡券
 * @version 15/11/11
 */
@Data
public class WxMpCard implements Serializable{
  private static final long serialVersionUID = 9214301870017772921L;

  private String cardId;

  private Long beginTime;

  private Long endTime;

  private String userCardStatus;

  private Boolean canConsume;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }
}
