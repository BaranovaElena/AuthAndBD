import java.sql.*;

public class DBAuthService {
    //данные для подключения задаются при установке postgresql на пк
    // по умолчанию порт 5432, юзер и пароль postgres
    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/GBChat";
    private static final String DB_USER="postgres";
    private static final String DB_PASSWORD="postgres";
    private static Connection connection;

    static {
        try {
            // подключение к бд
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection!=null)
            System.out.println("connected to DB");
        else
            System.out.println("fail connection");
    }

    //функция проверки логина и пароля
    public boolean isLoginAndPasswordRight(String login, String password) {
        try(
                //создаем запрос для бд
                //запрашиваем от базы запись, содержащую наш логин и пароль
                PreparedStatement stm = connection.prepareStatement(
                "SELECT * FROM public.\"Clients\" WHERE login='"+login+"' AND password='"+password+"'");
                //получаем из базы запись, которую мы запросили
                ResultSet resultSet = stm.executeQuery()){
            //если запись не нулевая, т.е. логин и пароль есть в базе, возвращаем успех
            if (resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
