/*
 * KINGSTAR MEDIA SOLUTIONS Co.,LTD. Copyright c 2005-2013. All rights reserved.
 *
 * This source code is the property of KINGSTAR MEDIA SOLUTIONS LTD. It is intended
 * only for the use of KINGSTAR MEDIA application development. Reengineering, reproduction
 * arose from modification of the original source, or other redistribution of this source
 * is not permitted without written permission of the KINGSTAR MEDIA SOLUTIONS LTD.
 */
package com.wechat.mp.util.json;

import com.google.gson.*;
import com.wechat.common.api.WxConsts;
import com.wechat.mp.bean.kefu.WxMpKefuMessage;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;

public class WxMpKefuMessageGsonAdapter implements JsonSerializer<WxMpKefuMessage> {

  @Override
  public JsonElement serialize(WxMpKefuMessage message, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject messageJson = new JsonObject();
    messageJson.addProperty("touser", message.getToUser());
    messageJson.addProperty("msgtype", message.getMsgType());

    if (WxConsts.KefuMsgType.TEXT.equals(message.getMsgType())) {
      JsonObject text = new JsonObject();
      text.addProperty("content", message.getContent());
      messageJson.add("text", text);
    }

    if (WxConsts.KefuMsgType.IMAGE.equals(message.getMsgType())) {
      JsonObject image = new JsonObject();
      image.addProperty("media_id", message.getMediaId());
      messageJson.add("image", image);
    }

    if (WxConsts.KefuMsgType.VOICE.equals(message.getMsgType())) {
      JsonObject voice = new JsonObject();
      voice.addProperty("media_id", message.getMediaId());
      messageJson.add("voice", voice);
    }

    if (WxConsts.KefuMsgType.VIDEO.equals(message.getMsgType())) {
      JsonObject video = new JsonObject();
      video.addProperty("media_id", message.getMediaId());
      video.addProperty("thumb_media_id", message.getThumbMediaId());
      video.addProperty("title", message.getTitle());
      video.addProperty("description", message.getDescription());
      messageJson.add("video", video);
    }

    if (WxConsts.KefuMsgType.MUSIC.equals(message.getMsgType())) {
      JsonObject music = new JsonObject();
      music.addProperty("title", message.getTitle());
      music.addProperty("description", message.getDescription());
      music.addProperty("thumb_media_id", message.getThumbMediaId());
      music.addProperty("musicurl", message.getMusicUrl());
      music.addProperty("hqmusicurl", message.getHqMusicUrl());
      messageJson.add("music", music);
    }

    if (WxConsts.KefuMsgType.NEWS.equals(message.getMsgType())) {
      JsonObject newsJsonObject = new JsonObject();
      JsonArray articleJsonArray = new JsonArray();
      for (WxMpKefuMessage.WxArticle article : message.getArticles()) {
        JsonObject articleJson = new JsonObject();
        articleJson.addProperty("title", article.getTitle());
        articleJson.addProperty("description", article.getDescription());
        articleJson.addProperty("url", article.getUrl());
        articleJson.addProperty("picurl", article.getPicUrl());
        articleJsonArray.add(articleJson);
      }
      newsJsonObject.add("articles", articleJsonArray);
      messageJson.add("news", newsJsonObject);
    }

    if (WxConsts.KefuMsgType.MPNEWS.equals(message.getMsgType())) {
      JsonObject json = new JsonObject();
      json.addProperty("media_id", message.getMpNewsMediaId());
      messageJson.add("mpnews", json);
    }

    if (WxConsts.KefuMsgType.WXCARD.equals(message.getMsgType())) {
      JsonObject wxcard = new JsonObject();
      wxcard.addProperty("card_id", message.getCardId());
      messageJson.add("wxcard", wxcard);
    }

    if (StringUtils.isNotBlank(message.getKfAccount())) {
      JsonObject newsJsonObject = new JsonObject();
      newsJsonObject.addProperty("kf_account", message.getKfAccount());
      messageJson.add("customservice", newsJsonObject);
    }

    return messageJson;
  }

}
