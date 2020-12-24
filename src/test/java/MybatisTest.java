import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiacool.domain.User;
import com.jiacool.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    //将java中的Date类型转化为数据库中的毫秒类型
    @Test
    public void test1() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession(true);//自动提交事务
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //模拟 User 对象
        User user = new User();
        user.setUsername("youzi");
        user.setPassword("1287");
        user.setBirthday(new Date());

        mapper.save(user);
        sqlSession.close();
    }

    //测试数据库类型转化为java中所需的类型
    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession(true);//自动提交事务
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.findById(7);
        System.out.println("user中的birthday:"+user.getBirthday());
        sqlSession.close();
    }

    @Test
    public void test3() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession(true);//自动提交事务
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

//        设置分页的相关参数:  当前页+每页显示的条数
        PageHelper.startPage(1,3);

        List<User> list = mapper.findAll();
        for (User user : list) {
            System.out.println(user);
        }

//        获得与分页相关的参数
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        System.out.println("当前页:"+pageInfo.getPageNum());
        System.out.println("每页显示的条数:"+pageInfo.getPageSize());
        System.out.println("总条数:"+pageInfo.getTotal());
        System.out.println("总页数:"+pageInfo.getPages());
        System.out.println("上一页:"+pageInfo.getPrePage());
        System.out.println("下一页:"+pageInfo.getNextPage());
        System.out.println("是否是第一页:"+pageInfo.isIsFirstPage());
        System.out.println("是否是最后一页:"+pageInfo.isIsLastPage());

        sqlSession.close();
    }
}
