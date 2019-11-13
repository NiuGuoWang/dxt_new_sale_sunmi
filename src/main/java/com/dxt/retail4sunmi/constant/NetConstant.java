package com.dxt.retail4sunmi.constant;

/**
 * @author star
 * @date 2017/10/10 0010
 */

public class NetConstant {

        public static final String BASE_URL = "http://123.196.123.106/svc-xls/a/";//测试服务器
//        public static final String BASE_URL = "http://192.168.4.207:8080/svc-xls/a/";//孙梦启服务器
//    public static final String BASE_URL = "http://47.93.227.52//svc-xls/a/";//正式服务器
    //    public static final String BASE_URL = "http://192.168.4.54:8088/svc-xls/a/";//刘娈莎
//    public static final String BASE_URL = "http://192.168.4.70:8080/svc-xls/a/";//郑涵
    public static final String APP_UPDATE = "app_isUpdate.action";
    public static final String URL_LOGIN = "login";
    public final static String URL_SEARCH_PRODUCT = "basics/product/product/list";
    public final static String URL_SEARCH_ORDER = "retail/retailOrder/list";
    public final static String URL_SEARCH_ORDERINFO = "retail/retailOrder/findOrderInfo";
    public final static String URL_ORDER_SUBMIT = "retail/retailOrder/saveOrderInfo";
    public final static String URL_ORDER_PAY = "retail/retailOrder/payOrder";
    public final static String URL_REFUND = "retail/retailOrder/salesReturn";
    public final static String URL_UPDATE = "getAppUpdateInfo";
    public final static String URL_GET_MEMBER_INFO = "member/member/getMemberByMobile";
    public final static String URL_ADD_MEMBER = "/svc-xls/a/member/member/save";
    public final static String QUERY_ORDER_STATUS = "retail/retailOrder/selectOrderStatus";

    //查询模板接口
    public final static String TEMPLATE_SEARCH = "retail/retailOrder/findTemplate";

}
