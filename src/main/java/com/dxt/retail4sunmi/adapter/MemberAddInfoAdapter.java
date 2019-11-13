package com.dxt.retail4sunmi.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.dxt.retail4sunmi.R;
import com.dxt.retail4sunmi.entity.UserBean;

import java.text.MessageFormat;
import java.util.List;

/**
 * @author liu04
 * @date 2017/12/20
 */

public class MemberAddInfoAdapter extends BaseAdapter {
    private List<UserBean.MemberPropertyListBean> list;
    private Context context;

    public MemberAddInfoAdapter(List<UserBean.MemberPropertyListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView
                    = LayoutInflater.from(context).inflate(R.layout.item_user_info, parent, false);
            holder.textAddMemberTip = convertView.findViewById(R.id.text_addmember_tip);
            holder.editAddMemberValue = convertView.findViewById(R.id.edit_addmember_value);
            holder.tvRequired = convertView.findViewById(R.id.tv_required);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final UserBean.MemberPropertyListBean bean = list.get(position);
        if ("1".equals(bean.getRequired())) {
            holder.tvRequired.setVisibility(View.VISIBLE);
        }else {
            holder.tvRequired.setVisibility(View.INVISIBLE);
        }
        holder.textAddMemberTip.setText(MessageFormat.format("{0}:", bean.getName()));
        if (!TextUtils.isEmpty(bean.getValue())){
            holder.editAddMemberValue.setText(MessageFormat.format("{0}", bean.getValue()));
        }

        holder.editAddMemberValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               bean.setValue(s.toString());
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView textAddMemberTip;
        TextView tvRequired;
        EditText editAddMemberValue;
    }
}
