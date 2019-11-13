package com.dxt.retail4sunmi.helper;

import android.os.RemoteException;
import android.text.TextUtils;

import com.dxt.retail4sunmi.DxtSaleApplication;
import com.dxt.retail4sunmi.entity.InfoListBean;
import com.dxt.retail4sunmi.entity.SubmitOrderBean;
import com.dxt.retail4sunmi.entity.TiketModelBean;
import com.dxt.retail4sunmi.util.DateUtils;
import com.dxt.retail4sunmi.util.ToastUtils;

import java.util.List;

import woyou.aidlservice.jiuiv5.ICallback;
import woyou.aidlservice.jiuiv5.IWoyouService;

/**
 * star on 2018/11/9 0009.
 */
public class PrintTicketHelper {


    public static void printCommonTicket(String realPayMoney, String changerStr, List<SubmitOrderBean.ResultBean.RetailOrderInfoListBean> goodsList, List<InfoListBean> mDiscountsInfoList, SubmitOrderBean submitOrderBean) {

        ICallback callback = PrintCallbackHelper.getCallback();
        try {
            IWoyouService woyouService = DxtSaleApplication.getInstance().getWoyouService();
            woyouService.printerInit(callback);
            woyouService.setAlignment(1, callback);
            woyouService.setFontSize(36, callback);
            woyouService.printText(DxtSaleApplication.getInstance().getUserBean().getOfficeName() + "\n\n", callback);
            woyouService.setFontSize(24, callback);
            woyouService.printColumnsString(new String[]{"名称", "单价", "数量", "金额"}, new int[]{4, 1, 1, 1}, new int[]{0, 1, 1, 1}, callback);
            woyouService.printText("------------------------------------------------\n", callback);

            for (int i = 0; i < goodsList.size(); i++) {
                SubmitOrderBean.ResultBean.RetailOrderInfoListBean bean = goodsList.get(i);
                String[] strings = new String[]{bean.getProductName(), bean.getProductPrice(), bean.getProductNum(), bean.getProductAmount()};
                woyouService.printColumnsString(strings, new int[]{7, 2, 2, 2}, new int[]{0, 1, 1, 1}, callback);
                if (!TextUtils.isEmpty(bean.getImei())) {
                    woyouService.printText("串码：" + bean.getImei() + "\n", callback);
                }
            }
            for (int i = 0; i < mDiscountsInfoList.size(); i++) {

                InfoListBean bean = mDiscountsInfoList.get(i);
                String[] strings = new String[]{bean.getName(), bean.getPrice(), bean.getNum(), bean.getAmount()};
                woyouService.printColumnsString(strings, new int[]{7, 2, 2, 2}, new int[]{0, 1, 1, 1}, callback);
            }
            woyouService.setAlignment(0, callback);
            woyouService.printText("备注：" + submitOrderBean.getResult().getRemarks() + "\n", callback);
            woyouService.setAlignment(1, callback);
            woyouService.printText("-----------------------------------------------\n", callback);
            woyouService.setAlignment(0, callback);
            woyouService.printText("合计：" + submitOrderBean.getResult().getProductAmount() + "元\n", callback);
            woyouService.printText("优惠：" + submitOrderBean.getResult().getDiscountsAmount() + "元\n", callback);
            woyouService.printText("应付金额：" + submitOrderBean.getResult().getProceedsAmount() + "元\n", callback);
            woyouService.printText("实收金额：" + realPayMoney + "元\n", callback);
            if (TextUtils.isEmpty(changerStr)) {
                changerStr = "0.0";
            }
            woyouService.printText("找零：" + changerStr + "元\n", callback);
            woyouService.printText("下单时间：" + DateUtils.getDate(submitOrderBean.getResult().getCreateDate()) + "\n", callback);
            woyouService.printText("订单号：" + submitOrderBean.getResult().getVchcode() + "\n\n", callback);
            woyouService.setAlignment(1, callback);
            woyouService.printText("关注公众号，获取更多服务\n\n", callback);
            woyouService.printQRCode("http://weixin.qq.com/r/0kRmfiDEL31QrWQd9xHA", 8, 2, callback);
            woyouService.lineWrap(3, callback);
        } catch (RemoteException e) {
            ToastUtils.showLong("打印失败");
            e.printStackTrace();
        }

    }

