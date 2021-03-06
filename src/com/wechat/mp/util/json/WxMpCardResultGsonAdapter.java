package com.wechat.mp.util.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.wechat.common.util.json.GsonHelper;
import com.wechat.mp.bean.WxMpCard;
import com.wechat.mp.bean.result.WxMpCardResult;

import java.lang.reflect.Type;

/**
 * @version 15/11/11
 */
public class WxMpCardResultGsonAdapter implements JsonDeserializer<WxMpCardResult> {
  @Override
  public WxMpCardResult deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxMpCardResult cardResult = new WxMpCardResult();
    JsonObject jsonObject = jsonElement.getAsJsonObject();

    cardResult.setOpenId(GsonHelper.getString(jsonObject, "openid"));
    cardResult.setErrorCode(GsonHelper.getString(jsonObject, "errcode"));
    cardResult.setErrorMsg(GsonHelper.getString(jsonObject, "errmsg"));
    cardResult.setCanConsume(GsonHelper.getBoolean(jsonObject, "can_consume"));
    cardResult.setUserCardStatus(GsonHelper.getString(jsonObject, "user_card_status"));

    WxMpCard card = WxMpGsonBuilder.INSTANCE.create().fromJson(jsonObject.get("card"),
      new TypeToken<WxMpCard>() {
      }.getType());

    cardResult.setCard(card);

    return cardResult;
  }
}
