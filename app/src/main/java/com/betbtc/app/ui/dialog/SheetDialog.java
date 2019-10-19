package com.betbtc.app.ui.dialog;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.betbtc.app.R;
import com.betbtc.app.base.BaseDialog;
import com.betbtc.app.ui.adapter.SheetAdapter;
import com.betbtc.app.view.VerticalDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SheetDialog extends BaseDialog {
    @BindView(R.id.rv_menu)
    RecyclerView rvMenu;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;

    List<String> dataArr;
    SheetAdapter adapter;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    OnItemClickListener onItemClickListener;
   public SheetDialog(@NonNull List<String> dataArr, final OnItemClickListener onItemClickListener){
       super();
       this.dataArr=dataArr;
       this.onItemClickListener=onItemClickListener;
   }

    @Override
    public int getLayout() {
        return R.layout.dialog_sheet;
    }

    @Override
    public void initViewAndData() {
        rvMenu.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        rvMenu.addItemDecoration(new VerticalDecoration(getContext()));
        rvMenu.setItemAnimator(new DefaultItemAnimator());
        adapter=new SheetAdapter(dataArr);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                dismiss();
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });
        rvMenu.setAdapter(adapter);
    }

    @OnClick(R.id.tv_cancel)
    public void onViewClicked() {
        dismiss();
    }
}
