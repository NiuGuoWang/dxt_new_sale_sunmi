package com.dxt.retail4sunmi.util;

/**
 * Created by star on 2018/1/3 0003.
 */

public class ValidUtils {

    //校验金额最多两位小数的实数
    public static boolean validPrice(String num) {
        String reg = "^(([1-9][0-9]*)|(([0]\\.\\d{1,2}|[1-9][0-9]*\\.\\d{1,2})))$";
        return num.matches(reg);
    }

    ///不超过五位的正整数
    public static boolean validPositionNum(String num) {
        String reg = "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";
        return num.matches(reg);
    }
    public static boolean validNum4(String num) {
        String reg = "^[+]?\\d{1,5}$";
        return num.matches(reg);
    }

    /**
     * 功能描述：验证手机号
     */
    public static boolean validMobileNumber(String mobileNumber) {
        String reg = "^1[3-9]\\d{9}$";
        return mobileNumber.matches(reg);
    }
    /**
     * 功能描述：验证身份证号
     *
     * @param certificate
     * @return
     * @author 徐江
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     * @since 2014-4-3
     */
    public static boolean validCertificate(String certificate) {
        String reg = "^\\d{15}|\\d{17}[1-9Xx]$";
        return !certificate.matches(reg);
    }
}
