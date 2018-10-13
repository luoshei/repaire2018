import org.junit.Test;
import org.repaire.mapper.RepaireappMapper;
import org.repaire.pojo.RepaireappUnion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.SimpleFormatter;

/**
 * Created by Luo on 2018/5/8.
 */
public class TestWx extends SpringTestBase{


    private RepaireappMapper mapper;

    @Test
    public void test(){
        ApplicationContext ctx = new FileSystemXmlApplicationContext( "classpath:spring-*.xml");
        mapper = (RepaireappMapper) ctx.getBean("RepaireappMapper");
       // RepaireappUnion union = mapper.selectRepaireappAndUserinfo();
      /*String path = "C:/Program Files/Apache Software Foundation/Tomcat 8.0/webapps/gdpbx/WEB-INF/classes";
       System.out.print(System.getProperty("finalName"));
        ResourceBundle resource = ResourceBundle.getBundle("weixin");   //读取属性文件
        System.out.print(resource.keySet());
        String requestUrl = resource.getString("url");  //请求地址 https://api.weixin.qq.com/sns/jscode2session
       System.out.print(requestUrl);
        try {
           // String str = "\\xE8\\xBD\\xA9\\xE6\\xAF\\x85";
            ;
           // System.out.print(new SimpleDateFormat("yyyyMMddHHmmss" + "8230").format(new Date()));
            //str= URLEncoder.encode(str,"ISO-8859-1");
            //StringBuffer dorm = new StringBuffer("8栋230");
            //dorm.deleteCharAt(dorm.indexOf("栋"));

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
