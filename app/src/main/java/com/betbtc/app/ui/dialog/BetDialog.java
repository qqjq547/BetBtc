package com.betbtc.app.ui.dialog;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpDialog;
import com.betbtc.app.model.HomeMyBet;
import com.betbtc.app.tools.Constant;
import com.betbtc.app.ui.adapter.DialAdapter;
import com.betbtc.app.view.GridItemDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjq.toast.ToastUtils;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

public class BetDialog extends MvpDialog {
    @BindView(R.id.tv_overage)
    TextView tvOverage;
    @BindView(R.id.rv_dial)
    RecyclerView rvDial;
    @BindView(R.id.tv_add_bet)
    TextView tvAddBet;
    @BindView(R.id.tv_confirm_bet)
    TextView tvConfirmBet;

    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.tv_rate)
    TextView tvRate;
    @BindView(R.id.tv_recent)
    TextView tvRecent;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.lin_info)
    LinearLayout linInfo;
    @BindView(R.id.tv_input)
    TextView tvInput;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.lin_operate)
    LinearLayout linOperate;


    HomeMyBet homeMyBet;

    double max;

    public BetDialog(HomeMyBet homeMyBet, double max) {
        this.homeMyBet = homeMyBet;
        this.max = max;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.dialog_bet;
    }

    @Override
    public void initViewAndData() {
        tvOverage.setText(String.valueOf(max));
        rvDial.setLayoutManager(new GridLayoutManager(getContext(), 4));
        rvDial.addItemDecoration(new GridItemDecoration(4, 2, true));
        DialAdapter adapter = new DialAdapter(Arrays.asList(Constant.DIAL_DATA));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                dealText(Constant.DIAL_DATA[position]);
            }
        });
        rvDial.setAdapter(adapter);
    }

    public void dealText(String str) {
        String curText = tvInput.getText().toString();
        String result = "";
        if (TextUtils.isDigitsOnly(str)) {
            if (curText.length() == 0 && TextUtils.equals(str, "0")) {
                return;
            }
            result = curText + str;
        } else {
            switch (str) {
                case "clean":
                    result = "";
                    break;
                case "del":
                    if (curText.length() > 0) {
                        result = curText.substring(0, (curText.length() - 1));
                    }
                    break;
                case "+10":
                    if (curText.length() > 0) {
                        int num = Integer.parseInt(curText);
                        result = String.valueOf(num + 10);
                    } else {
                        result = "10";
                    }
                    break;
                case "+50":
                    if (curText.length() > 0) {
                        int num = Integer.parseInt(curText);
                        result = String.valueOf(num + 50);
                    } else {
                        result = "50";
                    }
                    break;
                case "+100":
                    if (curText.length() > 0) {
                        int num = Integer.parseInt(curText);
                        result = String.valueOf(num + 200);
                    } else {
                        result = "100";
                    }
                    break;
                case "max":
                    result = String.valueOf(max);
                    break;
            }
        }
        try {
            if (result.length() > 0) {
                int cur = Integer.parseInt(result);
                if (cur > max) {
                    ToastUtils.show(R.string.out_of_amount);
                    return;
                }
            }
            tvInput.setText(result);
        } catch (Exception e) {
            ToastUtils.show(R.string.out_of_amount);
        }

    }

    @OnClick({R.id.iv_close, R.id.tv_add_bet, R.id.tv_confirm_bet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_add_bet:
                break;
            case R.id.tv_confirm_bet:
                break;
        }
    }
}
