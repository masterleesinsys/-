package com.wechat.mp.util.json;

import com.google.gson.*;
import com.wechat.common.util.json.GsonHelper;
import com.wechat.mp.bean.WxMpCard;

import java.lang.reflect.Type;

/**
 * @version 15/11/11
 */
public class WxMpCardGsonAdapter implements JsonDeserializer<WxMpCard> {

  @Override
  public WxMpCard deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext
    jsonDeserializationContext) throws JsonParseException {
    WxMpCard card = new WxMpCard();
    JsonObject jsonObject = jsonElement.getAsJsonObject();

    card.setCardId(GsonHelper.getString(jsonObject, "card_id"));
    card.setBeginTime(GsonHelper.getLong(jsonObject, "begin_time"));
    card.setEndTime(GsonHelper.getLong(jsonObject, "end_time"));

    return card;
  }

}
