import org.junit.Test;
import org.junit.runner.RunWith;
import org.repaire.mapper.UserMapper;
import org.repaire.pojo.User;
import org.repaire.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Luo on 2018/5/13.
 */
@ContextConfiguration(locations = { "classpath*:spring-mvc.xml" ,"classpath*:spring-mybatis.xml" ,"classpath*:spring-service.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTestBase extends AbstractJUnit4SpringContextTests {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test(){
       // User user = userMapper.selectByPrimaryKey(1);

        System.out.print(  userMapper.selectByExample(null).get(0).getNickName());
    }
}