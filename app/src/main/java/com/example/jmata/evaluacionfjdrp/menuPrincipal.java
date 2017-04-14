package com.example.jmata.evaluacionfjdrp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;


import com.example.jmata.evaluacionfjdrp.adapter.TabsPagerAdapter;
import com.example.jmata.evaluacionfjdrp.data.DBAdapter;
import com.example.jmata.evaluacionfjdrp.data.Usuario;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by jmata on 08/07/2016.
 */
public class menuPrincipal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //android.app.ActionBar.TabListener


    private android.app.ActionBar ab;
    private DBAdapter db;
    private CircleImageView imageUser;
    //private ImageView imageUser;
    private String img_str;
    private TabsPagerAdapter tabsAdapter;
    private TextView lblDrawerName, lblDrawerUser;
    private Tools tools;
    private Usuario mUsuario;
    private ViewPager vp;


    // Tab titles
    String[] tabTitles = {"Tab1", "Tab2", "Tab3"};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);

        db = new DBAdapter(this);
        tools = new Tools(this);
        DrawerLayout dwl = (DrawerLayout)findViewById(R.id.drawer_layout);
        CoordinatorLayout mCoordinator = (CoordinatorLayout)findViewById(R.id.coordMenuPrincipal);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setElevation(0);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, dwl, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dwl.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mUsuario = tools.getDatosUsuario(tools.getStringPreferences("user"));

        View header = navigationView.getHeaderView(0);
        lblDrawerName = (TextView) header.findViewById(R.id.lblDrawerName);
        lblDrawerUser = (TextView) header.findViewById(R.id.lblDrawerUser);
        imageUser = (CircleImageView) header.findViewById(R.id.imageUser);


        if(mUsuario.getImgString()!=null){
            String data = mUsuario.getImgString();
            byte[] byteImage = Base64.decode(data,Base64.DEFAULT);
            Bitmap decodedImage = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
            Bitmap scalatedImage = Bitmap.createScaledBitmap(decodedImage,500,350,true);
            imageUser.setImageBitmap(scalatedImage);
        }else{
            imageUser.setImageResource(R.drawable.user);
        }

        lblDrawerName.setText(mUsuario.getNombre());
        lblDrawerUser.setText(mUsuario.getUsuario());




        Window wd = this.getWindow();
        wd.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        wd.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            wd.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }

        tabsAdapter = new TabsPagerAdapter(this.getSupportFragmentManager());
        vp = (ViewPager) findViewById(R.id.pager);
        vp.setAdapter(tabsAdapter);

        TabLayout mtabLayout = (TabLayout)findViewById(R.id.tabs);
        mtabLayout.setupWithViewPager(vp);
        //ab.setDisplayHomeAsUpEnabled(false);

        //for(String tabTitle : tabTitles){
            //ab.addTab(ab.newTab().setText(tabTitle).setTabListener(menuPrincipal.this));
        //}

        /*vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ab.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            case android.R.id.home:
                Intent intent = new Intent(menuPrincipal.this, myLogin.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.nav_cerrar_sesion:
                String mensaje = tools.getStringPreferences("user")+" "+"Deseas cerrar la sesión?";
                AlertDialog dialog = new AlertDialog.Builder(menuPrincipal.this)
                        .setTitle("Cerrar sesión").setMessage(mensaje)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent cerrarSesion = new Intent(menuPrincipal.this, myLogin.class);
                                startActivity(cerrarSesion);
                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();
                default:
                    return false;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            return false;
        }else{
            return true;
        }
        //return super.onKeyDown(keyCode, event);
    }

    /*public class TabsPagerAdapter extends FragmentPagerAdapter{

        public TabsPagerAdapter (FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch(position){
                case 0:
                    return new imagesFragment();
                case 1:
                    return new priceFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }


    /*@Override
    public void onTabSelected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {
        vp.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(android.app.ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }*/
}