    public static void printModelTicket(String realPayMoney, String changerStr, SubmitOrderBean submitOrderBean, TiketModelBean tiketModelBean, List<InfoListBean> mDiscountsInfoList, List<SubmitOrderBean.ResultBean.RetailOrderInfoListBean> goodsList, int num) {

        ICallback callback = PrintCallbackHelper.getCallback();
        int count = 0;
        try {
            IWoyouService woyouService = DxtSaleApplication.getInstance().getWoyouService();
            woyouService.printerInit(callback);
            do {
                List printModelInfoList = tiketModelBean.getResult().getPrintModelInfoList();
                for (int i = 0; i < printModelInfoList.size(); i++) {
                    TiketModelBean.ResultBean.PrintModelInfoListBean bean = (TiketModelBean.ResultBean.PrintModelInfoListBean) printModelInfoList.get(i);
                    woyouService.setAlignment(Integer.parseInt(bean.getStyleflag()), callback);
                    woyouService.setFontSize(24, callback);
                    switch (bean.getFlag()) {
                        case "0":
                        case "1":
                            if ("1".equals(bean.getLineNo())) {
                                woyouService.setFontSize(36, callback);
                                woyouService.sendRAWData(new byte[]{0x1B, 0x45, 0x1},
                                        callback);
                                woyouService.printText(bean.getText() + "\n", callback);
                                woyouService.sendRAWData(new byte[]{0x1B, 0x45, 0x0},
                                        callback);
                            } else {
                                woyouService.printText(bean.getText() + "\n", callback);
                            }

                            break;
                        case "2":
                            woyouService.printText("\n", callback);
                            woyouService.printQRCode(bean.getText() + "\n", 8, 2, callback);
                            break;
                        case "3":
                            woyouService.printText("\n", callback);
                            woyouService.printColumnsString(new String[]{"名称", "单价", "数量", "金额"}, new int[]{4, 1, 1, 1}, new int[]{0, 1, 1, 1}, callback);
                            woyouService.printText("------------------------------------------------\n", callback);
                            for (int j = 0; j < goodsList.size(); j++) {
                                SubmitOrderBean.ResultBean.RetailOrderInfoListBean retailOrderInfoListBean = goodsList.get(j);
                                String[] strings = new String[]{retailOrderInfoListBean.getProductName(), retailOrderInfoListBean.getProductPrice(), retailOrderInfoListBean.getProductNum(), retailOrderInfoListBean.getProductAmount()};
                                woyouService.printColumnsString(strings, new int[]{7, 2, 2, 2}, new int[]{0, 1, 1, 1}, callback);
                                if (!TextUtils.isEmpty(retailOrderInfoListBean.getImei())) {
                                    woyouService.setAlignment(0, callback);
                                    woyouService.printText("串码：" + retailOrderInfoListBean.getImei() + "\n", callback);
                                    woyouService.setAlignment(1, callback);
                                }
                            }
                            for (int j = 0; j < mDiscountsInfoList.size(); j++) {

                                InfoListBean infoListBean = mDiscountsInfoList.get(j);
                                String[] strings = new String[]{infoListBean.getName(), infoListBean.getPrice(), infoListBean.getNum(), infoListBean.getAmount()};
                                woyouService.printColumnsString(strings, new int[]{7, 2, 2, 2}, new int[]{0, 1, 1, 1}, callback);
                            }
                            woyouService.setAlignment(0, callback);
                            woyouService.printText("备注：" + submitOrderBean.getResult().getRemarks() + "\n", callback);
                            woyouService.setAlignment(1, callback);
                            woyouService.printText("-----------------------------------------------\n", callback);
                            woyouService.setAlignment(0, callback);
                            woyouService.printText("合计：" + submitOrderBean.getResult().getProductAmount() + "元\n", callback);
                            woyouService.printText("优惠：" + submitOrderBean.getResult().getDiscountsAmount() + "元\n", callback);
                            woyouService.printText("应付金额：" + submitOrderBean.getResult().getProceedsAmount() + "元\n", callback);
                            woyouService.printText("实收金额：" + realPayMoney + "元\n", callback);
                            if (TextUtils.isEmpty(changerStr)) {
                                changerStr = "0.0";
                            }
                            woyouService.printText("找零：" + changerStr + "元\n", callback);
                            break;
                        case "4":
                            woyouService.printText("-----------------------------------------------\n", callback);
                            break;
                        default:
                            break;
                    }
                }


                woyouService.lineWrap(3, callback);
                count++;
            } while (count < num);

        } catch (
                RemoteException e) {
            ToastUtils.showLong("打印失败");
            e.printStackTrace();
        }


    }

