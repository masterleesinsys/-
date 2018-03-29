package com.wechat.mp.api;

import com.wechat.mp.bean.datacube.*;
import com.wechat.common.exception.WxErrorException;
import com.wechat.mp.bean.datacube.WxDataCubeArticleResult;
import com.wechat.mp.bean.datacube.WxDataCubeArticleTotal;
import com.wechat.mp.bean.datacube.WxDataCubeInterfaceResult;
import com.wechat.mp.bean.datacube.WxDataCubeMsgResult;
import com.wechat.mp.bean.datacube.WxDataCubeUserCumulate;
import com.wechat.mp.bean.datacube.WxDataCubeUserSummary;

import java.util.Date;
import java.util.List;

/**
 * 统计分析相关接口
 */
public interface WxMpDataCubeService {
  String GET_USER_SUMMARY = "https://api.weixin.qq.com/datacube/getusersummary";
  String GET_USER_CUMULATE = "https://api.weixin.qq.com/datacube/getusercumulate";
  String GET_ARTICLE_SUMMARY = "https://api.weixin.qq.com/datacube/getarticlesummary";
  String GET_ARTICLE_TOTAL = "https://api.weixin.qq.com/datacube/getarticletotal";
  String GET_USER_READ = "https://api.weixin.qq.com/datacube/getuserread";
  String GET_USER_READ_HOUR = "https://api.weixin.qq.com/datacube/getuserreadhour";
  String GET_USER_SHARE = "https://api.weixin.qq.com/datacube/getusershare";
  String GET_USER_SHARE_HOUR = "https://api.weixin.qq.com/datacube/getusersharehour";
  String GET_UPSTREAM_MSG = "https://api.weixin.qq.com/datacube/getupstreammsg";
  String GET_UPSTREAM_MSG_HOUR = "https://api.weixin.qq.com/datacube/getupstreammsghour";
  String GET_UPSTREAM_MSG_WEEK = "https://api.weixin.qq.com/datacube/getupstreammsgweek";
  String GET_UPSTREAM_MSG_MONTH = "https://api.weixin.qq.com/datacube/getupstreammsgmonth";
  String GET_UPSTREAM_MSG_DIST = "https://api.weixin.qq.com/datacube/getupstreammsgdist";
  String GET_UPSTREAM_MSG_DIST_WEEK = "https://api.weixin.qq.com/datacube/getupstreammsgdistweek";
  String GET_UPSTREAM_MSG_DIST_MONTH = "https://api.weixin.qq.com/datacube/getupstreammsgdistmonth";
  String GET_INTERFACE_SUMMARY = "https://api.weixin.qq.com/datacube/getinterfacesummary";
  String GET_INTERFACE_SUMMARY_HOUR = "https://api.weixin.qq.com/datacube/getinterfacesummaryhour";

  //*******************用户分析数据接口***********************//

  /**
   * <pre>
   * 获取用户增减数据
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141082&token=&lang=zh_CN">用户分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getusersummary?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度7天，endDate不能早于begingDate
   */
  List<WxDataCubeUserSummary> getUserSummary(Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取累计用户数据
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141082&token=&lang=zh_CN">用户分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getusercumulate?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度7天，endDate不能早于begingDate
   */
  List<WxDataCubeUserCumulate> getUserCumulate(Date beginDate, Date endDate) throws WxErrorException;

  //*******************图文分析数据接口***********************//

  /**
   * <pre>
   * 获取图文群发每日数据（getarticlesummary）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getarticlesummary?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度1天，endDate不能早于begingDate
   */
  List<WxDataCubeArticleResult> getArticleSummary(Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取图文群发总数据（getarticletotal）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getarticletotal?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度1天，endDate不能早于begingDate
   */
  List<WxDataCubeArticleTotal> getArticleTotal(Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取图文统计数据（getuserread）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getuserread?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度3天，endDate不能早于begingDate
   */
  List<WxDataCubeArticleResult> getUserRead(Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取图文统计分时数据（getuserreadhour）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getuserreadhour?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度1天，endDate不能早于begingDate
   */
  List<WxDataCubeArticleResult> getUserReadHour(Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取图文分享转发数据（getusershare）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getusershare?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度7天，endDate不能早于begingDate
   */
  List<WxDataCubeArticleResult> getUserShare(Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取图文分享转发分时数据（getusersharehour）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141084&token=&lang=zh_CN">图文分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getusersharehour?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度1天，endDate不能早于begingDate
   */
  List<WxDataCubeArticleResult> getUserShareHour(Date beginDate, Date endDate) throws WxErrorException;

  //*******************消息分析数据接口***********************//

  /**
   * <pre>
   * 获取消息发送概况数据（getupstreammsg）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsg?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度7天，endDate不能早于begingDate
   */
  List<WxDataCubeMsgResult> getUpstreamMsg(Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取消息分送分时数据（getupstreammsghour）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsghour?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度1天，endDate不能早于begingDate
   */
  List<WxDataCubeMsgResult> getUpstreamMsgHour(Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取消息发送周数据（getupstreammsgweek）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsgweek?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度30天，endDate不能早于begingDate
   */
  List<WxDataCubeMsgResult> getUpstreamMsgWeek(Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取消息发送月数据（getupstreammsgmonth）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsgmonth?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度30天，endDate不能早于begingDate
   */
  List<WxDataCubeMsgResult> getUpstreamMsgMonth(Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取消息发送分布数据（getupstreammsgdist）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsgdist?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度15天，endDate不能早于begingDate
   */
  List<WxDataCubeMsgResult> getUpstreamMsgDist(Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取消息发送分布周数据（getupstreammsgdistweek）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsgdistweek?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度30天，endDate不能早于begingDate
   */
  List<WxDataCubeMsgResult> getUpstreamMsgDistWeek(Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取消息发送分布月数据（getupstreammsgdistmonth）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141085&token=&lang=zh_CN">消息分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getupstreammsgdistmonth?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度30天，endDate不能早于begingDate
   */
  List<WxDataCubeMsgResult> getUpstreamMsgDistMonth(Date beginDate, Date endDate) throws WxErrorException;

  //*******************接口分析数据接口***********************//

  /**
   * <pre>
   * 获取接口分析数据（getinterfacesummary）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141086&token=&lang=zh_CN">接口分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getinterfacesummary?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度30天，endDate不能早于begingDate
   */
  List<WxDataCubeInterfaceResult> getInterfaceSummary(Date beginDate, Date endDate) throws WxErrorException;

  /**
   * <pre>
   * 获取接口分析分时数据（getinterfacesummaryhour）
   * 详情请见文档：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141086&token=&lang=zh_CN">接口分析数据接口</a>
   * 接口url格式：https://api.weixin.qq.com/datacube/getinterfacesummaryhour?access_token=ACCESS_TOKEN
   *
   * @param beginDate 开始时间
   * @param endDate   最大时间跨度1天，endDate不能早于begingDate
   */
  List<WxDataCubeInterfaceResult> getInterfaceSummaryHour(Date beginDate, Date endDate) throws WxErrorException;

}
