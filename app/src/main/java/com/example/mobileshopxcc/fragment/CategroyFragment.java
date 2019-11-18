package com.example.mobileshopxcc.fragment;


import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileshopxcc.Adapter.CategroyLeftAdapter;
import com.example.mobileshopxcc.Adapter.CategroyRightAdapter;
import com.example.mobileshopxcc.Common.BaseFragment;
import com.example.mobileshopxcc.R;
import com.example.mobileshopxcc.activtity.GoodsListActivity;
import com.example.mobileshopxcc.http.ProgressDialogSubscriber;
import com.example.mobileshopxcc.http.entity.CategoryEntity;
import com.example.mobileshopxcc.http.presenter.CategoryPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CategroyFragment extends BaseFragment {
    @BindView(R.id.rv_left)
    RecyclerView rv_left;

    @BindView(R.id.rv_right)
    RecyclerView rv_right;

    private List<CategoryEntity> leftData;
    private List<CategoryEntity> rightData;
    private CategroyLeftAdapter leftAdapter;
    private CategroyRightAdapter rightAdapter;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_categroy;
    }

    @OnClick(R.id.ll_search)
    void search(){toastShort("开发中...");}


    @Override
    protected void initData() {
        super.initData();

        CategoryPresenter.getTopList(new ProgressDialogSubscriber<List<CategoryEntity>>(getActivity()) {
            @Override
            public void onNext(List<CategoryEntity> categoryEntities) {
                leftData.clear();
                leftData.addAll(categoryEntities);
                leftAdapter.notifyDataSetChanged();
                leftAdapter.setSelect(0);
                loadSecondList(0);
            }
        });
    }
    private void loadSecondList(int pos){
        if (leftData==null||leftData.size()==0){
            return;
        }

        CategoryEntity entity = leftData.get(pos);
        CategoryPresenter.getSecondList(new ProgressDialogSubscriber<List<CategoryEntity>>(getActivity()) {
            @Override
            public void onNext(List<CategoryEntity> categoryEntities) {
                rightData.clear();
                rightData.addAll(categoryEntities);
                rightAdapter.notifyDataSetChanged();
            }
        },entity.getCat_id());
    }
    @Override
    protected void initView(View view) {
        super.initView(view);
        leftData =new ArrayList<>();
        rightData = new ArrayList<>();
        LinearLayoutManager leftManager = new LinearLayoutManager(getActivity());
        leftManager.setOrientation(OrientationHelper.VERTICAL);
        rv_left.setLayoutManager(leftManager);
        GridLayoutManager rightManager = new GridLayoutManager(getActivity(),3,OrientationHelper.VERTICAL,false);
        rv_right.setLayoutManager(rightManager);
        leftAdapter = new CategroyLeftAdapter(getActivity(),leftData);
        leftAdapter.setOnItemClickListener(new CategroyLeftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, CategoryEntity entity) {
                leftAdapter.setSelect(position);
                loadSecondList(position);
            }
        });
        rightAdapter = new CategroyRightAdapter(getActivity(),rightData);
        rightAdapter.setOnItemClickListener(new CategroyRightAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, CategoryEntity entity) {
                Intent intent = new Intent(getActivity(), GoodsListActivity.class);
                intent.putExtra("cat_id",entity.getCat_id());
                startActivity(intent);
            }
        });

        rv_left.setAdapter(leftAdapter);
        rv_right.setAdapter(rightAdapter);

    }
}
