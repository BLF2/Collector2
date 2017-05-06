package net.blf2.service;

import net.blf2.entity.PrimaryKeyValue;
import net.blf2.service.impl.FormService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by blf2 on 17-5-4.
 * 测试 form service
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages="net.blf2.dao")
public class FormServiceTest {
    @Resource
    private FormService formService;
    @Test
    public void testFormService(){
        PrimaryKeyValue primaryKeyValue = new PrimaryKeyValue();
    }
}
