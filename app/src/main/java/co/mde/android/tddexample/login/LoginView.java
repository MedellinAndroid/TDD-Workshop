package co.mde.android.tddexample.login;

public interface LoginView {
  String username();

  String password();

  void hideLogin();

  void showLogin();

  void hideLoading();

  void showLoading();

  void showLoginError();

  void showHomeScreen();
}
