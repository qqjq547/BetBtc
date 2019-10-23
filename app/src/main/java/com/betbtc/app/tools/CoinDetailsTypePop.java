package com.betbtc.app.tools;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betbtc.app.R;
import com.betbtc.app.ui.adapter.CoinDetailsTypeAdapter;
import com.betbtc.app.view.GridItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoinDetailsTypePop extends PopupWindow {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.lin_root)
    View linRoot;

    CoinDetailsTypeAdapter coinDetailsTypeAdapter;

    @OnClick(R.id.lin_root)
    public void onViewClicked() {
        dismiss();
    }

    public interface OnSelectItemListener {
        void onSelectItem(int position);
    }

    public CoinDetailsTypePop(Activity activity, List<String> ksbTypes, final OnSelectItemListener onSelectItemListener) {
        View view = LayoutInflater.from(activity).inflate(R.layout.ppw_coin_details, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        rv.setLayoutManager(new GridLayoutManager(activity, 3));
        rv.addItemDecoration(new GridItemDecoration(3,CommonUtil.dp2px(activity,15),true));
        coinDetailsTypeAdapter = new CoinDetailsTypeAdapter(ksbTypes);
        coinDetailsTypeAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        coinDetailsTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                onSelectItemListener.onSelectItem(position);
                dismiss();
            }
        });
        rv.setAdapter(coinDetailsTypeAdapter);
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
    }
}
