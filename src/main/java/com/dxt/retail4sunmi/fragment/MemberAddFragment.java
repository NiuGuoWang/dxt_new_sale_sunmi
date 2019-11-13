package com.dxt.retail4sunmi.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.dxt.retail4sunmi.DxtSaleApplication;
import com.dxt.retail4sunmi.R;
import com.dxt.retail4sunmi.activity.MemberActivity;
import com.dxt.retail4sunmi.adapter.MemberAddInfoAdapter;
import com.dxt.retail4sunmi.api.ApiService;
import com.dxt.retail4sunmi.entity.QueryMemberBean;
import com.dxt.retail4sunmi.entity.RigisterMemberBean;
import com.dxt.retail4sunmi.entity.UserBean;
import com.dxt.retail4sunmi.eventbus.MessageVipEvent;
import com.dxt.retail4sunmi.network.RxHttpUtils;
import com.dxt.retail4sunmi.network.base.BaseObserver;
import com.dxt.retail4sunmi.network.interceptor.DialogTransformer;
import com.dxt.retail4sunmi.network.interceptor.SchedulerTransformer;
import com.dxt.retail4sunmi.util.ToastUtils;
import com.dxt.retail4sunmi.util.Util;
import com.dxt.retail4sunmi.util.ValidUtils;
import com.dxt.retail4sunmi.view.MyDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liu04
 * @date 2017/9/20
 */

public class MemberAddFragment extends Fragment {
    private String mobile;
    private String type;
    private List<UserBean.MemberPropertyListBean> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getString("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.addmember, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        ListView mMemberInfoList = view.findViewById(R.id.memberInfoList);
        list = DxtSaleApplication.getInstance().getUserBean().getMemberPropertyList();
        MemberAddInfoAdapter mMemberAddInfoAdapter = new MemberAddInfoAdapter(list, getActivity());
        mMemberInfoList.setAdapter(mMemberAddInfoAdapter);
        //提交按钮
        View submitBut = view.findViewById(R.id.relative_addmember_add);
        submitBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findVip();
            }
        });

    }

    private void getFormParam(String[] properties) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("userId", DxtSaleApplication.getInstance().getUserBean().getUserId());
        paraMap.put("companycode", DxtSaleApplication.getInstance().getUserBean().getCompanycode());
        paraMap.put("properties", gson.toJson(properties));
        String sign = Util.returnSign(paraMap, Util.SIGN_KEY);
        paraMap.put("sign", sign);
        String toJson = gson.toJson(paraMap);
        RxHttpUtils
                .createApi(ApiService.class)
                .addMember(toJson)
                .compose(SchedulerTransformer.<RigisterMemberBean>transformer())
                .compose(((MemberActivity) getActivity()).<RigisterMemberBean>bindToLifecycle())
                .compose(new DialogTransformer(getActivity()).<RigisterMemberBean>transformer())
                .subscribe(new BaseObserver<RigisterMemberBean>() {

                    @Override
                    protected void onSuccess(RigisterMemberBean bean) {
                        if ("success".equals(bean.getFlag())) {
                            ToastUtils.showLong("注册成功");
                            if ("select".equals(type)) {
                                EventBus.getDefault().post(new MessageVipEvent(mobile,
                                        bean.getMemberId()));
                            }
                            getActivity().finish();
                        } else {
                            ToastUtils.showLong("注册失败" + bean.getMsg());
                        }
                    }
                });
    }


    private void findVip() {
        List<UserBean.MemberPropertyListBean> memberPropertyList = DxtSaleApplication.getInstance().getUserBean().getMemberPropertyList();
        final String[] properties = new String[memberPropertyList.size()];

        for (int i = 0; i < properties.length; i++) {
            UserBean.MemberPropertyListBean bean = memberPropertyList.get(i);
            String text = bean.getValue();
            if ("1".equals(bean.getRequired()) && TextUtils.isEmpty(text)) {
                ToastUtils.showLong(MessageFormat.format("{0}为必填项", bean.getName()));
                return;
            }
            if ("MOBILE".equals(bean.getColumnName())) {
                if (!TextUtils.isEmpty(text) && !ValidUtils.validMobileNumber(text)) {
                    ToastUtils.showLong("请输入正确的手机号");
                    return;
                } else {
                    mobile = text;
                }
            }
            properties[i] = bean.getId() + "," + text;
        }


        Map<String, Object> us = new HashMap<>();
        us.put("companycode", DxtSaleApplication.getInstance().getUserBean().getCompanycode());
        us.put("mobile", mobile);
        us.put("method", "member/member/getMemberByMobile");
        us.put("timestamp", System.currentTimeMillis());
        String sign = Util.returnSign(us, Util.SIGN_KEY);
        us.put("sign", sign);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String toJson = gson.toJson(us);

        RxHttpUtils
                .createApi(ApiService.class)
                .getMemberInfo(toJson)
                .compose(SchedulerTransformer.<QueryMemberBean>transformer())
                .compose(new DialogTransformer(getActivity()).<QueryMemberBean>transformer())
                .subscribe(new BaseObserver<QueryMemberBean>() {
                    @Override
                    protected void onSuccess(QueryMemberBean bean) {
                        if ("success".equals(bean.getFlag())) {
                            if (bean.getRestult().size() == 0) {
                                getFormParam(properties);
                            } else {
                                final MyDialog diaLog = MyDialog.createDialog(getActivity(), R.style.MyDialog, R.layout.dialog_delete, 0.25f, 0.8f, getActivity().getWindowManager());
                                TextView cancel = diaLog.getDialogView().findViewById(R.id.delete_cancel);
                                TextView determine = diaLog.getDialogView().findViewById(R.id.delete_determine);
                                TextView message = diaLog.getDialogView().findViewById(R.id.delete_message);
                                TextView deleteTitle = diaLog.getDialogView().findViewById(R.id.delete_title);
                                deleteTitle.setText("确认添加");
                                message.setTextSize(14);
                                message.setText("该手机已经注册过会员，确认仍要注册？");
                                cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        diaLog.dismiss();
                                    }
                                });
                                determine.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        getFormParam(properties);
                                        diaLog.dismiss();
                                    }
                                });
                                diaLog.show();
                            }
                        } else {
                            ToastUtils.showLong(bean.getMsg());
                        }
                    }
                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        for (UserBean.MemberPropertyListBean bean : list) {
            bean.setValue("");
        }
    }
}
