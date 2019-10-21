package com.betbtc.app.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpFragment;
import com.betbtc.app.model.UserMenu;
import com.betbtc.app.tools.ColorUtils;
import com.betbtc.app.tools.glide.CircleTransformation;
import com.betbtc.app.tools.glide.GlideApp;
import com.betbtc.app.ui.activity.CoinDetailActivity;
import com.betbtc.app.ui.activity.PersonInfoActivity;
import com.betbtc.app.ui.activity.RechargeActivity;
import com.betbtc.app.ui.activity.SafeCenterActivity;
import com.betbtc.app.ui.activity.SettingActivity;
import com.betbtc.app.ui.activity.WithDrawActivity;
import com.betbtc.app.ui.adapter.UserMenuAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class UserFragment extends MvpFragment {


    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.iv_tip)
    ImageView ivTip;
    @BindView(R.id.tv_reward)
    TextView tvReward;
    @BindView(R.id.tv_recharge)
    TextView tvRecharge;
    @BindView(R.id.tv_withdraw)
    TextView tvWithdraw;
    @BindView(R.id.iv_banner)
    ImageView ivBanner;
    @BindView(R.id.rv_menu)
    RecyclerView rvMenu;

    List<UserMenu> menuArr=new ArrayList<>();
    UserMenuAdapter userMenuAdapter;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_user;
    }

    @Override
    public void initViewAndData() {
        GlideApp.with(getContext())
                .load(R.drawable.ic_default_head)
                .transform(new CircleTransformation(2, ColorUtils.getColor(R.color.bg_white)))
                .into(ivAvatar);
        rvMenu.setLayoutManager(new GridLayoutManager(getContext(),3));
        menuArr.add(new UserMenu(getString(R.string.menu_recommend),R.drawable.ic_my_welfare));
        menuArr.add(new UserMenu(getString(R.string.balance_details),R.drawable.ic_my_balance));
        menuArr.add(new UserMenu(getString(R.string.safe_center),R.drawable.ic_my_safe));
        menuArr.add(new UserMenu(getString(R.string.play_tutorial),R.drawable.ic_my_guide));
        menuArr.add(new UserMenu(getString(R.string.common_help),R.drawable.ic_my_help));
        menuArr.add(new UserMenu(getString(R.string.relate_custom),R.drawable.ic_my_custom));
        menuArr.add(new UserMenu(getString(R.string.about_us),R.drawable.ic_my_us));
        userMenuAdapter=new UserMenuAdapter(menuArr);
        userMenuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position){
                    case 0:
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), CoinDetailActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), SafeCenterActivity.class));
                        break;

                }
            }
        });
        rvMenu.setAdapter(userMenuAdapter);

    }

    @OnClick({R.id.iv_setting,R.id.iv_avatar, R.id.tv_share, R.id.tv_balance, R.id.iv_tip, R.id.tv_recharge, R.id.tv_withdraw, R.id.iv_banner})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.iv_avatar:
                startActivity(new Intent(getActivity(), PersonInfoActivity.class));
                break;
            case R.id.tv_share:
                break;
            case R.id.tv_balance:
                break;
            case R.id.iv_tip:
                break;
            case R.id.tv_recharge:
                startActivity(new Intent(getActivity(), RechargeActivity.class));
                break;
            case R.id.tv_withdraw:
                startActivity(new Intent(getActivity(), WithDrawActivity.class));
                break;
            case R.id.iv_banner:
                break;
        }
    }
}
