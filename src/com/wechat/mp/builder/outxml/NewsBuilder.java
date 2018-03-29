package com.wechat.mp.builder.outxml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.wechat.mp.bean.message.WxMpXmlOutNewsMessage;

/**
 * 图文消息builder
 */
public final class NewsBuilder extends BaseBuilder<NewsBuilder, WxMpXmlOutNewsMessage> {
  private List<WxMpXmlOutNewsMessage.Item> articles = new ArrayList<>();

  public NewsBuilder addArticle(WxMpXmlOutNewsMessage.Item... items) {
    Collections.addAll(this.articles, items);
    return this;
  }

  public NewsBuilder articles(List<WxMpXmlOutNewsMessage.Item> articles){
    this.articles = articles;
    return this;
  }

  @Override
  public WxMpXmlOutNewsMessage build() {
    WxMpXmlOutNewsMessage m = new WxMpXmlOutNewsMessage();
    for (WxMpXmlOutNewsMessage.Item item : this.articles) {
      m.addArticle(item);
    }
    setCommon(m);
    return m;
  }

}
