package com.betbtc.app.ui.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betbtc.app.R;
import com.betbtc.app.model.HomeMyBet;
import com.betbtc.app.tools.Constant;
import com.betbtc.app.view.GridItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjq.toast.ToastUtils;

import java.util.Arrays;
import java.util.List;

public class HomeMyBetAdapter extends BaseQuickAdapter<HomeMyBet,BaseViewHolder> {
    int operatePos=-1;
    int max=0;

    public HomeMyBetAdapter(@Nullable List<HomeMyBet> data) {
        super(R.layout.item_home_my_bet,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeMyBet item) {
        helper.addOnClickListener(R.id.lin_info);
        helper.addOnClickListener(R.id.tv_add_bet);
        helper.addOnClickListener(R.id.tv_confirm_bet);
        helper.addOnClickListener(R.id.iv_close);
        if (operatePos==helper.getAdapterPosition()){
            helper.setGone(R.id.lin_operate,true);
        }else {
            helper.setGone(R.id.lin_operate,false);
        }
        RecyclerView rvDial=helper.getView(R.id.rv_dial);
        if (rvDial.getAdapter()==null) {
            rvDial.setLayoutManager(new GridLayoutManager(mContext, 4));
            rvDial.addItemDecoration(new GridItemDecoration(4, 3, true));
            DialAdapter adapter=new DialAdapter(Arrays.asList(Constant.DIAL_DATA));
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    dealText(helper,Constant.DIAL_DATA[position]);
                }
            });
            rvDial.setAdapter(adapter);
        }

    }

    public void setMax(int max) {
        this.max = max;
    }

    public void expandItem(int postition){
        if (operatePos==postition){
            operatePos=-1;
            notifyItemChanged(postition);
        }else {
            int tempPos=operatePos;
            operatePos=postition;
            if (tempPos>=0){
                notifyItemChanged(tempPos);
            }
            if (operatePos>=0){
                notifyItemChanged(operatePos);
            }
        }
    }
    public void dealText(BaseViewHolder helper, String str){
        TextView textView=helper.getView(R.id.tv_input);
        String curText=textView.getText().toString();
        String result="";
        if (TextUtils.isDigitsOnly(str)){
            if (curText.length()==0&&TextUtils.equals(str,"0")){
                return;
            }
            result=curText+str;
        }else {
            switch (str){
                case "clean":
                    result="";
                    break;
                case "del":
                    if (curText.length()>0){
                        result=curText.substring(0,(curText.length()-1));
                    }
                    break;
                case "+10":
                    if (curText.length()>0){
                        int num=Integer.parseInt(curText);
                        result=String.valueOf(num+10);
                    }else {
                        result="10";
                    }
                    break;
                case "+50":
                    if (curText.length()>0){
                        int num=Integer.parseInt(curText);
                        result=String.valueOf(num+50);
                    }else {
                        result="50";
                    }
                    break;
                case "+100":
                    if (curText.length()>0){
                        int num=Integer.parseInt(curText);
                        result=String.valueOf(num+100);
                    }else {
                        result="100";
                    }
                    break;
                case "max":
                    result=String.valueOf(max);
                    break;
            }
        }
        try {
            if (result.length()>0) {
                Integer.parseInt(result);
            }
            textView.setText(result);
//            helper.setText(R.id.tv_confirm_bet,)
        }catch (Exception e){
            e.printStackTrace();
            ToastUtils.show(R.string.out_of_amount);
        }

    }
}
