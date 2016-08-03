package co.mde.android.tddexample;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class LoginPresenterTest {

  //Collaborators
  @Mock LoginView view;
  @Mock LoginInteractor interactor;

  @Before public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test public void showLoadingOnClick() throws Exception {
    //Given
    LoginPresenter presenter = new LoginPresenterTest(view, interactor);
    Mockito.when(view.username()).thenReturn("registered@example.com");
    Mockito.when(view.password()).thenReturn("valid");
    //When
    presenter.login();
    //Then
    Mockito.verify(view).showLoading();
  }
}