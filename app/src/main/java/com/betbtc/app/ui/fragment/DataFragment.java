package com.betbtc.app.ui.fragment;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpFragment;
import com.betbtc.app.tools.FragmentUtil;

import butterknife.BindView;

public class DataFragment extends MvpFragment {

    @BindView(R.id.rgroup_data)
    RadioGroup rgroupData;
    @BindView(R.id.rbtn_trend)
    RadioButton rbtnTrend;
    @BindView(R.id.rbtn_history)
    RadioButton rbtnHistory;

    FragmentUtil fragmentUtil;
    Fragment[] fragments=new Fragment[2];

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_data;
    }

    @Override
    public void initViewAndData() {
        fragments[0]=new TrendFragment();
        fragments[1]=new HistoryFragment();
        fragmentUtil=new FragmentUtil(getFragmentManager(),R.id.fl_content,fragments);
        rgroupData.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.rbtn_trend:
                    fragmentUtil.showFragment(0);
                    break;
                case R.id.rbtn_history:
                    fragmentUtil.showFragment(1);
                    break;
            }
        });
        rbtnTrend.setChecked(true);
    }

}
