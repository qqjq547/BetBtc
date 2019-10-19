package com.betbtc.app.ui.adapter;

import androidx.annotation.Nullable;

import com.betbtc.app.R;
import com.betbtc.app.model.UserMenu;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class UserMenuAdapter extends BaseQuickAdapter<UserMenu, BaseViewHolder> {
    public UserMenuAdapter(@Nullable List<UserMenu> data) {
        super(R.layout.item_user_menu,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserMenu item) {
        helper.setText(R.id.tv_name,item.getName());
        helper.setImageResource(R.id.iv_image,item.getIconResId());

    }
}
