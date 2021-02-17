package jhaturanga.views.login;

import java.io.IOException;
import java.util.Optional;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jhaturanga.controllers.Controller;
import jhaturanga.controllers.login.LoginController;
import jhaturanga.controllers.login.LoginControllerImpl;
import jhaturanga.views.PageLoader;

public final class LoginViewImpl implements LoginView {

    private Stage stage;

    private LoginController controller;

    // declaration of element from fxml

    @FXML
    private TextField userNameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Text errorText;

    /*
     * TODO cambiare il metodo di switch delle finestre, e implementere quindi set e
     * get controller
     */

    @FXML
    void initialize() {
        try {
            this.controller = new LoginControllerImpl(this);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void switchRegisterView(final Event event) throws IOException {
        PageLoader.switchPage(this.getStage(), "register");
    }

    @Override
    public Controller getController() {
        return this.controller;
    }

    @Override
    public void setController(final Controller controller) {
        this.controller = (LoginController) controller;
    }

    @Override
    public void setStage(final Stage stage) {
        this.stage = stage;

    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    @FXML
    @Override
    public void login(final Event event) {
        if (userNameTextField.getText().isEmpty() | passwordTextField.getText().isEmpty()) {
            errorText.setText("completare i campi correttamente");
        } else if (this.controller.login(userNameTextField.getText(), passwordTextField.getText())
                .equals(Optional.empty())) {
            errorText.setText("Username o Password errati");
        } else {
            try {
                PageLoader.switchPage(this.getStage(), "chessboard");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void register(final Event event) {

        if (userNameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            errorText.setText("completare i campi correttamtne");
        } else {
            this.controller.register(userNameTextField.getText(), passwordTextField.getText());

            try {
                PageLoader.switchPage(this.getStage(), "login");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    @FXML
    public void backToLogin(final Event event) throws IOException {
        PageLoader.switchPage(this.getStage(), "login");

    }

    @FXML
    public void settingButton(final Event event) throws IOException {
        PageLoader.switchPage(this.getStage(), "settings");
    }

}
