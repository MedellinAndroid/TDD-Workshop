package co.mde.android.tddexample.login;

public class LoginPresenter {
  private final LoginView view;
  private final LoginInteractor interactor;

  public LoginPresenter(LoginView view, LoginInteractor interactor) {
    this.view = view;
    this.interactor = interactor;
    view.showLogin();
    view.hideLoading();
  }

  public void login() {
    view.showLoading();
    view.hideLogin();
    interactor.auth(view.username(), view.password(), new LoginInteractor.Callback() {
      @Override public void onFailure() {
        view.hideLoading();
        view.showLogin();
        view.showLoginError();
      }

      @Override public void onSuccess() {
        view.showHomeScreen();
      }
    });
  }
}
