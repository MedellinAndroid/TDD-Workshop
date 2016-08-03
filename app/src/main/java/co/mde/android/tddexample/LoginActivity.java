package co.mde.android.tddexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

  @BindView(R.id.loading) View loadingView;
  @BindView(R.id.login) View loginView;
  @BindView(R.id.login_email) EditText emailView;
  @BindView(R.id.login_password) EditText passwordView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.login_button) public void login() {

  }
}
