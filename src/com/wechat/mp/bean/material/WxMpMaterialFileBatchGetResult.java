package com.wechat.mp.bean.material;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.wechat.common.util.ToStringUtils;

@Data
public class WxMpMaterialFileBatchGetResult implements Serializable {
  private static final long serialVersionUID = -560388368297267884L;

  private int totalCount;
  private int itemCount;
  private List<WxMaterialFileBatchGetNewsItem> items;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  @Data
  public static class WxMaterialFileBatchGetNewsItem {
    private String mediaId;
    private Date updateTime;
    private String name;
    private String url;

    @Override
    public String toString() {
      return ToStringUtils.toSimpleString(this);
    }
  }
}
