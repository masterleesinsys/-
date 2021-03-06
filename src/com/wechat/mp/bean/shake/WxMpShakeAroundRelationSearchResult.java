package com.wechat.mp.bean.shake;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

import com.wechat.mp.util.json.WxMpGsonBuilder;

@Data
public class WxMpShakeAroundRelationSearchResult implements Serializable {
  private Integer errcode;
  private String errmsg;
  private WxMpShakeAcoundRelationSearch data;
  public static WxMpShakeAroundRelationSearchResult fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxMpShakeAroundRelationSearchResult.class);
  }
  @Data
  public static class WxMpShakeAcoundRelationSearch implements Serializable{
    private List<WxMpDeviceIdentifier> relations;
    private Integer total_count;
  }
}
