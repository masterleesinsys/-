package com.wechat.mp.bean.membercard;

import lombok.Data;

import com.wechat.common.util.ToStringUtils;
import com.wechat.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * <pre>
 * 用于 `7 更新会员信息` 的接口调用后的返回结果
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1451025283
 * </pre>
 * @version 2017/7/15
 */
@Data
public class WxMpMemberCardUpdateResult implements Serializable {

  private static final long serialVersionUID = 9084886191442098311L;

  private String errorCode;

  private String errorMsg;

  private String openId;

  private Integer resultBonus;

  private Integer resultBalance;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public static WxMpMemberCardUpdateResult fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxMpMemberCardUpdateResult.class);
  }
}
