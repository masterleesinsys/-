package com.wechat.mp.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wechat.mp.bean.*;
import com.wechat.mp.bean.material.*;
import com.wechat.mp.bean.result.*;
import com.wechat.mp.bean.WxMpCard;
import com.wechat.mp.bean.WxMpMassNews;
import com.wechat.mp.bean.WxMpMassOpenIdsMessage;
import com.wechat.mp.bean.WxMpMassPreviewMessage;
import com.wechat.mp.bean.WxMpMassTagMessage;
import com.wechat.mp.bean.WxMpMassVideo;
import com.wechat.mp.bean.datacube.WxDataCubeUserCumulate;
import com.wechat.mp.bean.datacube.WxDataCubeUserSummary;
import com.wechat.mp.bean.kefu.WxMpKefuMessage;
import com.wechat.mp.bean.material.WxMediaImgUploadResult;
import com.wechat.mp.bean.material.WxMpMaterialArticleUpdate;
import com.wechat.mp.bean.material.WxMpMaterialCountResult;
import com.wechat.mp.bean.material.WxMpMaterialFileBatchGetResult;
import com.wechat.mp.bean.material.WxMpMaterialNews;
import com.wechat.mp.bean.material.WxMpMaterialNewsBatchGetResult;
import com.wechat.mp.bean.material.WxMpMaterialUploadResult;
import com.wechat.mp.bean.material.WxMpMaterialVideoInfoResult;
import com.wechat.mp.bean.membercard.WxMpMemberCardUpdateResult;
import com.wechat.mp.bean.membercard.WxMpMemberCardUserInfoResult;
import com.wechat.mp.bean.result.WxMpCardResult;
import com.wechat.mp.bean.result.WxMpMassSendResult;
import com.wechat.mp.bean.result.WxMpMassUploadResult;
import com.wechat.mp.bean.result.WxMpOAuth2AccessToken;
import com.wechat.mp.bean.result.WxMpQrCodeTicket;
import com.wechat.mp.bean.result.WxMpSemanticQueryResult;
import com.wechat.mp.bean.result.WxMpUser;
import com.wechat.mp.bean.result.WxMpUserBlacklistGetResult;
import com.wechat.mp.bean.result.WxMpUserList;
import com.wechat.mp.bean.template.WxMpTemplateIndustry;
import com.wechat.mp.bean.template.WxMpTemplateMessage;

public class WxMpGsonBuilder {

  public static final GsonBuilder INSTANCE = new GsonBuilder();

  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(WxMpKefuMessage.class, new WxMpKefuMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassNews.class, new WxMpMassNewsGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassTagMessage.class, new WxMpMassTagMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassOpenIdsMessage.class, new WxMpMassOpenIdsMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpUser.class, new WxMpUserGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpUserList.class, new WxUserListGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassVideo.class, new WxMpMassVideoAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassSendResult.class, new WxMpMassSendResultAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassUploadResult.class, new WxMpMassUploadResultAdapter());
    INSTANCE.registerTypeAdapter(WxMpQrCodeTicket.class, new WxQrCodeTicketAdapter());
    INSTANCE.registerTypeAdapter(WxMpTemplateMessage.class, new WxMpTemplateMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpSemanticQueryResult.class, new WxMpSemanticQueryResultAdapter());
    INSTANCE.registerTypeAdapter(WxMpOAuth2AccessToken.class, new WxMpOAuth2AccessTokenAdapter());
    INSTANCE.registerTypeAdapter(WxDataCubeUserSummary.class, new WxMpUserSummaryGsonAdapter());
    INSTANCE.registerTypeAdapter(WxDataCubeUserCumulate.class, new WxMpUserCumulateGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMaterialUploadResult.class, new WxMpMaterialUploadResultAdapter());
    INSTANCE.registerTypeAdapter(WxMpMaterialVideoInfoResult.class, new WxMpMaterialVideoInfoResultAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassNews.WxMpMassNewsArticle.class, new WxMpMassNewsArticleGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMaterialArticleUpdate.class, new WxMpMaterialArticleUpdateGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMaterialCountResult.class, new WxMpMaterialCountResultAdapter());
    INSTANCE.registerTypeAdapter(WxMpMaterialNews.class, new WxMpMaterialNewsGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMaterialNews.WxMpMaterialNewsArticle.class, new WxMpMaterialNewsArticleGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMaterialNewsBatchGetResult.class, new WxMpMaterialNewsBatchGetGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMaterialNewsBatchGetResult.WxMaterialNewsBatchGetNewsItem.class, new WxMpMaterialNewsBatchGetGsonItemAdapter());
    INSTANCE.registerTypeAdapter(WxMpMaterialFileBatchGetResult.class, new WxMpMaterialFileBatchGetGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMaterialFileBatchGetResult.WxMaterialFileBatchGetNewsItem.class, new WxMpMaterialFileBatchGetGsonItemAdapter());
    INSTANCE.registerTypeAdapter(WxMpCardResult.class, new WxMpCardResultGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpCard.class, new WxMpCardGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMassPreviewMessage.class, new WxMpMassPreviewMessageGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMediaImgUploadResult.class, new WxMediaImgUploadResultGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpTemplateIndustry.class, new WxMpIndustryGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpUserBlacklistGetResult.class, new WxUserBlacklistGetResultGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMemberCardUserInfoResult.class, new WxMpMemberCardUserInfoResultGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMpMemberCardUpdateResult.class, new WxMpMemberCardUpdateResultGsonAdapter());
  }

  public static Gson create() {
    return INSTANCE.create();
  }

}
