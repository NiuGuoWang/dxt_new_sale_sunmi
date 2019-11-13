package com.dxt.retail4sunmi.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.dxt.retail4sunmi.DxtSaleApplication;
import com.dxt.retail4sunmi.R;
import com.dxt.retail4sunmi.adapter.VipAdapter;
import com.dxt.retail4sunmi.api.ApiService;
import com.dxt.retail4sunmi.entity.QueryMemberBean;
import com.dxt.retail4sunmi.eventbus.MessageVipEvent;
import com.dxt.retail4sunmi.network.RxHttpUtils;
import com.dxt.retail4sunmi.network.base.BaseObserver;
import com.dxt.retail4sunmi.network.interceptor.DialogTransformer;
import com.dxt.retail4sunmi.network.interceptor.SchedulerTransformer;
import com.dxt.retail4sunmi.util.ToastUtils;
import com.dxt.retail4sunmi.util.Util;
import com.dxt.retail4sunmi.util.ValidUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liu04
 * @date 2017/9/20
 */

public class MemberFindFragment extends Fragment implements VipAdapter.Callback {
    private Context mContext;
    private EditText etPhoneNum;//按手机号或会员卡号搜索
    private String strPhoneNum;
    private List<QueryMemberBean.RestultBean> listEntity;
    private VipAdapter adapter;
    private String type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        type = getArguments().getString("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_member_find, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }


    private void initView(View view) {
        listEntity = new ArrayList<>();
        adapter = new VipAdapter(mContext, listEntity, this, type);
        etPhoneNum = view.findViewById(R.id.et_search);

        ListView listViewShowVip = view.findViewById(R.id.listview_showVIP);
        listViewShowVip.setAdapter(adapter);
        etPhoneNum.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                strPhoneNum = "";
                listEntity.clear();
                strPhoneNum = etPhoneNum.getText().toString().trim();
                if (!TextUtils.isEmpty(strPhoneNum)) {
                    if (ValidUtils.validMobileNumber(strPhoneNum)) {
                        findVip(strPhoneNum);
                    } else {
                        ToastUtils.showLong("请输入正确的手机号");
                    }
                } else {
                    ToastUtils.showLong("搜索条件不能为空");
                }
                return true;
            }
        });
    }

    @Override
    public void click(View v) {
        int num = (Integer) v.getTag();
        EventBus.getDefault().post(new MessageVipEvent(listEntity.get(num).getMobile(),
                listEntity.get(num).getId()));
        getActivity().finish();
    }

    private void findVip(final String strPhoneNum) {
        Map<String, Object> us = new HashMap<>();
        us.put("companycode", DxtSaleApplication.getInstance().getUserBean().getCompanycode());
        us.put("mobile", strPhoneNum);
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
                                ToastUtils.showLong("未搜索到该会员");
                            } else {
                                listEntity.addAll(bean.getRestult());
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            ToastUtils.showLong(bean.getMsg());
                        }
                    }
                });


    }
}
