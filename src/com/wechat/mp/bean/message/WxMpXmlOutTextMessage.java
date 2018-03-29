package com.wechat.mp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.wechat.common.api.WxConsts;
import com.wechat.common.util.xml.XStreamCDataConverter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = true)
public class WxMpXmlOutTextMessage extends WxMpXmlOutMessage {
  private static final long serialVersionUID = -3972786455288763361L;

  @XStreamAlias("Content")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String content;

  public WxMpXmlOutTextMessage() {
    this.msgType = WxConsts.XmlMsgType.TEXT;
  }

}
