package com.betbtc.app.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.betbtc.app.tools.GsonUtil;
import com.betbtc.app.tools.LogUtil;
import com.betbtc.app.ui.WebSocketManager;
import com.betbtc.app.ui.adapter.DealRecordAdapter;
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

public class TrendFragment extends MvpFragment {

    @BindView(R.id.kLineChartView)
    KLineChartView kLineChartView;
    @BindView(R.id.rv_deal_record)
    RecyclerView rvDealRecord;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.tv_filter)
    TextView tvFilter;
    KLineChartAdapter adapter;
    String[] navTitle;
    String[] periods;
    String symbol = "btcusdt";
    int curPosition=1;
    int size = 50;
    List<KLine> klines = new ArrayList<>();
    FragmentContainerHelper mFragmentContainerHelper;

    DealRecordAdapter dealRecordAdapter;
    List<DealRecord> dealRecords = new ArrayList<>();



    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_trend;
    }

    @Override
    public void initViewAndData() {
        initIndicater();
        initKline();
        initListView();
        magicIndicator.onPageSelected(curPosition);
        changeChart(curPosition);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        WebSocketManager.getInstance().disconnectSocket();
    }

    @OnClick(R.id.tv_filter)
    public void onViewClicked() {
        String[] names=getContext().getResources().getStringArray(R.array.filter_real_deal);
        new SheetDialog(Arrays.asList(names), new SheetDialog.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                tvFilter.setText(names[position]);
            }
        }).show(getFragmentManager());

    }
    void connectWebSocket() {
        WebSocketManager.getInstance().connectSocket(new WebSocketManager.OnWebSocketListener() {
            @Override
            public void onOpen() {
                WebSocketManager.getInstance().subMarket1Day(symbol,periods[curPosition]);
            }

            @Override
            public void onMessage(String json) {
                LogUtil.d(json);
//                EventBus.getDefault().post(new KLineEvent());
                if (!TextUtils.isEmpty(json)) {
                    SocketMsg msg = GsonUtil.fromJson(json, SocketMsg.class);
                    KLine kLine = msg.getTick();
                    if (adapter.getCount() > 0) {
                        if (kLine.getId() == (klines.get(adapter.getCount() - 1).getId())) {
                            adapter.changeItem(adapter.getCount() - 1, kLine);
                        } else {
                            adapter.addLast(kLine);
                        }
                    }
                }

            }
        });
    }

    public void initIndicater() {
        navTitle=getResources().getStringArray(R.array.data_nav);
        periods=getResources().getStringArray(R.array.huobi_period);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return navTitle == null ? 0 : navTitle.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setSelectedColor(ColorUtils.getColor(R.color.colorPrimary));
                colorTransitionPagerTitleView.setTextSize(12);
                colorTransitionPagerTitleView.setText(navTitle[index]);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (curPosition!=index){
                            curPosition=index;
                            mFragmentContainerHelper.handlePageSelected(index);
                            changeChart(index);
                        }
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setColors(ColorUtils.getColor(R.color.colorPrimary));
                indicator.setLineHeight(CommonUtil.dp2px(getContext(),2));
                indicator.setLineWidth(CommonUtil.dp2px(getContext(),20));
                indicator.setRoundRadius(CommonUtil.dp2px(getContext(),1));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        mFragmentContainerHelper = new FragmentContainerHelper(magicIndicator);
    }

    public void initListView() {
        rvDealRecord.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        rvDealRecord.addItemDecoration(new VerticalDecoration(getContext()));
        rvDealRecord.setItemAnimator(new DefaultItemAnimator());
        dealRecords.add(new DealRecord());
        dealRecords.add(new DealRecord());
        dealRecords.add(new DealRecord());
        dealRecords.add(new DealRecord());
        dealRecords.add(new DealRecord());
        dealRecords.add(new DealRecord());
        dealRecords.add(new DealRecord());
        dealRecords.add(new DealRecord());
        dealRecords.add(new DealRecord());
        dealRecordAdapter = new DealRecordAdapter(dealRecords);
        rvDealRecord.setAdapter(dealRecordAdapter);
    }

    private void initKline() {
        TextView loadingView = new TextView(getContext());
        loadingView.setTextColor(getResources().getColor(R.color.white));
        loadingView.setText("正在加载...");
        adapter = new KLineChartAdapter();

        kLineChartView.setAdapter(adapter)
                //loading anim
                .setAnimLoadData(true)
                .setGridColumns(5)
                .setGridRows(5)
                .setOverScrollRange(getActivity().getWindowManager().getDefaultDisplay().getWidth() / 5)
                //show loading View
                .setLoadingView(loadingView)
                //full or stroke
                .setCandleSolid(false)
                .showLoading()
                .setBetterX(false)
                .setSlidListener(new SlidListener() {
                    @Override
                    public void onSlidLeft() {
                        ToastUtils.show("onSlidLeft");
//                        if (!load) {
//                            kLineChartView.showLoading();
//                            com.icechao.klinelib.utils.LogUtil.e("onSlidLeft");
//                            List<KChartBean> kChartBeans = all.subList(0, 300);
//                            kChartBeans.addAll(adapter.getDatas());
//                            adapter.resetData(kChartBeans, true);
//                            kLineChartView.hideLoading();
//                            load = true;
//                        }
                    }

                    @Override
                    public void onSlidRight() {
                        com.icechao.klinelib.utils.LogUtil.e("onSlidRight");

                    }
                })
                //set Y label formater
                .setValueFormatter(new ValueFormatter() {
                    @Override
                    public String format(float value) {
                        return String.format(Locale.CHINA, "%.02f", value);
                    }
                })
                //set vol y label formater
                .setVolFormatter(new ValueFormatter() {
                    @Override
                    public String format(float value) {
                        return String.format(Locale.CHINA, "%.02f", value);
                    }
                })
                //set date label formater
                .setDateTimeFormatter(new DateFormatter() {
                    @Override
                    public String format(Date date) {
                        return DateUtil.MMddHHmmTimeFormat.format(date);
                    }
                });
    }

    public void getKLineData(String period){
        HuobiRepository.getInstance().getHistoryKline(symbol, period, size).subscribe(new ApiCallback<HuobiResponse<List<KLine>>>() {
            @Override
            public void onSuccess(HuobiResponse<List<KLine>> response) {
                if (response.getStatus().equals("ok")) {
                        kLineChartView.showLoading();
                        List<KLine> temp = response.getData();
                        Collections.reverse(temp);
                        klines.clear();
                        klines.addAll(0, temp);
                        adapter.resetData(klines,true );
                        kLineChartView.hideLoading();
                        connectWebSocket();
                }
            }

            @Override
            public void onError(ApiException exception) {
                ToastUtils.show(exception.getMessage());
            }

            @Override
            public void onFinish() {

            }
        });
    }
    public void changeChart(int index){
        WebSocketManager.getInstance().disconnectSocket();
        kLineChartView.hideSelectData();
        if (index==0){
            kLineChartView.setKlineState(Status.KlineStatus.TIME_LINE);
        }else {
            kLineChartView.setKlineState(Status.KlineStatus.K_LINE);
        }
        getKLineData(periods[curPosition]);

    }
}
