package com.dxt.retail4sunmi.api;


import com.dxt.retail4sunmi.constant.NetConstant;
import com.dxt.retail4sunmi.entity.BaseResult;
import com.dxt.retail4sunmi.entity.QueryMemberBean;
import com.dxt.retail4sunmi.entity.QueyOrderStateBean;
import com.dxt.retail4sunmi.entity.RigisterMemberBean;
import com.dxt.retail4sunmi.entity.SearchGoodsBean;
import com.dxt.retail4sunmi.entity.SearchOrderBean;
import com.dxt.retail4sunmi.entity.SubmitOrderBean;
import com.dxt.retail4sunmi.entity.UpdateBean;
import com.dxt.retail4sunmi.entity.UserBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by allen on 2016/12/26.
 */

public interface ApiService {


    //登陆
    @FormUrlEncoded
    @POST(NetConstant.URL_LOGIN)
    Observable<UserBean> login(@Field("data") String data);

    //产品搜索
    @FormUrlEncoded
    @POST(NetConstant.URL_SEARCH_PRODUCT)
    Observable<SearchGoodsBean> searchProduct(@Field("data") String data);
    //订单查询
    @FormUrlEncoded
    @POST(NetConstant.URL_SEARCH_ORDER)
    Observable<SearchOrderBean> searchOrder(@Field("data") String data);

    //查询订单详情
    @FormUrlEncoded
    @POST(NetConstant.URL_SEARCH_ORDERINFO)
    Observable<SubmitOrderBean> searchOrderInfo(@Field("data") String data);

    //提交订单
   @FormUrlEncoded
    @POST(NetConstant.URL_ORDER_SUBMIT)
    Observable<SubmitOrderBean> orderSubmit(@Field("data") String data);
    //确认付款
    @FormUrlEncoded
    @POST(NetConstant.URL_ORDER_PAY)
    Observable<BaseResult> orderPay(@Field("data") String data);


    //获取会员信息
    @FormUrlEncoded
    @POST(NetConstant.URL_GET_MEMBER_INFO)
    Observable<QueryMemberBean> getMemberInfo(@Field("data") String data);

    //添加会员
    @FormUrlEncoded
    @POST(NetConstant.URL_ADD_MEMBER)
    Observable<RigisterMemberBean> addMember(@Field("data") String data);
    //保存退款信息
    @FormUrlEncoded
    @POST(NetConstant.URL_REFUND)
    Observable<BaseResult> refund(@Field("data") String data);

    //APP更新
    @FormUrlEncoded
    @POST(NetConstant.URL_UPDATE)
    Observable<UpdateBean> getUpdate(@Field("data") String data);

    //查询收款状态
    @FormUrlEncoded
    @POST(NetConstant.QUERY_ORDER_STATUS)
    Observable<QueyOrderStateBean> queryOrderState(@Field("data") String data);

    //模板搜索
    @FormUrlEncoded
    @POST(NetConstant.TEMPLATE_SEARCH)
    Observable<String> queryTemplateSearch(@Field("data") String data);


}
