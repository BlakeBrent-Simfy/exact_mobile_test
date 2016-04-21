package com.exactmobile.jean.challenge;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.exactmobile.jean.challenge.enums.Action;
import com.exactmobile.jean.challenge.enums.BundleExtras;
import com.exactmobile.jean.challenge.enums.Prefs;
import com.exactmobile.jean.challenge.fragments.EnterNameFragment;
import com.exactmobile.jean.challenge.fragments.ImageItemListFragment;
import com.exactmobile.jean.challenge.fragments.ShowNameFragment;
import com.prashantsolanki.secureprefmanager.SecurePrefManager;
import com.prashantsolanki.secureprefmanager.SecurePrefManagerInit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, EnterNameFragment.OnSubmitRequired, ShowNameFragment.OnActionRequired {

    private FragmentManager fragmentManager;
    private android.support.v4.app.Fragment imageListFragment, enterNameFragment, showNameFragment;
    private String enteredName;
    private boolean returningUser = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();
        initializeSecurePrefs();

        //We default to the image list fragment @ when the activity starts
        navigateToImageListFragment();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_func1) {
            navigateToImageListFragment();

        } else if (id == R.id.nav_func2) {
            navigateToEnterNameFragment();

            String prefsSavedName = SecurePrefManager.with(this)
                    .get(Prefs.NAME.name())
                    .defaultValue("")
                    .go();
            if (prefsSavedName.isEmpty()) {
                navigateToEnterNameFragment();
            } else {
                enteredName = prefsSavedName;
                returningUser = true;
                navigateToShowNameFragment();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initializeSecurePrefs() {
        new SecurePrefManagerInit.Initializer(getApplicationContext())
                .useEncryption(true)
                .initialize();
    }

    @Override
    public void submitAction(String name) {
        SecurePrefManager.with(this)
                .set(Prefs.NAME.name())
                .value(name)
                .go();
        returningUser = false;
        enteredName = name;
        navigateToShowNameFragment();
    }

    @Override
    public void processAction(Action action) {
        if (action == Action.BACK) {
            navigateToEnterNameFragment();
        } else if (action == Action.CLOSE) {
            finish();
        }
    }

    void navigateToImageListFragment() {
        if (imageListFragment == null) {
            imageListFragment = new ImageItemListFragment();
        }
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, imageListFragment)
                .commit();
        setTitle(getString(R.string.func1));
    }

    void navigateToEnterNameFragment() {
        if (enterNameFragment == null) {
            enterNameFragment = new EnterNameFragment();
        }
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, enterNameFragment)
                .commit();
        setTitle(getString(R.string.func2));
    }

    void navigateToShowNameFragment() {
        Bundle args = new Bundle();
        args.putString(BundleExtras.EXTRAS.name(), enteredName);
        args.putBoolean(BundleExtras.RETURNING.name(), returningUser);
        if (showNameFragment == null) {
            showNameFragment = new ShowNameFragment();
        }
        showNameFragment.setArguments(args);

        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, showNameFragment)
                .commit();
        setTitle(getString(R.string.func2));

    }
}
