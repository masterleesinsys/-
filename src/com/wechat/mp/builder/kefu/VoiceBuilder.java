package com.wechat.mp.builder.kefu;

import com.wechat.common.api.WxConsts;
import com.wechat.mp.bean.kefu.WxMpKefuMessage;

/**
 * 语音消息builder
 * <pre>
 * 用法: WxMpKefuMessage m = WxMpKefuMessage.VOICE().mediaId(...).toUser(...).build();
 * </pre>
 */
public final class VoiceBuilder extends BaseBuilder<VoiceBuilder> {
  private String mediaId;

  public VoiceBuilder() {
    this.msgType = WxConsts.KefuMsgType.VOICE;
  }

  public VoiceBuilder mediaId(String media_id) {
    this.mediaId = media_id;
    return this;
  }

  @Override
  public WxMpKefuMessage build() {
    WxMpKefuMessage m = super.build();
    m.setMediaId(this.mediaId);
    return m;
  }
}
