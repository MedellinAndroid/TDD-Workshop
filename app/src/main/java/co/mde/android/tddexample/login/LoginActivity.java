package co.mde.android.tddexample.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.mde.android.tddexample.R;
import co.mde.android.tddexample.home.HomeActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {

  @BindView(R.id.loading) View loadingView;
  @BindView(R.id.login) View loginView;
  @BindView(R.id.login_email) EditText emailView;
  @BindView(R.id.login_password) EditText passwordView;

  private LoginPresenter presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
    presenter = new LoginPresenter(this, new MockLoginInteractor());
  }

  @OnClick(R.id.login_button) public void login() {
    presenter.login();
  }

  @Override public String username() {
    return emailView.getText().toString();
  }

  @Override public String password() {
    return passwordView.getText().toString();
  }

  @Override public void hideLogin() {
    loginView.setVisibility(View.GONE);
  }

  @Override public void showLoading() {
    loadingView.setVisibility(View.VISIBLE);
  }

  @Override public void showLogin() {
    loginView.setVisibility(View.VISIBLE);
  }

  @Override public void hideLoading() {
    loadingView.setVisibility(View.GONE);
  }

  @Override public void showLoginError() {
    Toast.makeText(this, R.string.login_error, Toast.LENGTH_SHORT).show();
  }

  @Override public void showHomeScreen() {
    startActivity(new Intent(this, HomeActivity.class));
    finish();
  }
}
