package com.xx.webframework.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisToolsTest {
    @Autowired
    private RedisTools redisTools;

    public void test() {
        log.info("set name=张三");
        redisTools.setString("name","张三");
        String name = redisTools.getString("name");
        log.info("get name={}",name);

        Person person = new Person("张三");
        log.info("set person={}",person);
        redisTools.setObj("person",person);
        person = redisTools.getObj("person");
        log.info("get person= {}",person);


    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person{
    private String name;
}
