package com.betbtc.app.ui.adapter;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.betbtc.app.R;
import com.betbtc.app.tools.ColorUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class DialAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public DialAdapter(@Nullable List<String> data) {
        super(R.layout.item_dial,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (TextUtils.equals(item,"del")){
            helper.setGone(R.id.tv_name,false);
            helper.setGone(R.id.iv_image,true);
            helper.setImageResource(R.id.iv_image,R.drawable.ic_dial_delete);
        }else if(TextUtils.equals(item,"clear")){
            helper.setGone(R.id.tv_name,false);
            helper.setGone(R.id.iv_image,true);
            helper.setImageResource(R.id.iv_image,R.drawable.ic_dial_clear);
        }else {
            helper.setGone(R.id.tv_name,true);
            helper.setGone(R.id.iv_image,false);
            helper.setText(R.id.tv_name,item);
        }
        if ((helper.getAdapterPosition()+1)%4==0){
            helper.setBackgroundColor(R.id.lin_item, ColorUtils.getColor(R.color.bg_theme));
        }else {
            helper.setBackgroundColor(R.id.lin_item, ColorUtils.getColor(R.color.bg_white));
        }
    }
}
