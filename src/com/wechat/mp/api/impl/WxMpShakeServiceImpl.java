package com.wechat.mp.api.impl;

import com.wechat.mp.bean.shake.*;
import com.wechat.common.bean.result.WxError;
import com.wechat.common.exception.WxErrorException;
import com.wechat.mp.api.WxMpService;
import com.wechat.mp.api.WxMpShakeService;
import com.wechat.mp.bean.WxMpShakeInfoResult;
import com.wechat.mp.bean.WxMpShakeQuery;
import com.wechat.mp.bean.shake.WxMpShakeAroundDeviceBindPageQuery;
import com.wechat.mp.bean.shake.WxMpShakeAroundPageAddQuery;
import com.wechat.mp.bean.shake.WxMpShakeAroundPageAddResult;
import com.wechat.mp.bean.shake.WxMpShakeAroundRelationSearchQuery;
import com.wechat.mp.bean.shake.WxMpShakeAroundRelationSearchResult;

/**
 * Created on 2017/6/5.
 */
public class WxMpShakeServiceImpl implements WxMpShakeService {

  private WxMpService wxMpService;

  public WxMpShakeServiceImpl(WxMpService wxMpService) {
    this.wxMpService = wxMpService;
  }

  /**
   * <pre>
   * 获取设备及用户信息<br/>
   * 获取设备信息，包括UUID、major、minor，以及距离、openID等信息。
   * 详情请见: https://mp.weixin.qq.com/wiki?action=doc&id=mp1443447963
   * http请求方式: POST（请使用https协议）
   * 接口地址：https://api.weixin.qq.com/shakearound/user/getshakeinfo?access_token=ACCESS_TOKE
   * </pre>
   *
   * @param wxMpShakeQuery 查询参数
   */
  @Override
  public WxMpShakeInfoResult getShakeInfo(WxMpShakeQuery wxMpShakeQuery) throws WxErrorException {
    String url = "https://api.weixin.qq.com/shakearound/user/getshakeinfo";
    String postData = wxMpShakeQuery.toJsonString();
    String responseContent = this.wxMpService.post(url, postData);
    return WxMpShakeInfoResult.fromJson(responseContent);
  }

  @Override
  public WxMpShakeAroundPageAddResult pageAdd(WxMpShakeAroundPageAddQuery shakeAroundPageAddQuery) throws WxErrorException {
    String url = "https://api.weixin.qq.com/shakearound/page/add";
    String postData = shakeAroundPageAddQuery.toJsonString();
    String responseContent = this.wxMpService.post(url, postData);
    return WxMpShakeAroundPageAddResult.fromJson(responseContent);
  }

  @Override
  public WxError deviceBindPageQuery(WxMpShakeAroundDeviceBindPageQuery shakeAroundDeviceBindPageQuery) throws WxErrorException {
    String url = "https://api.weixin.qq.com/shakearound/device/bindpage";
    String postData = shakeAroundDeviceBindPageQuery.toJsonString();
    String responseContent = this.wxMpService.post(url, postData);
    return WxError.fromJson(responseContent);
  }

  @Override
  public WxMpShakeAroundRelationSearchResult relationSearch(WxMpShakeAroundRelationSearchQuery shakeAroundRelationSearchQuery) throws WxErrorException {
    String url = "https://api.weixin.qq.com/shakearound/relation/search";
    String postData = shakeAroundRelationSearchQuery.toJsonString();
    String responseContent = this.wxMpService.post(url, postData);
    return WxMpShakeAroundRelationSearchResult.fromJson(responseContent);
  }
}
