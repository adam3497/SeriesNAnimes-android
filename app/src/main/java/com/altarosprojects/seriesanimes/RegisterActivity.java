package com.altarosprojects.seriesanimes;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.altarosprojects.seriesanimes.db.DatabaseModel;
import com.altarosprojects.seriesanimes.db.DatabaseUsersHelper;

public class RegisterActivity extends AppCompatActivity {

    private EditText etxUsername, etxEmail, etxPassword, etxConfirmPassword;
    private Button btnCreateAccount;

    private DatabaseUsersHelper usersHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        usersHelper = new DatabaseUsersHelper(getApplicationContext());

        setViews();
    }

    private void setViews() {
        etxUsername = (EditText) findViewById(R.id.etx_register_username);
        etxEmail = (EditText) findViewById(R.id.etx_register_email);
        etxPassword = (EditText) findViewById(R.id.etx_register_password);
        etxConfirmPassword = (EditText) findViewById(R.id.etx_register_password_validate);
        btnCreateAccount = (Button) findViewById(R.id.btn_create_account);

        btnCreateAccount.setEnabled(false);
        btnCreateAccount.setBackgroundColor(getResources().getColor(R.color.colorButtonDisable));
        btnCreateAccount.setTextColor(getResources().getColor(R.color.colorButtonTextDisable));
        etxUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(etxUsername.getText().length() > 0 && etxEmail.getText().length() > 0 && etxPassword.getText().length() > 0
                        && etxConfirmPassword.getText().length() > 0){
                    btnCreateAccount.setEnabled(true);
                    btnCreateAccount.setTextColor(getResources().getColor(R.color.colorButtonsText));
                    btnCreateAccount.setBackgroundColor(getResources().getColor(R.color.colorButtonsBackground));
                }
                else{
                    btnCreateAccount.setEnabled(false);
                    btnCreateAccount.setBackgroundColor(getResources().getColor(R.color.colorButtonDisable));
                    btnCreateAccount.setTextColor(getResources().getColor(R.color.colorButtonTextDisable));
                }
            }
        });

        etxEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(etxUsername.getText().length() > 0 && etxEmail.getText().length() > 0 && etxPassword.getText().length() > 0
                        && etxConfirmPassword.getText().length() > 0){
                    btnCreateAccount.setEnabled(true);
                    btnCreateAccount.setTextColor(getResources().getColor(R.color.colorButtonsText));
                    btnCreateAccount.setBackgroundColor(getResources().getColor(R.color.colorButtonsBackground));
                }
                else{
                    btnCreateAccount.setEnabled(false);
                    btnCreateAccount.setBackgroundColor(getResources().getColor(R.color.colorButtonDisable));
                    btnCreateAccount.setTextColor(getResources().getColor(R.color.colorButtonTextDisable));
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
                if(etxUsername.getText().length() > 0 && etxEmail.getText().length() > 0 && etxPassword.getText().length() > 0
                        && etxConfirmPassword.getText().length() > 0){
                    btnCreateAccount.setEnabled(true);
                    btnCreateAccount.setTextColor(getResources().getColor(R.color.colorButtonsText));
                    btnCreateAccount.setBackgroundColor(getResources().getColor(R.color.colorButtonsBackground));
                }
                else{
                    btnCreateAccount.setEnabled(false);
                    btnCreateAccount.setBackgroundColor(getResources().getColor(R.color.colorButtonDisable));
                    btnCreateAccount.setTextColor(getResources().getColor(R.color.colorButtonTextDisable));
                }
            }
        });

        etxConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(etxConfirmPassword.getText().toString().equals(etxPassword.getText().toString())){
                    etxConfirmPassword.setTextColor(getResources().getColor(R.color.colorCorrectPasswordConfirm));
                    btnCreateAccount.setEnabled(true);
                    btnCreateAccount.setTextColor(getResources().getColor(R.color.colorButtonsText));
                    btnCreateAccount.setBackgroundColor(getResources().getColor(R.color.colorButtonsBackground));
                }
                else{
                    etxConfirmPassword.setTextColor(getResources().getColor(R.color.colorIncorrectPasswordConfirm));
                    btnCreateAccount.setEnabled(false);
                    btnCreateAccount.setBackgroundColor(getResources().getColor(R.color.colorButtonDisable));
                    btnCreateAccount.setTextColor(getResources().getColor(R.color.colorButtonTextDisable));
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(etxUsername.getText().length() > 0 && etxEmail.getText().length() > 0 && etxPassword.getText().length() > 0
                        && etxConfirmPassword.getText().length() > 0 && etxConfirmPassword.getText().toString()
                        .equals(etxPassword.getText().toString())){
                    btnCreateAccount.setEnabled(true);
                    btnCreateAccount.setTextColor(getResources().getColor(R.color.colorButtonsText));
                    btnCreateAccount.setBackgroundColor(getResources().getColor(R.color.colorButtonsBackground));
                }
                else{
                    btnCreateAccount.setEnabled(false);
                    btnCreateAccount.setBackgroundColor(getResources().getColor(R.color.colorButtonDisable));
                    btnCreateAccount.setTextColor(getResources().getColor(R.color.colorButtonTextDisable));

                }
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase dbWritte = usersHelper.getWritableDatabase();
                SQLiteDatabase dbRead = usersHelper.getReadableDatabase();

                final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
                progressDialog.setMessage(getResources().getString(R.string.register_msg_while_create_account));
                progressDialog.show();

                // Columns which we need.
                String[] projection = {
                        DatabaseModel.UsersTable.COLUMN_1,
                        DatabaseModel.UsersTable.COLUMN_2,
                        DatabaseModel.UsersTable.COLUMN_3
                };

                String selection = DatabaseModel.UsersTable.COLUMN_1 + " = ?";
                String[] selectionArgs = {etxUsername.getText().toString()};

                Cursor cursor = dbRead.query(DatabaseModel.UsersTable.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, null);

                if(!(cursor.getCount() == 0)){
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.register_already_exist_username),
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        String username = etxUsername.getText().toString();
                        String email = etxEmail.getText().toString();
                        String password = etxPassword.getText().toString();

                        ContentValues values = new ContentValues();
                        values.put(DatabaseModel.UsersTable.COLUMN_1, username);
                        values.put(DatabaseModel.UsersTable.COLUMN_2, email);
                        values.put(DatabaseModel.UsersTable.COLUMN_3, password);

                        long newRowId = dbWritte.insert(DatabaseModel.UsersTable.TABLE_NAME, null, values);

                        Toast.makeText(RegisterActivity.this, getResources()
                                .getString(R.string.register_created_user_successfully), Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();

                        onBackPressed();
                    }
                    catch (SQLException e){
                        e.printStackTrace();
                        Toast.makeText(RegisterActivity.this, getResources()
                                        .getString(R.string.register_created_user_unsuccessfully), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
