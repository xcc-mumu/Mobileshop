package com.example.mobileshopxcc.fragment;

import android.view.View;
import android.widget.ImageButton;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mobileshopxcc.Common.BaseFragment;
import com.example.mobileshopxcc.R;

public class NavigationFragment extends BaseFragment implements View.OnClickListener{
    private ImageButton mIbHome;
    private ImageButton mIbCategory;
    private ImageButton mIbCart;
    private ImageButton mIbPersonal;
    private HomeFragment homeFragment;
    private CartFragment cartFragment;
    private CategroyFragment categroyFragment;
    private PersonalFragment personalFragment;

    @Override
    public int getContentViewId() {
        return R.layout.frament_navigation;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mIbHome=view.findViewById(R.id.ib_home);
        mIbCategory=view.findViewById(R.id.ib_category);
        mIbCart=view.findViewById(R.id.ib_cart);
        mIbPersonal=view.findViewById(R.id.ib_personal);
        mIbHome.setOnClickListener(this);
        mIbPersonal.setOnClickListener(this);
        mIbCart.setOnClickListener(this);
        mIbCategory.setOnClickListener(this);

        select(mIbHome);
    }

    private void select(View v) {
        mIbHome.setImageResource(R.mipmap.tab_item_home_normal);
        mIbCategory.setImageResource(R.mipmap.tab_item_category_normal);
        mIbCart.setImageResource(R.mipmap.tab_item_cart_normal);
        mIbPersonal.setImageResource(R.mipmap.tab_item_personal_normal);
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        if(homeFragment!=null){
            fragmentTransaction.hide(homeFragment);
        }
        if (cartFragment!=null){
            fragmentTransaction.hide(cartFragment);
        }
        if(categroyFragment!=null){
            fragmentTransaction.hide(categroyFragment);
        }
        if(personalFragment!=null){
            fragmentTransaction.hide(personalFragment);
        }
        switch (v.getId()){
            case R.id.ib_home:
                mIbHome.setImageResource(R.mipmap.tab_item_home_focus);
                if(homeFragment==null){
                    homeFragment=new HomeFragment();
                    fragmentTransaction.add(R.id.fl_main_navigation,homeFragment);

                }else{
                    fragmentTransaction.show(homeFragment);
                }
                break;
            case R.id.ib_category:
                mIbCategory.setImageResource(R.mipmap.tab_item_category_focus);
                if(categroyFragment==null){
                    categroyFragment=new CategroyFragment();
                    fragmentTransaction.add(R.id.fl_main_navigation,categroyFragment);
                }else{
                    fragmentTransaction.show(categroyFragment);
                }
                break;
            case R.id.ib_cart:
                mIbCart.setImageResource(R.mipmap.tab_item_cart_focus);
                if(cartFragment==null){
                    cartFragment=new CartFragment();
                    fragmentTransaction.add(R.id.fl_main_navigation,cartFragment);
                }else{
                    fragmentTransaction.show(cartFragment);
                }
                break;
            case R.id.ib_personal:
                mIbPersonal.setImageResource(R.mipmap.tab_item_personal_focus);
                if(personalFragment==null){
                    personalFragment=new PersonalFragment();
                    fragmentTransaction.add(R.id.fl_main_navigation,personalFragment);
                }else{
                    fragmentTransaction.show(personalFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }
    @Override
    public void onClick(View view) {
        select(view);
    }
}