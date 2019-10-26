package com.betbtc.app.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentController;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpFragment;
import com.betbtc.app.http.exception.ApiException;
import com.betbtc.app.http.response.HuobiResponse;
import com.betbtc.app.http.retrofit.ApiCallback;
import com.betbtc.app.mvp.model.DealRecord;
import com.betbtc.app.service.huobi.HuobiRepository;
import com.betbtc.app.service.huobi.model.KLine;
import com.betbtc.app.service.huobi.model.SocketMsg;
import com.betbtc.app.tools.ColorUtils;
import com.betbtc.app.tools.CommonUtil;
import com.betbtc.app.tools.FragmentUtil;
import com.betbtc.app.tools.GsonUtil;
import com.betbtc.app.tools.LogUtil;
import com.betbtc.app.ui.WebSocketManager;
import com.betbtc.app.ui.adapter.DealRecordAdapter;
import com.betbtc.app.ui.adapter.MyFragmentPagerAdapter;
import com.betbtc.app.ui.dialog.SheetDialog;
import com.betbtc.app.view.VerticalDecoration;
import com.hjq.toast.ToastUtils;
import com.icechao.klinelib.adapter.KLineChartAdapter;
import com.icechao.klinelib.formatter.DateFormatter;
import com.icechao.klinelib.formatter.ValueFormatter;
import com.icechao.klinelib.utils.DateUtil;
import com.icechao.klinelib.utils.SlidListener;
import com.icechao.klinelib.utils.Status;
import com.icechao.klinelib.view.KLineChartView;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

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
