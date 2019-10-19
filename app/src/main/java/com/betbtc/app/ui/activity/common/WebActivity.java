package com.betbtc.app.ui.activity.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.betbtc.app.R;
import com.betbtc.app.base.BasePresenter;
import com.betbtc.app.base.MvpActivity;
import com.betbtc.app.tools.CommonUtil;
import com.betbtc.app.tools.Constant;
import com.betbtc.app.tools.IntentUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.Nullable;

public class WebActivity extends MvpActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;
    @BindView(R.id.wv_content)
    WebView wvContent;
    @BindView(R.id.lin_title)
    LinearLayout linTitle;

    boolean hasRefresh = true;
;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_web;
    }

    @SuppressLint("JavascriptInterface")
    @Override
    public void initViewAndData() {
        String title = getIntent().getStringExtra(Constant.TITLE);
        String url = getIntent().getStringExtra(Constant.URL);
        srlRefresh.setEnableRefresh(hasRefresh);
        tvTitle.setText(title);
        wvContent.getSettings().setJavaScriptEnabled(true);
        wvContent.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wvContent.getSettings().setLoadWithOverviewMode(true);
        wvContent.getSettings().setSupportZoom(true);
        wvContent.getSettings().setUseWideViewPort(true);
        wvContent.getSettings().setBuiltInZoomControls(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            wvContent.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        wvContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (TextUtils.equals(url, "jxaction://close")) {
                    finish();
                } else {
                    IntentUtils.startWebActivity(WebActivity.this, view.getTitle(), url);
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (wvContent.getProgress() == 100) {
                    tvTitle.setText(view.getTitle());
                    srlRefresh.finishRefresh();
                }
            }
        });
        wvContent.addJavascriptInterface(new JSInterface(this), "browserController");
        wvContent.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
                downloadByBrowser(url);
            }
        });
        wvContent.loadUrl(CommonUtil.getTimeStampUrl(url));
        srlRefresh.setEnableLoadMore(false);
        srlRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                wvContent.reload();
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (wvContent.canGoBack()) {
            wvContent.goBack();
        } else {
            super.onBackPressed();
        }
    }


    public class JSInterface {
        public JSInterface(Activity activity) {
        }

        @JavascriptInterface
        public void openWin(final String openUrl) {


        }

    }

    private void downloadByBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                default:
                    wvContent.reload();
                    break;
            }
        }
    }

}