    public static void printRefundModelTicket( SubmitOrderBean submitOrderBean, TiketModelBean tiketModelBean, List<InfoListBean> mDiscountsInfoList, List<SubmitOrderBean.ResultBean.RetailOrderInfoListBean> goodsList, int num) {

        ICallback callback = PrintCallbackHelper.getCallback();
//        String change ="0.0";
//        for (int i = 0; i < submitOrderBean.getResult().getRetailPays().size(); i++) {
//            RefundBean.ResultBean.RetailPaysBean payBean = submitOrderBean.getResult().getRetailPays().get(i);
//            if ("现金".equals(payBean.getShroffMethod())) {
//                change = String.valueOf(payBean.getChangeMoney());
//            }
//        }
        int count = 0;
        try {
            IWoyouService woyouService = DxtSaleApplication.getInstance().getWoyouService();
            woyouService.printerInit(callback);
            do {
                List printModelInfoList = tiketModelBean.getResult().getPrintModelInfoList();
                for (int i = 0; i < printModelInfoList.size(); i++) {
                    TiketModelBean.ResultBean.PrintModelInfoListBean bean = (TiketModelBean.ResultBean.PrintModelInfoListBean) printModelInfoList.get(i);
                    woyouService.setAlignment(Integer.parseInt(bean.getStyleflag()), callback);
                    woyouService.setFontSize(24, callback);
                    switch (bean.getFlag()) {
                        case "0":
                        case "1":
                            if ("1".equals(bean.getLineNo())) {
                                woyouService.setFontSize(36, callback);
                                woyouService.sendRAWData(new byte[]{0x1B, 0x45, 0x1},
                                        callback);
                                woyouService.printText(bean.getText() + "\n", callback);
                                woyouService.sendRAWData(new byte[]{0x1B, 0x45, 0x0},
                                        callback);
                            } else {
                                woyouService.printText(bean.getText() + "\n", callback);
                            }

                            break;
                        case "2":
                            woyouService.printText("\n", callback);
                            woyouService.printQRCode(bean.getText() + "\n", 8, 2, callback);
                            break;
                        case "3":
                            woyouService.printText("\n", callback);
                            woyouService.printColumnsString(new String[]{"名称", "单价", "数量", "金额"}, new int[]{4, 1, 1, 1}, new int[]{0, 1, 1, 1}, callback);
                            woyouService.printText("------------------------------------------------\n", callback);
                            for (int j = 0; j < goodsList.size(); j++) {
                                SubmitOrderBean.ResultBean.RetailOrderInfoListBean retailOrderInfoListBean = goodsList.get(j);
                                String[] strings = new String[]{retailOrderInfoListBean.getProductName(), String.valueOf(retailOrderInfoListBean.getProductPrice()), retailOrderInfoListBean.getProductNum(), String.valueOf(retailOrderInfoListBean.getProductAmount())};
                                woyouService.printColumnsString(strings, new int[]{7, 2, 2, 2}, new int[]{0, 1, 1, 1}, callback);
                                if (!TextUtils.isEmpty(retailOrderInfoListBean.getImei())) {
                                    woyouService.setAlignment(0, callback);
                                    woyouService.printText("串码：" + retailOrderInfoListBean.getImei() + "\n", callback);
                                    woyouService.setAlignment(1, callback);
                                }
                            }
                            for (int j = 0; j < mDiscountsInfoList.size(); j++) {

                                InfoListBean infoListBean = mDiscountsInfoList.get(j);
                                String[] strings = new String[]{infoListBean.getName(), infoListBean.getPrice(), infoListBean.getNum(), infoListBean.getAmount()};
                                woyouService.printColumnsString(strings, new int[]{7, 2, 2, 2}, new int[]{0, 1, 1, 1}, callback);
                            }
                            woyouService.setAlignment(0, callback);
                            woyouService.printText("备注：" + submitOrderBean.getResult().getRemarks() + "\n", callback);
                            woyouService.setAlignment(1, callback);
                            woyouService.printText("-----------------------------------------------\n", callback);
                            woyouService.setAlignment(0, callback);
                            woyouService.printText("合计：" + submitOrderBean.getResult().getProductAmount() + "元\n", callback);
                            woyouService.printText("优惠：" + submitOrderBean.getResult().getDiscountsAmount() + "元\n", callback);
                            woyouService.printText("应付金额：" + submitOrderBean.getResult().getProceedsAmount() + "元\n", callback);
//                            woyouService.printText("实收金额：" + realPayMoney + "元\n", callback);
//                            woyouService.printText("找零：" + change + "元\n", callback);
                            break;
                        case "4":
                            woyouService.printText("-----------------------------------------------\n", callback);
                            break;
                        default:
                            break;
                    }
                }


                woyouService.lineWrap(3, callback);
                count++;
            } while (count < num);

        } catch (RemoteException e) {
            ToastUtils.showLong("打印失败");
            e.printStackTrace();
        }


    }

