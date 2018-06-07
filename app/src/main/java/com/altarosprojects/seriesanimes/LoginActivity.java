package com.altarosprojects.seriesanimes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.altarosprojects.seriesanimes.db.DatabaseModel;
import com.altarosprojects.seriesanimes.db.DatabaseUsersHelper;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 101;
    private static final String TAG = "LoginActivty";
    private Button btnCreateAccount, btnLogin;
    private SignInButton ibtnGoogle;
    private LoginButton ibtnFacebook;
    private EditText etxUsername, etxPassword;

    private DatabaseUsersHelper usersHelper;
    private CallbackManager callbackManager;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        callbackManager = CallbackManager.Factory.create();
        setViews();
        usersHelper = new DatabaseUsersHelper(getApplicationContext());
    }

    @Override
    protected void onStart() {
        super.onStart();
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if(isLoggedIn){
            loggedFacebook();
        }
        else{
            // Check for existing Google Sign In account, if the user is already signed in
            // the GoogleSignInAccount will be non-null.
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
            updateUI(account);
        }
    }

    private void loggedFacebook() {
        Toast.makeText(getApplicationContext(), "Inicio de sesión éxito con la cuenta de Facebook", Toast.LENGTH_SHORT).show();
        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        mainIntent.putExtra("accountSignedFacebook", true);
        mainIntent.putExtra("accountSignedGoogle", false);
        startActivity(mainIntent);
        finish();
    }

    private void updateUI(GoogleSignInAccount account) {
        if(account != null){
            Toast.makeText(getApplicationContext(), "Inicio de sesión éxito con la cuenta de Google", Toast.LENGTH_SHORT).show();
            Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
            mainIntent.putExtra("accountSignedGoogle", true);
            mainIntent.putExtra("accountSignedFacebook", false);
            startActivity(mainIntent);
            finish();
        }
    }

    private void setViews() {
        btnCreateAccount = (Button) findViewById(R.id.btn_login_create_account);
        btnLogin = (Button) findViewById(R.id.btn_login);
        etxUsername = (EditText) findViewById(R.id.etx_username_login);
        etxPassword = (EditText) findViewById(R.id.etx_password_login);
        ibtnFacebook = (LoginButton) findViewById(R.id.ibtn_facebook_login);
        ibtnGoogle = (SignInButton) findViewById(R.id.ibtn_google_login);

        //action for login google button
        ibtnGoogle.setSize(SignInButton.SIZE_STANDARD);
        ibtnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        //action for login facebook button
        ibtnFacebook.setReadPermissions(Arrays.asList("public_profile", "email"));
        //callback registration
        ibtnFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Snackbar.make(ibtnFacebook, "Inicio éxitoso", Snackbar.LENGTH_SHORT).show();

                //app code
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try{
                            //get profile information
                            String name = "";
                            String email = "";
                            String uriPicture = "";
                            String userId = "";

                            if (object.getString("name") != null) {
                                name = object.getString("name");
                            }

                            if(object.getString("id") != null){
                                userId = object.getString("id");
                            }

                            if (object.getString("email") != null) {
                                email = object.getString("email");
                            }
                            if (object.getString("picture") != null) {
                                JSONObject imagen = new JSONObject(object.getString("picture"));
                                JSONObject imagen2 = new JSONObject(imagen.getString("data"));
                                uriPicture = imagen2.getString("url");
                            }

                            // save profile information to preferences
                            SharedPreferences prefs = getSharedPreferences("com.altarosprojects.seriesanimes", Context.MODE_PRIVATE);
                            prefs.edit().putString("facebookName", name).apply();
                            prefs.edit().putString("facebookEmail", email).apply();
                            prefs.edit().putString("facebookUriPicture", uriPicture).apply();
                            prefs.edit().putString("facebookUserId", userId).apply();
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link,gender,birthday,email,picture");
                request.setParameters(parameters);
                request.executeAsync();

                loggedFacebook();
            }

            @Override
            public void onCancel() {
                Snackbar.make(ibtnFacebook, "Inicio cancelado", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Snackbar.make(ibtnFacebook, "Se produjo un error al iniciar con Facebook", Snackbar.LENGTH_SHORT).show();
            }
        });

        btnLogin.setEnabled(false);
        btnLogin.setBackgroundColor(getResources().getColor(R.color.colorButtonDisable));
        btnLogin.setTextColor(getResources().getColor(R.color.colorButtonTextDisable));

        etxUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(etxUsername.getText().length() > 0 && etxPassword.getText().length() > 0){
                    btnLogin.setEnabled(true);
                    btnLogin.setTextColor(getResources().getColor(R.color.colorButtonsText));
                    btnLogin.setBackgroundColor(getResources().getColor(R.color.colorButtonsBackground));
                }
                else{
                    btnLogin.setEnabled(false);
                    btnLogin.setBackgroundColor(getResources().getColor(R.color.colorButtonDisable));
                    btnLogin.setTextColor(getResources().getColor(R.color.colorButtonTextDisable));
                }
            }
        });

        etxPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(etxUsername.getText().length() > 0 && etxPassword.getText().length() > 0){
                    btnLogin.setEnabled(true);
                    btnLogin.setTextColor(getResources().getColor(R.color.colorButtonsText));
                    btnLogin.setBackgroundColor(getResources().getColor(R.color.colorButtonsBackground));
                }
                else{
                    btnLogin.setEnabled(false);
                    btnLogin.setBackgroundColor(getResources().getColor(R.color.colorButtonDisable));
                    btnLogin.setTextColor(getResources().getColor(R.color.colorButtonTextDisable));
                }
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(registerIntent);
                finish();
            }
        });

        //listener for Login Button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage(getResources().getString(R.string.login_validate_user_msg));
                progressDialog.show();

                final SQLiteDatabase db = usersHelper.getReadableDatabase();

                // Columns which we need.
                String[] projection = {
                        DatabaseModel.UsersTable.COLUMN_1,
                        DatabaseModel.UsersTable.COLUMN_2,
                        DatabaseModel.UsersTable.COLUMN_3
                };

                String selection = DatabaseModel.UsersTable.COLUMN_1 + " = ?";
                String[] selectionArgs = {etxUsername.getText().toString()};

                Cursor cursor = db.query(DatabaseModel.UsersTable.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, null);

                if(!(cursor.getCount() == 0)){
                    while(cursor.moveToNext()){
                        String username = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseModel.UsersTable.COLUMN_1));
                        String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseModel.UsersTable.COLUMN_2));
                        String password = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseModel.UsersTable.COLUMN_3));

                        validateUser(username, email, password, progressDialog);
                    }
                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_not_found_user),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void validateUser(String username, String email, String password, ProgressDialog progressDialog) {
        if(etxPassword.getText().toString().equals(password)){
            progressDialog.dismiss();
            Toast.makeText(this, getResources().getString(R.string.login_found_user), Toast.LENGTH_SHORT).show();
            Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
            String[] info = {username, email, password};
            mainIntent.putExtra("accountSignedGoogle", false);
            mainIntent.putExtra("accountSignedFacebook", false);
            mainIntent.putExtra("normalSigned", info);
            startActivity(mainIntent);
        }
        else{
            progressDialog.dismiss();
            Toast.makeText(this, getResources().getString(R.string.login_wrong_password),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }else{
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }

    }
}
