package com.dxt.retail4sunmi.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.dxt.retail4sunmi.R;
import com.dxt.retail4sunmi.entity.GoodsBean;
import com.dxt.retail4sunmi.util.Arith;
import com.dxt.retail4sunmi.util.ValidUtils;
import com.dxt.retail4sunmi.view.MyDialog;

import java.util.ArrayList;
import java.util.List;


public class OrderUpdateAdapter extends BaseAdapterHelper<GoodsBean> implements DialogInterface.OnDismissListener {

    private Callback mCallback;//接口
    private Context mContext;
    private boolean isVip = false;
//    private boolean isSpinnerFirst = true;

    public void setVip(boolean vip) {
        isVip = vip;
        modifyRealPrice();
        modifySum();
    }

    public OrderUpdateAdapter(Context context, List<GoodsBean> list, Callback callback) {
        super(context, list);
        this.mCallback = callback;
        mContext = context;
        clearSpinnerTag();
        modifyRealPrice();
        modifySum();
    }

    private void modifyRealPrice() {
        for (GoodsBean goodsBean : adapterList) {
            if (goodsBean.getRealPrice() != null && "0".equals(goodsBean.getPriceType())) {
                continue;//议价优先级
            }
            //如果单位不改变，且满足会员，则商品有会员价：如果单位改变，虽然满足会员，也不考虑会员价；
            if (goodsBean.getProductMultipleUnit() == null || goodsBean.getProductMultipleUnit().getSelectProductUnitNum() == 0) {
                if (isVip && !TextUtils.isEmpty(goodsBean.getMemberPrice())) {
                    goodsBean.setRealPrice(goodsBean.getMemberPrice());
                } else {
                    goodsBean.setRealPrice(String.valueOf(goodsBean.getRetailPrice()));
                }
            } else {
                goodsBean.setRealPrice(goodsBean.getProductMultipleUnit().getProductSubUnitList().get(goodsBean.getProductMultipleUnit().getSelectProductUnitNum()).getProductUnitPrice().getPrice());
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View getItemView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder viewHolder;
        final GoodsBean goodsBean = adapterList.get(position);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_ordersubmit, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.editUpdateItemPrice.setTag(position);
        viewHolder.editUpdateItemNumber.setTag(position);
        viewHolder.etImei.setTag(position);
        viewHolder.imageUpdateItemDelete.setTag(position);
        viewHolder.tvUint.setTag(position);

        if ("1".equals(goodsBean.getPriceType())) {
            viewHolder.editUpdateItemPrice.setEnabled(false);
        } else {
            viewHolder.editUpdateItemPrice.setEnabled(true);
            viewHolder.editUpdateItemPrice.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    int position = (int) viewHolder.editUpdateItemPrice.getTag();
                    double price;
                    String str = s.toString().trim();
                    if (TextUtils.isEmpty(str) || !ValidUtils.validPositionNum(str)) {

                        //同步价格显示
                        textChange(position, 0);
                        return;
                    }
                    price = Double.parseDouble(s.toString().trim());
                    textChange(position, price);
                }
            });
        }
        viewHolder.imageUpdateItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu((Integer) v.getTag());
            }
        });

        //单位管理
        if ("1".equals(goodsBean.getUnitType())) {
            viewHolder.unit_view.setVisibility(View.VISIBLE);
            final List<GoodsBean.ProductMultipleUnit.ProductSubUnit> list = goodsBean.getProductMultipleUnit().getProductSubUnitList();
            List<String> nameList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                GoodsBean.ProductMultipleUnit.ProductSubUnit unit = list.get(i);
                nameList.add(unit.getName());
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, R.layout.item_unit, nameList);
            viewHolder.tvUint.setAdapter(arrayAdapter);
            viewHolder.tvUint.setSelection(goodsBean.getProductMultipleUnit().getSelectProductUnitNum());
            viewHolder.tvUint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view,
                                           int pos, long id) {
                    GoodsBean goodsBean = adapterList.get((Integer) viewHolder.tvUint.getTag());
                    if (goodsBean.getUnitFirst()) {
                        goodsBean.setUnitFirst(false);
                        return;
                    }
                    GoodsBean.ProductMultipleUnit.ProductSubUnit unit = list.get(pos);
                    goodsBean.setRealPrice(unit.getProductUnitPrice().getPrice());
                    goodsBean.getProductMultipleUnit().setSelectProductUnitNum(pos);
                    viewHolder.editUpdateItemPrice.setText(goodsBean.getRealPrice());
                    modifySum();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        } else {
            viewHolder.unit_view.setVisibility(View.GONE);
        }
        viewHolder.textUpdateItemName.setText(goodsBean.getName());


        //Imei号
        if ("1".equals(goodsBean.getImeiManage())) {
            viewHolder.viewImei.setVisibility(View.VISIBLE);
            viewHolder.ivImeiScan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallback.scanClick(position);
                }
            });
            viewHolder.etImei.setText(goodsBean.getImei());
            viewHolder.etImei.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    GoodsBean goodsBean = adapterList.get((Integer) viewHolder.etImei.getTag());
                    goodsBean.setImei(s.toString());
                }
            });
        } else {
            viewHolder.viewImei.setVisibility(View.GONE);
        }

        viewHolder.editUpdateItemNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int position = (int) viewHolder.editUpdateItemNumber.getTag();
                String str = s.toString().trim();
                if (TextUtils.isEmpty(str) || !ValidUtils.validPositionNum(str)) {
                    numChange(position, 0);
                    return;
                }
                double num = Double.valueOf(str);
                numChange(position, num);
            }
        });

        viewHolder.imageUpdateItemPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsBean goodsBean1 = adapterList.get(position);
                double num = goodsBean1.getNum();
                num = Arith.add(num, 1);
                goodsBean1.setNum(num);
                viewHolder.editUpdateItemNumber.setText(String.valueOf(num));
                numChange(position, goodsBean1.getNum());
            }
        });

        viewHolder.imageUpdateItemSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsBean goodsBean1 = adapterList.get(position);
                double num = goodsBean1.getNum();
                if (num < 1) {
                    return;
                }
                num = Arith.sub(num, 1);
                goodsBean1.setNum(num);
                viewHolder.editUpdateItemNumber.setText(String.valueOf(num));
                numChange(position, goodsBean1.getNum());
            }
        });

        viewHolder.editUpdateItemPrice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ("0.0".equals(viewHolder.editUpdateItemPrice.getText().toString().trim())) {
                    viewHolder.editUpdateItemPrice.setText("");
                }
                return false;
            }
        });
        viewHolder.editUpdateItemPrice.setText(goodsBean.getRealPrice());
        viewHolder.editUpdateItemNumber.setText(String.valueOf(goodsBean.getNum()));
        return convertView;
    }


    //删除弹窗
    private void showPopupMenu(final int position) {
        final MyDialog diaLog = MyDialog.createDialog(mContext, R.style.MyDialog, R.layout.dialog_delete, 0.25f, 0.8f, ((Activity) mContext).getWindowManager());
        TextView cancel = diaLog.getDialogView().findViewById(R.id.delete_cancel);
        TextView determine = diaLog.getDialogView().findViewById(R.id.delete_determine);
        TextView tvMsg = diaLog.getDialogView().findViewById(R.id.delete_message);
        tvMsg.setText("确认删除？");
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaLog.dismiss();
            }
        });
        determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterList.remove(position);
                notifyDataSetChanged();
                clearSpinnerTag();
                modifySum();
                diaLog.dismiss();
            }
        });
        diaLog.show();
    }

    private void clearSpinnerTag() {
        for (int i = 0; i < adapterList.size(); i++) {
            GoodsBean bean = adapterList.get(i);
            bean.setUnitFirst(true);
        }
    }

    private void modifySum() {
        double totalSum = 0.0;
        int totalNum = adapterList.size();
        for (int i = 0; i < totalNum; i++) {
            GoodsBean bean = adapterList.get(i);
            Double temp = Arith.mul(bean.getNum(), Double.parseDouble(bean.getRealPrice()));
            Double singleTotal = Arith.round2(temp, 4);
            totalSum = Arith.add(totalSum, singleTotal);
        }
        totalSum = Arith.round2(totalSum, 2);
        mCallback.refreshSum(totalNum, totalSum);
    }

    private void textChange(int position, double price) {
        if (adapterList.size() == 0) {
            return;
        }
        GoodsBean goodsBean = adapterList.get(position);
        if (price == Double.valueOf(goodsBean.getRealPrice())) {
            return;
        }
        goodsBean.setRealPrice(String.valueOf(price));
        modifySum();
    }

    private void numChange(int position, double num) {
        if (adapterList.size() == 0) {
            return;
        }
        GoodsBean goodsBean = adapterList.get(position);
        goodsBean.setNum(num);
        modifySum();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        notifyDataSetChanged();
        clearSpinnerTag();
    }

    class ViewHolder {
        private TextView textUpdateItemName;
        private EditText editUpdateItemPrice;
        private ImageView imageUpdateItemSubtract;
        private ImageView imageUpdateItemPlus;
        private ImageView imageUpdateItemDelete;
        private EditText editUpdateItemNumber;
        private Spinner tvUint;
        private View unit_view;
        private View viewImei;
        private EditText etImei;
        private ImageView ivImeiScan;

        ViewHolder(View itemView) {
            textUpdateItemName = itemView.findViewById(R.id.text_orderSubmitItem);
            editUpdateItemNumber = itemView.findViewById(R.id.et_num);
            tvUint = itemView.findViewById(R.id.tv_unit);
            editUpdateItemPrice = itemView.findViewById(R.id.et_price);
            imageUpdateItemSubtract = itemView.findViewById(R.id.iv_subtract);
            imageUpdateItemPlus = itemView.findViewById(R.id.iv_plus);
            imageUpdateItemDelete = itemView.findViewById(R.id.iv_clear);
            viewImei = itemView.findViewById(R.id.view_imei);
            unit_view = itemView.findViewById(R.id.unit_view);
            etImei = itemView.findViewById(R.id.et_imei);
            ivImeiScan = itemView.findViewById(R.id.iv_item_scan);
        }
    }
}
