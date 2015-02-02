import com.han.user.dao.UserDao;
import com.han.user.domain.User;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao dao = new UserDao();

        User user = new User();
        user.setId("joono01");
        user.setName("한준호");
        user.setPassword("1234");

        dao.add(user);

        System.out.println(user.getId() + " 등록성공!!");
    }
}
