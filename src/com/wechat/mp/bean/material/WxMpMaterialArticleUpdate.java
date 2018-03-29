package com.wechat.mp.bean.material;

import lombok.Data;

import java.io.Serializable;

import com.wechat.mp.util.json.WxMpGsonBuilder;

@Data
public class WxMpMaterialArticleUpdate implements Serializable {
  private static final long serialVersionUID = -7611963949517780270L;

  private String mediaId;
  private int index;
  private WxMpMaterialNews.WxMpMaterialNewsArticle articles;

  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
