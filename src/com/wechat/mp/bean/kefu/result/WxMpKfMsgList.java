package com.wechat.mp.bean.kefu.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import com.wechat.common.util.ToStringUtils;
import com.wechat.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @date 2016/7/15
 */
@Data
public class WxMpKfMsgList implements Serializable {
  private static final long serialVersionUID = 4524296707435188202L;

  @SerializedName("recordlist")
  private List<WxMpKfMsgRecord> records;

  @SerializedName("number")
  private Integer number;

  @SerializedName("msgid")
  private Long msgId;

  public static WxMpKfMsgList fromJson(String responseContent) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(responseContent, WxMpKfMsgList.class);
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }
}
