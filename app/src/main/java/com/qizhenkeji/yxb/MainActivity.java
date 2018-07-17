package com.qizhenkeji.yxb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qizhenkeji.yxb.custom.FragmentMainTabAdapter;
import com.qizhenkeji.yxb.home.HomeFragment;
import com.qizhenkeji.yxb.user.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentMainTabAdapter tabAdapter;
    private RadioGroup rgs;
    private RadioButton tab_rb_home,tab_rb_mine;
    private FrameLayout tab_content;
    public static int currentIndex = 0;

    private Fragment mTabHomeFm = null;
    private Fragment mTabNewCarFm = null;
    private Fragment mTabSecondCarFm = null;
    private Fragment mTabMineFm = null;
    public List<Fragment> fragments = new ArrayList<>();
    public static FragmentMainTabAdapter fragmentMainTabAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife(this);
        initView();
        initFragment();
        initAdapter();

    }

    private void initFragment() {
        newHomePage();
        newMinePage();

        fragments.add(mTabHomeFm);
        fragments.add(mTabMineFm);
    }

    public void newHomePage() {
        mTabHomeFm = new HomeFragment();
    }

    public void newMinePage() {
        mTabMineFm = new UserFragment();
    }

    private void initAdapter() {

        tabAdapter = new FragmentMainTabAdapter(this, fragments, R.id.tab_content, rgs);
        tabAdapter.setOnRgsExtraCheckedChangedListener(new FragmentMainTabAdapter.OnRgsExtraCheckedChangedListener() {
            @Override
            public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {

                currentIndex = index;
            }

            @Override
            public void OnRgsGoLoginChanged(RadioGroup radioGroup, int checkedId) {
            }
        });
        fragmentMainTabAdapter = tabAdapter;
    }


    private void initView() {
        rgs = (RadioGroup) findViewById(R.id.tabs_rgs);
        tab_rb_home = (RadioButton) findViewById(R.id.tab_rb_home);
        tab_rb_mine = (RadioButton) findViewById(R.id.tab_rb_mine);
        tab_content = (FrameLayout) findViewById(R.id.tab_content);
    }

}
