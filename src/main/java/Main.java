import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.han.reflect.Reflect;
import com.han.user.dao.*;
import com.han.user.domain.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main {

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException {

        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao dao = context.getBean("userDao", UserDao.class);

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.get("unknown_id");
    }

    @Test
    public void count() throws SQLException {

        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao dao = context.getBean("userDao", UserDao.class);

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(new User("joono41", "한대웅", "0919"));
        assertThat(dao.getCount(), is(1));

        dao.add(new User("joono42", "한대성", "0101"));
        assertThat(dao.getCount(), is(2));

        dao.add(new User("joono43", "한대정", "0102"));
        assertThat(dao.getCount(), is(3));
    }

    @Test
    public void addAndGet() throws SQLException {

        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDao dao = context.getBean("userDao", UserDao.class);

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        User user1 = new User("joono42", "한대웅", "0919");
        User user2 = new User("joono43", "한준호", "0128");

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        User userget1 = dao.get(user1.getId());
        assertThat(userget1.getName(), is(user1.getName()));
        assertThat(userget1.getPassword(), is(user1.getPassword()));

        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getName(), is(user2.getName()));
        assertThat(userget2.getPassword(), is(user2.getPassword()));

    }

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

//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

        // UserDao 예제
        UserDao dao = context.getBean("userDao", UserDao.class);

        User user1 = new User("whiteship", "백기선", "married");

        dao.add(user1);

        System.out.println(user1.getId() + " 등록 성공!!");

        User user2 = dao.get(user1.getId());

        if (!user1.getName().equals(user2.getName())) {
            System.out.println("테스트 실패 (name)");
        }
        else if (!user1.getPassword().equals(user2.getPassword())) {
            System.out.println("테스트 실패 (password)");
        }
        else {
            System.out.println("조회 테스트 성공!!");
        }



        /*ApplicationContext context =
                new AnnotationConfigApplicationContext(CountingDaoFactory.class);

        UserDao dao = context.getBean("userDao", UserDao.class);

        dao.get("whiteship");

        CountingConnectionMaker ccm =
                context.getBean("connectionMaker", CountingConnectionMaker.class);

        System.out.println("Connection counter : " + ccm.getCounter());*/
    }
}
