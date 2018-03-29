package com.wechat.mp.builder.kefu;

import com.wechat.common.api.WxConsts;
import com.wechat.mp.bean.kefu.WxMpKefuMessage;

/**
 * 卡券消息builder
 * <pre>
 * 用法: WxMpKefuMessage m = WxMpKefuMessage.WXCARD().cardId(...).toUser(...).build();
 * </pre>
 */
public final class WxCardBuilder extends BaseBuilder<WxCardBuilder> {
  private String cardId;

  public WxCardBuilder() {
    this.msgType = WxConsts.KefuMsgType.WXCARD;
  }

  public WxCardBuilder cardId(String cardId) {
    this.cardId = cardId;
    return this;
  }

  @Override
  public WxMpKefuMessage build() {
    WxMpKefuMessage m = super.build();
    m.setCardId(this.cardId);
    return m;
  }
}
