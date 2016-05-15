package com.example.koo_m.stepwithswumans;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private Fragment fragment;
    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        setupDrawerContent(navigationView);

        fragmentManager.beginTransaction().add(R.id.flContent, new Fragment1()).commit();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                fragmentClass = Fragment1.class;
                break;
            case R.id.nav_second_fragment:
                fragmentClass = Fragment2.class;
                break;
            case R.id.nav_third_fragment:
                fragmentClass = Fragment3.class;
                break;
            case R.id.nav_forth_fragment:
                fragmentClass = Fragment4.class;
                break;
            default:
                fragmentClass = Fragment1.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).addToBackStack(null).commit();
        //fragment 자채에서 Navigation Drawer View 접근해서 menuItem 을 체크하게 만들었습니다
        //menuItem.setChecked(true);
        drawer.closeDrawers();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            //여기서부터 설명 시작!
            //fragmentManager 의 backStack 에는 지금까지 우리가 열어본 fragment 들이 차곡차곡 싸였있음!
            //그럼으로 backStack 에 싸여있는 fragment 가 0 이상일때 back 버튼을 누르면 밑에있는 코드가 실행됩니다 (한번만 프래그먼트를 실행했으면 fragmentManager.getBackStackEntryCount() 는 1, 두번했으면 2 등등)
        } else if (fragmentManager.getBackStackEntryCount() > 0) {
            //backStack 이 0보다 컸기때문에 밑에있는 3줄을 실행!
            //fragmentManager.getBackStackEntryCount() 는 현제 몇게의 fragment 가 싸여있는지 보여줍니다, 그러니까 그것에서 1을 빼면 바로 전 fragment 가 있는 위치!
            //int backStack 에 그 위치를 저장합니다.
            int backStack = fragmentManager.getBackStackEntryCount() - 1;
            //이제 그 위치에 있는 fragment 를 가져옵니다. (fragmentManager.getFragment().get(backStack)
            //가져온 fragment 에 있는 .onResume() function 을 실행해주면 끝~~!!! (지금 각 fragment 의 onResume()에는 setTitle 과 타이틀의 이름을 Toast 로 보여주는 코드, 그리고 drawer 의 아이템 선택이 사용되고있습니다)
            fragmentManager.getFragments().get(backStack).onResume();
            //이제 다했으니까 가장 위에있는 fragment 를 지워줍니다~!
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}