    public static void printCommonRefundTicket(List<SubmitOrderBean.ResultBean.RetailOrderInfoListBean> goodsList, List<InfoListBean> mDiscountsInfoList, SubmitOrderBean submitOrderBean) {
//        String change ="0.0";
//        for (int i = 0; i < submitOrderBean.getResult().getRetailPays().size(); i++) {
//            RefundBean.ResultBean.RetailPaysBean payBean = submitOrderBean.getResult().getRetailPays().get(i);
//            if ("现金".equals(payBean.getShroffMethod())) {
//                change = String.valueOf(payBean.getChangeMoney());
//            }
//        }
        ICallback callback = PrintCallbackHelper.getCallback();
        try {
            IWoyouService woyouService = DxtSaleApplication.getInstance().getWoyouService();
            woyouService.printerInit(callback);
            woyouService.setAlignment(1, callback);
            woyouService.setFontSize(36, callback);
            woyouService.printText(DxtSaleApplication.getInstance().getUserBean().getOfficeName() + "\n\n", callback);
            woyouService.setFontSize(24, callback);
            woyouService.printColumnsString(new String[]{"名称", "单价", "数量", "金额"}, new int[]{4, 1, 1, 1}, new int[]{0, 1, 1, 1}, callback);
            woyouService.printText("------------------------------------------------\n", callback);

            for (int i = 0; i < goodsList.size(); i++) {
                SubmitOrderBean.ResultBean.RetailOrderInfoListBean bean = goodsList.get(i);
                String[] strings = new String[]{bean.getProductName(), String.valueOf(bean.getProductPrice()), bean.getProductNum(), String.valueOf(bean.getProductAmount())};
                woyouService.printColumnsString(strings, new int[]{7, 2, 2, 2}, new int[]{0, 1, 1, 1}, callback);
                if (!TextUtils.isEmpty(bean.getImei())) {
                    woyouService.printText("串码：" + bean.getImei() + "\n", callback);
                }
            }
            for (int i = 0; i < mDiscountsInfoList.size(); i++) {

                InfoListBean bean = mDiscountsInfoList.get(i);
                String[] strings = new String[]{bean.getName(), bean.getPrice(), bean.getNum(), bean.getAmount()};
                woyouService.printColumnsString(strings, new int[]{7, 2, 2, 2}, new int[]{0, 1, 1, 1}, callback);
            }
            woyouService.setAlignment(0, callback);
            woyouService.printText("备注：" + submitOrderBean.getResult().getRemarks() + "\n", callback);
            woyouService.setAlignment(1, callback);
            woyouService.printText("-----------------------------------------------\n", callback);
            woyouService.setAlignment(0, callback);
            woyouService.printText("合计：" + submitOrderBean.getResult().getProductAmount() + "元\n", callback);
            woyouService.printText("优惠：" + submitOrderBean.getResult().getDiscountsAmount() + "元\n", callback);
            woyouService.printText("应付金额：" + submitOrderBean.getResult().getProceedsAmount() + "元\n", callback);
//            woyouService.printText("实收金额：" + realPayMoney + "元\n", callback);
//            woyouService.printText("找零：" + change + "元\n", callback);
            woyouService.printText("下单时间：" + DateUtils.getDate(String.valueOf(submitOrderBean.getResult().getCreateDate())) + "\n", callback);
            woyouService.printText("订单号：" + submitOrderBean.getResult().getVchcode() + "\n\n", callback);
            woyouService.setAlignment(1, callback);
            woyouService.printText("关注公众号，获取更多服务\n\n", callback);
            woyouService.printQRCode("http://weixin.qq.com/r/0kRmfiDEL31QrWQd9xHA", 8, 2, callback);
            woyouService.lineWrap(3, callback);
        } catch (RemoteException e) {
            ToastUtils.showLong("打印失败");
            e.printStackTrace();
        }

    }
}
