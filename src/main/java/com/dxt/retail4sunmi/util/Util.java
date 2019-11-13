package com.dxt.retail4sunmi.util;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author star
 */
public class Util {

    public static final String SIGN_KEY="KBmTWW0nvtC298rJ";

    private static final String TAG = "Utils";

    private static Context context;

    private Util() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull Context context) {
        Util.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("u should init first");
    }





    /**
     * 方法: compare
     * 描述:
     * 参数: @param string
     * 参数: @param string2
     * 参数: @return
     * 返回: int
     * 异常
     * 作者:
     * 日期: 2011-10-18
     */
    private static int compare(String o1, String o2) {
        int mask = 0xFFDF;
        int length1 = o1.length();
        int length2 = o2.length();
        int length = length1 > length2 ? length2 : length1;
        int c1, c2;
        int d1, d2;
        for (int i = 0; i < length; i++) {
            c1 = o1.charAt(i);
            c2 = o2.charAt(i);
            d1 = c1 & mask;
            d2 = c2 & mask;
            if (d1 > d2) {
                return 1;
            } else if (d1 < d2) {
                return -1;
            } else {
                if (c1 > c2) {
                    return 1;
                } else if (c1 < c2) {
                    return -1;
                }
            }
        }
        if (length1 > length2) {
            return 1;
        } else if (length1 < length2) {
            return -1;
        }
        return 0;
    }

    /**
     * 方法: encrypt
     * 描述:
     * 参数: @param needToMd5
     * 参数: @param string
     * 参数: @return
     * 返回: String
     * 异常
     * 作者:
     * 日期: 2011-10-18
     */
    private static String encrypt(String inputText, String algorithmName) {
        if (inputText == null || "".equals(inputText.trim())) {
            throw new IllegalArgumentException("请输入要加密的内容");
        }
        if (algorithmName == null || "".equals(algorithmName.trim())) {
            algorithmName = "md5";
        }
        try {
            MessageDigest m = MessageDigest.getInstance(algorithmName);
            m.update(inputText.getBytes("UTF8"));
            byte[] s = m.digest();
            StringBuilder sb = new StringBuilder();
            for (byte value : s) {
                sb.append(Integer.toHexString((value & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String returnSign(Map<String, Object> myMap,String key) {
        StringBuilder str = new StringBuilder();
        List<String> ls = new ArrayList<>(myMap.keySet());
        Collections.sort(ls);
        for (int i = 0; i < ls.size(); i++) {
            //空value不参与签名
            if (!"sign".equals(ls.get(i)) && !"sign_type".equals(ls.get(i)) && myMap.get(ls.get(i)) != null
                    && !"".equals(myMap.get(ls.get(i))) ) {
//                Log.i("llz",myMap.get(ls.get(i)).toString()+"--------");
                try {
                    String s=myMap.get(ls.get(i)).toString();
//                    Log.i("llz","s--"+i+"--"+s);
                    if(s.contains("{")){continue;}
//                    str.append(ls.get(i) + "=" + URLDecoder.decode(s ,"UTF-8") + "&");
                    str.append(ls.get(i)).append("=").append(s).append("&");
//                    str.append(ls.get(i) + "=" + URLDecoder.decode(s.replaceAll("%", "%25") ,"UTF-8") + "&");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        str.append(key);
//        Log.i("llz","signMap----"+str);
        return encrypt(str.toString(), "md5");
    }

}

