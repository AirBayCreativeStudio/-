package com.example.demo

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.authentication.encoding.Md5PasswordEncoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class DemoApplicationTests {

    @Test
    fun md5Test() {

        //通常情况下，我们使用MD5加密方式，是通过比较加密串是否一致
        //这里使用 Spring Boot Security 的 Crypto 库
        val encoder = Md5PasswordEncoder()
        //原始串
        val password = "123456"
        //加盐
        val salt = "demo"
        //加密密码
        val encodePassword = encoder.encodePassword(password, salt)
        //比较原始加密串 和 重新加密串 的值是否一致，在kotlin语法中，字符串比较可以使用双等号
        assert(encoder.encodePassword(password, salt) == encodePassword)

    }

    @Test
    fun bCryptTest() {

        //好吧，我们按照楼上的逻辑，通常我们都这样认为的。。
        //一样的，我们使用Spring Boot Security 的 Crypto 库
        val encoder = BCryptPasswordEncoder()
        //原始串
        val password = "123456"
        //加密密码
        val encodePassword = encoder.encode(password)
        //比较原始加密串 和 重新加密串 的值是否一致，在本单元测试中，他们是不相等的！！！
        //
        //  (⊙ω⊙)   Wtf 为什么会是不一样的！！
        //
        assert(encoder.encode(password) != encodePassword)

        //应该这么玩，这样才能正确验证。。。。
        assert(encoder.matches(password,encodePassword))

    }

}
