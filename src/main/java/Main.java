import com.han.reflect.Reflect;
import com.han.user.domain.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException,
            ClassNotFoundException, InvocationTargetException {

        Reflect<User> reflect = new Reflect<User>();

        User user = reflect.invoke("id", "joono43");
        System.out.println(user.getId());

        user = reflect.invoke("name", "한준호");
        System.out.println(user.getName());

        user = reflect.invoke("password", "1234");
        System.out.println(user.getPassword());
    }
}
