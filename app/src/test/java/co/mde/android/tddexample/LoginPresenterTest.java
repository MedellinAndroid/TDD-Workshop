package co.mde.android.tddexample;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class LoginPresenterTest {

  //Collaborators
  @Mock LoginView view;
  @Mock LoginInteractor interactor;
  @Captor ArgumentCaptor<LoginInteractor.Callback> captor;

  @Before public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test public void showLoadingOnClick() throws Exception {
    //Given
    LoginPresenter presenter = new LoginPresenter(view, interactor);
    Mockito.when(view.username()).thenReturn("registered@example.com");
    Mockito.when(view.password()).thenReturn("valid");
    //When
    presenter.login();
    //Then
    Mockito.verify(view).showLoading();
  }

  @Test public void showLoginScreenAndErrorOnInteractorFailure() {
    //Given
    LoginPresenter presenter = new LoginPresenter(view, interactor);
    Mockito.when(view.username()).thenReturn("non-registered@example.com");
    Mockito.when(view.password()).thenReturn("invalid");
    //When
    presenter.login();
    Mockito.verify(interactor)
        .auth(Mockito.eq("non-registered@example.com"), Mockito.eq("invalid"), captor.capture());
    captor.getValue().onFailure();

    //Then
    Mockito.verify(view).showLogin();
    Mockito.verify(view).hideLoading();
    Mockito.verify(view).showLoginError();
  }
}