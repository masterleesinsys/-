package com.wechat.mp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.wechat.common.util.ToStringUtils;
import com.wechat.common.util.xml.XStreamCDataConverter;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XStreamAlias("SendPicsInfo")
@Data
public class SendPicsInfo implements Serializable {
  private static final long serialVersionUID = -4572837013294199227L;

  @XStreamAlias("PicList")
  protected final List<Item> picList = new ArrayList<>();

  @XStreamAlias("Count")
  private Long count;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  @XStreamAlias("item")
  @Data
  public static class Item implements Serializable {
    private static final long serialVersionUID = 7706235740094081194L;

    @XStreamAlias("PicMd5Sum")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String picMd5Sum;

    @Override
    public String toString() {
      return ToStringUtils.toSimpleString(this);
    }

  }
}
