import com.han.reflect.Reflect;
import com.han.user.dao.UserDao;
import com.han.user.domain.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException,
            ClassNotFoundException, InvocationTargetException, SQLException {

        // reflect 예제
        Reflect<User> reflect = new Reflect<User>();

        User user = reflect.invoke("id", "joono43");
        System.out.println(user.getId());

        user = reflect.invoke("name", "한준호");
        System.out.println(user.getName());

        user = reflect.invoke("password", "1234");
        System.out.println(user.getPassword());

        // UserDao 예제
        UserDao dao = new UserDao();

        User user1 = new User();
        user1.setId("whiteship");
        user1.setName("백기선");
        user1.setPassword("married");

        dao.add(user1);

        System.out.println(user1.getId() + " 등록 성공!!");

        User user2 = dao.get(user1.getId());
        System.out.println(user2.getName());
    }
}
