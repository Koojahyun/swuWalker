package com.example.koo_m.stepwithswumans;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements SensorEventListener, BackgroundResultReceiver.Receiver {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private Fragment fragment;
    FragmentManager fragmentManager = getSupportFragmentManager();
    public static int count;
    private long lastTime;
    private float speed;
    private float lastX;
    private float lastY;
    private float lastZ;
    private float x, y, z;

    private static final int SHAKE_THRESHOLD = 800;
    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;

    private SensorManager sensorManager;
    private Sensor accelerormeterSensor;

    public BackgroundResultReceiver mReceiver;
    boolean isFinish = false;

    public static SQLiteDatabase mDatabase;
    public static String currentDate;
    public static String weekAgo;

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

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = dateFormat.format(new Date());

        Calendar calWeekAgo = Calendar.getInstance();
        calWeekAgo.add(Calendar.DATE, -6);
        weekAgo = dateFormat.format(calWeekAgo.getTime());

        mDatabase = openOrCreateDatabase("WALKCOUNT", MODE_PRIVATE, null);
        mDatabase.execSQL("CREATE TABLE IF NOT EXISTS WALK(Id INTEGER PRIMARY KEY AUTOINCREMENT, Date DATE NOT NULL, Count INTEGER NOT NULL);");

        try {
            Cursor cursor = MainActivity.mDatabase.rawQuery("SELECT COUNT FROM WALK WHERE DATE='" + currentDate + "';", null);
            cursor.moveToFirst();
            if (cursor.getColumnCount() != 0) {
                count = cursor.getInt(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Intent DBreset = new Intent(getApplicationContext(), DBService.class);
        PendingIntent pDBreset = PendingIntent.getService(this, 0, DBreset, 0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pDBreset);
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
        } else if (fragmentManager.getBackStackEntryCount() > 0) {
            int backStack = fragmentManager.getBackStackEntryCount() - 1;
            fragmentManager.getFragments().get(backStack).onResume();
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

    @Override
    public void onStart() {
        super.onStart();

        if (accelerormeterSensor != null)
            sensorManager.registerListener(this, accelerormeterSensor, SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    public void onStop() {
        super.onStop();

        if (sensorManager != null)
            sensorManager.unregisterListener(this);

        Toast.makeText(getApplicationContext(),
                "onStop", Toast.LENGTH_LONG).show();

        mReceiver = new BackgroundResultReceiver(new Handler());
        mReceiver.setReceiver(this);

        final Intent intent = new Intent(Intent.ACTION_SYNC, null, this, BackgroundService.class);

        intent.putExtra("count", count);
        intent.putExtra("receiver", mReceiver);
        intent.putExtra("command", "increase count");
        startService(intent);

    }


    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);
            if (gabOfTime > 100) {
                lastTime = currentTime;
                x = event.values[SensorManager.DATA_X];
                y = event.values[SensorManager.DATA_Y];
                z = event.values[SensorManager.DATA_Z];

                speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    ++count;
                    int backStack = fragmentManager.getBackStackEntryCount();
                    fragmentManager.getFragments().get(backStack).onResume();
                }

                lastX = event.values[DATA_X];
                lastY = event.values[DATA_Y];
                lastZ = event.values[DATA_Z];
            }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Toast.makeText(getApplicationContext(),
                "onRestart", Toast.LENGTH_LONG).show();

        final Intent intent = new Intent(Intent.ACTION_SYNC, null, this, BackgroundService.class);
        stopService(intent);

    }

    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case BackgroundService.STATUS_RUNNING:

                Toast.makeText(this, "STATUS_RUNNING", Toast.LENGTH_LONG).show();
                break;


            case BackgroundService.STATUS_FINISHED:

                count = resultData.getInt("back");
                String str = Integer.toString(count);

                Fragment2.textView.setText(str);

                Toast.makeText(this, "STATUS_FINISHED", Toast.LENGTH_LONG).show();

                break;

            case BackgroundService.STATUS_ERROR:
                Toast.makeText(this, "STATUS_ERROR", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
