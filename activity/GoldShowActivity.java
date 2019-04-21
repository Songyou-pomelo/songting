package com.example.greeknews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.greeknews.R;
import com.example.greeknews.adapter.GoldShowAdapter;
import com.example.greeknews.base.BaseActivity;
import com.example.greeknews.base.Constants;
import com.example.greeknews.bean.GoldShowBean;
import com.example.greeknews.ptrsenter.EmptyPersenter;
import com.example.greeknews.view.EmptyView;
import com.example.greeknews.weigth.SimpleTouchHelperCallBack;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoldShowActivity extends BaseActivity<EmptyView, EmptyPersenter> implements EmptyView {

    @BindView(R.id.gold_show_tool)
    Toolbar goldShowTool;
    @BindView(R.id.gold_tab_rec)
    RecyclerView goldTabRec;
    private ArrayList<GoldShowBean> showBeans;

    @Override
    protected EmptyPersenter initPersenter() {
        return new EmptyPersenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gold_show;
    }

    @Override
    public void initView() {
        showBeans = (ArrayList<GoldShowBean>) getIntent().getSerializableExtra(Constants.DATA);

        goldShowTool.setTitle(R.string.special_show);
        goldShowTool.setNavigationIcon(R.mipmap.ic_close);
        setSupportActionBar(goldShowTool);

        goldShowTool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finshAct();
            }
        });

        goldTabRec.setLayoutManager(new LinearLayoutManager(this));
        GoldShowAdapter adapter = new GoldShowAdapter(showBeans);
        goldTabRec.setAdapter(adapter);
        goldTabRec.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        //拖拽移动和 侧滑删除的功能
        SimpleTouchHelperCallBack simpleTouchHelperCallBack = new SimpleTouchHelperCallBack(adapter);
        ItemTouchHelper helper = new ItemTouchHelper(simpleTouchHelperCallBack);
        helper.attachToRecyclerView(goldTabRec);
        simpleTouchHelperCallBack.setMswipeEnable(false);
//        //attachToRecyclerView 附加到回收商的观点
    }

    private void finshAct() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DATA,showBeans);
        setResult(RESULT_OK,intent);

        finish();

    }

    @Override
    public void onBackPressed() {
        finshAct();
    }
}
