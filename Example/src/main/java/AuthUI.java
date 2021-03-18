import javax.swing.*;
import java.awt.*;

public class AuthUI extends JFrame {

    private JPanel  loginPanel; //окно
    private DBAuthService service = new DBAuthService(); //класс для работы с бд

    public AuthUI() {
        setMainFrame();
        initAuthUI();
    }

    private void setMainFrame() {
        setTitle("AuthAndDB");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initAuthUI() {
        loginPanel = new JPanel();
        loginPanel.setBackground(Color.white);
        loginPanel.setLayout(new FlowLayout());

        JLabel authLabel = new JLabel("Для входа введите логин и пароль");
        authLabel.setPreferredSize(new Dimension(
                this.getWidth()-20, 25));

        JLabel loginLabel = new JLabel("Логин : ");
        JTextField loginField = new JTextField();
        loginField.setPreferredSize(new Dimension(
                this.getWidth()-loginLabel.getWidth()-50, 25));

        JLabel passwordLabel = new JLabel("Пароль : ");
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(
                this.getWidth()-loginLabel.getWidth()-50, 25));

        JButton submitButton = new JButton("Войти");

        // обработчик нажатия кнопки
        submitButton.addActionListener(e -> {
            //спрашиваем у базы верный ли логин пароль
            boolean result = service.isLoginAndPasswordRight(
                    loginField.getText(), String.valueOf(passwordField.getPassword()));
            if (result) {
                //если верный, то не знаю что дальше, другое окно например открыть
            }
            else {
                //иначе очищаем поля ввода и логинемся дальше
                loginField.setText("");
                passwordField.setText("");
            }
        });

        loginPanel.add(authLabel);
        loginPanel.add(loginLabel);
        loginPanel.add(loginField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(submitButton);

        loginPanel.setVisible(true);
        add(loginPanel);
        setVisible(true);
    }
}