package com.wechat.mp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.wechat.common.api.WxConsts;
import com.wechat.common.util.xml.XStreamMediaIdConverter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMpXmlOutVoiceMessage extends WxMpXmlOutMessage {
  private static final long serialVersionUID = 240367390249860551L;

  @XStreamAlias("Voice")
  @XStreamConverter(value = XStreamMediaIdConverter.class)
  private String mediaId;

  public WxMpXmlOutVoiceMessage() {
    this.msgType = WxConsts.XmlMsgType.VOICE;
  }

}
