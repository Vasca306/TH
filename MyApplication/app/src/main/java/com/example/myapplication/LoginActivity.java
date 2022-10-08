package com.example.myapplication;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password, name;
    FirebaseUser currentUser;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Create Account");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        TextView newdnewaccount = findViewById(R.id.needs_new_account);
        TextView reocverpass = findViewById(R.id.forgetp);
        mAuth = FirebaseAuth.getInstance();
        Button mlogin = findViewById(R.id.login_button);
        loadingBar = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mlogin.setOnClickListener(v -> {

            String emaill = email.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (!Patterns.EMAIL_ADDRESS.matcher(emaill).matches()) {

                email.setError("Invalid Email");
                email.setFocusable(true);

            }
            else {

                loginUser(emaill, pass);
            }
        });

        newdnewaccount.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegistrationActivity.class)));
        reocverpass.setOnClickListener(v -> showRecoverPasswordDialog());
    }

    private void showRecoverPasswordDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Recover Password");
        LinearLayout linearLayout = new LinearLayout(this);
        final EditText emailet = new EditText(this);
        emailet.setText("Email");
        emailet.setMinEms(16);
        emailet.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        linearLayout.addView(emailet);
        linearLayout.setPadding(10, 10, 10, 10);
        builder.setView(linearLayout);
        builder.setPositiveButton("Recover", (dialog, which) -> {

            String emaill = emailet.getText().toString().trim();
            beginRecovery(emaill);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

    private void beginRecovery(String emaill) {

        loadingBar.setMessage("Sending Email....");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        mAuth.sendPasswordResetEmail(emaill).addOnCompleteListener(task -> {

            loadingBar.dismiss();
            if (task.isSuccessful()) {

                Toast.makeText(LoginActivity.this, "Done sent", Toast.LENGTH_LONG).show();
            } else {

                Toast.makeText(LoginActivity.this, "Invalid Email", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loginUser(String emaill, String pass) {

        loadingBar.setMessage("Logging In....");
        loadingBar.show();


        mAuth.signInWithEmailAndPassword(emaill, pass).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {

                loadingBar.dismiss();
                FirebaseUser user = mAuth.getCurrentUser();

                if (task.getResult().getAdditionalUserInfo().isNewUser()) {

                    String email = user.getEmail();
                    String uid = user.getUid();
                    HashMap<Object, String> hashMap = new HashMap<>();
                    hashMap.put("email", email);
                    hashMap.put("uid", uid);
                    hashMap.put("name", "");
                    hashMap.put("onlineStatus", "online");
                    hashMap.put("typingTo", "noOne");
                    hashMap.put("phone", "");
                    hashMap.put("image", "");
                    hashMap.put("cover", "");
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference("Users");
                    reference.child(uid).setValue(hashMap);
                }
                Intent mainIntent = new Intent(LoginActivity.this, DashboardActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
                finish();
            }
            else {

                loadingBar.dismiss();
                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(e -> {

            loadingBar.dismiss();
            Toast.makeText(LoginActivity.this, "Email or Password is incorrect", Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
