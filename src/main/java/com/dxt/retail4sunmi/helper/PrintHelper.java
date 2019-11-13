package com.dxt.retail4sunmi.helper;

import android.os.RemoteException;

import com.dxt.retail4sunmi.DxtSaleApplication;
import com.dxt.retail4sunmi.entity.InfoListBean;
import com.dxt.retail4sunmi.entity.SubmitOrderBean;
import com.dxt.retail4sunmi.util.DateUtils;
import com.dxt.retail4sunmi.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import woyou.aidlservice.jiuiv5.ICallback;
import woyou.aidlservice.jiuiv5.IWoyouService;

/**
 * star on 2018/4/23 0023.
 */
public class PrintHelper {

    public static void printTicket(final List<SubmitOrderBean.ResultBean.RetailOrderInfoListBean> goodsList, final SubmitOrderBean orderBean) {
        final List<InfoListBean> mList = new ArrayList<>();
        List<SubmitOrderBean.ResultBean.RetailOrderInfoListBean> retailOrderInfoList;
        retailOrderInfoList = orderBean.getResult().getRetailOrderInfoList();
        List<SubmitOrderBean.ResultBean.RetailDiscountsInfoListBean> retailDiscountsInfoList = orderBean.getResult().getRetailDiscountsInfoList();

        for (int i = 0; i < retailOrderInfoList.size(); i++) {
            //商品或活动
            if ("0".equals(retailOrderInfoList.get(i).getActivityFlag())) {
                SubmitOrderBean.ResultBean.RetailOrderInfoListBean subBean = retailOrderInfoList.get(i);
                mList.add(new InfoListBean(subBean.getProductName(), subBean.getProductPrice()));
            }
        }
        for (int i = 0; i < retailDiscountsInfoList.size(); i++) {
            //优惠券或预付金
            SubmitOrderBean.ResultBean.RetailDiscountsInfoListBean subBean = retailDiscountsInfoList.get(i);
            mList.add(new InfoListBean(subBean.getDiscountsName(), subBean.getDiscountsAmount()));
        }
        ICallback callback = PrintCallbackHelper.getCallback();
        try {
            IWoyouService woyouService = DxtSaleApplication.getInstance().getWoyouService();
            woyouService.printerInit(callback);
            woyouService.setAlignment(1, callback);
            woyouService.setFontSize(26, callback);
            woyouService.printText(DxtSaleApplication.getInstance().getUserBean().getOfficeName() + "\n\n", callback);
            woyouService.setFontSize(20, callback);
            woyouService.printColumnsString(new String[]{"名称", "单价", "数量", "金额"}, new int[]{7, 2, 2, 2}, new int[]{0, 1, 1, 1}, callback);
            woyouService.printText("--------------------------------------\n", callback);

            for (int i = 0; i < goodsList.size(); i++) {
                SubmitOrderBean.ResultBean.RetailOrderInfoListBean bean = goodsList.get(i);
                woyouService.setAlignment(0, callback);
                woyouService.printText(bean.getProductName()+"\n", callback);
                String[] strings = new String[]{"", bean.getProductPrice(), bean.getProductNum(), bean.getProductAmount()};
                woyouService.printColumnsString(strings, new int[]{7, 2, 2, 2}, new int[]{0, 1, 1, 1}, callback);
            }
            for (int i = 0; i < mList.size(); i++) {
                InfoListBean bean = mList.get(i);
                woyouService.printText(bean.getName()+"\n", callback);

                String[] strings = new String[]{"", bean.getPrice(), "1.0", bean.getPrice()};
                woyouService.printColumnsString(strings, new int[]{7, 2, 2, 2}, new int[]{0, 1, 1, 1}, callback);
            }
            woyouService.printText("备注：" + orderBean.getResult().getRemarks() + "\n", callback);
            woyouService.setAlignment(1, callback);
            woyouService.printText("--------------------------------------\n", callback);
            woyouService.setAlignment(0, callback);
//            woyouService.printText("合计：" + bean.getResult().getProductAmount() + "元\n", callback);
            woyouService.printText("应付金额：" + orderBean.getResult().getProceedsAmount() + "元\n", callback);
            woyouService.printText("实付金额：" + orderBean.getResult().getProceedsAmount() + "元\n", callback);
            woyouService.printText("下单时间：" + DateUtils.getDate(orderBean.getResult().getCreateDate()) + "\n", callback);
            woyouService.printText("订单号：" + orderBean.getResult().getVchcode() + "\n\n", callback);
            woyouService.setAlignment(1, callback);
            woyouService.printText("关注公众号，获取更多服务\n\n", callback);
            woyouService.printQRCode("http://weixin.qq.com/r/0kRmfiDEL31QrWQd9xHA", 8, 2, callback);
            woyouService.lineWrap(5, callback);
        } catch (RemoteException e) {
            ToastUtils.showLong("打印失败");
            e.printStackTrace();
        }
    }
}
