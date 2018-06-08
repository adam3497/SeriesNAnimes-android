package com.altarosprojects.seriesanimes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
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

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView txtUsername, txtEmail, txtPassword, txtSignedWith;
    Boolean signedFace;
    Boolean signedGoogle;
    private GoogleSignInClient mGoogleSignInClient;
    private CallbackManager callbackManager;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        callbackManager = CallbackManager.Factory.create();

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        txtUsername = (TextView) findViewById(R.id.txt_username);
        txtEmail = (TextView) findViewById(R.id.txt_email);
        txtPassword = (TextView) findViewById(R.id.txt_password);
        txtSignedWith = (TextView) findViewById(R.id.txt_signed_with);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            signedFace = extras.getBoolean("accountSignedFacebook", false);
            signedGoogle = extras.getBoolean("accountSignedGoogle", false);

            if(signedFace){
                //load data from shared preferences
                SharedPreferences prefs = getSharedPreferences("com.altarosprojects.seriesanimes", Context.MODE_PRIVATE);
                String username = prefs.getString("facebookName", "not found");
                String email = prefs.getString("facebookEmail", "not found");
                String userId = prefs.getString("facebookUserId", "not found");

                txtUsername.setText(username);
                txtEmail.setText(email);
                txtPassword.setText(userId);
                txtSignedWith.setText(getResources().getString(R.string.signed_with).replace("{0}", "Facebook Account"));
            }
            else if(signedGoogle){
                GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
                if(acct != null){
                    String personName = acct.getDisplayName();
                    String personGivenName = acct.getGivenName();
                    String personfamilyName = acct.getFamilyName();
                    String personEmail = acct.getEmail();
                    String personId = acct.getId();
                    Uri personPhoto = acct.getPhotoUrl();

                    txtUsername.setText(personName + ", " + personGivenName);
                    txtEmail.setText(personEmail);
                    txtPassword.setText(personId);
                    txtSignedWith.setText(getResources().getString(R.string.signed_with).replace("{0}", "Gogle Account"));
                }

            }
            else{
                //info where info[0] = username, info[1] = email, info[2] = password
                Set<String> info = new HashSet<>();
                info = sharedPreferences.getStringSet("appAccountInfo", null);
                if(info != null){
                    ArrayList<String> infoArray = new ArrayList<>(info);

                    txtUsername.setText(infoArray.get(0));
                    txtEmail.setText(infoArray.get(1));
                    txtPassword.setText(infoArray.get(2));
                    txtSignedWith.setText(getResources().getString(R.string.signed_with).replace("{0}", "Application Account"));
                }
                else{
                    Toast.makeText(this, getResources().getString(R.string.app_account_error), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
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
        // automatically handle clicks on the Home/Up btn_login_create_account, so long
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

        switch (id){
            case R.id.nav_opt_log_out:
                logOut();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * This method manage the logout of all the accounts
     * Currently, this app just allow login with Facebook, Google or App Account
     * Also, this method finish this activity and launch the Login Activity again.
     */
    private void logOut(){
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        final Intent loginRequestIntent = new Intent(MainActivity.this, LoginActivity.class);

        //first, we need to check with what account was logged the user
        //and then, make the needed request to logout
        if(signedFace){
            progressDialog.setMessage(getResources().getString(R.string.main_loggin_out_msg).replace("{0}", "Facebook"));
            progressDialog.show();

            //obtain the current token, if true the user is logging
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

            if(isLoggedIn){
                //the user is logging, so we need to logout this session
                new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions", null, HttpMethod.DELETE, new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        LoginManager.getInstance().logOut();
                        progressDialog.dismiss();
                        startActivity(loginRequestIntent);
                        finish();
                    }
                }).executeAsync();
            }
            else{
                //the user is currently logout
                progressDialog.dismiss();
                startActivity(loginRequestIntent);
                finish();
            }
        }
        else if(signedGoogle){
            progressDialog.setMessage(getResources().getString(R.string.main_loggin_out_msg).replace("{0}", "Google"));
            progressDialog.show();
            //make the request to logout from Google Account
            mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    //when the request is complete, finish this activity and start the Login Activity
                    progressDialog.dismiss();
                    startActivity(loginRequestIntent);
                    finish();
                }
            });
        }
        else{
            progressDialog.setMessage(getResources().getString(R.string.main_loggin_out_msg).replace("{0}", txtUsername.getText().toString()));
            progressDialog.show();
            sharedPreferences.edit().putBoolean("accountLoggedIn", false).apply();
            sharedPreferences.edit().putStringSet("appAccountInfo", null).apply();
            startActivity(loginRequestIntent);
            finish();
        }
    }
}
