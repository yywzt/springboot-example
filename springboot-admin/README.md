# springboot-admin
springboot-admin（Spring Boot 2.0，基于 Eureka 的实现）

### 添加依赖

```xml
<dependency>
	<groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-server</artifactId>
    <version>2.0.3</version>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    <version>2.0.0.RELEASE</version>
</dependency>
```

### 启动类添加注解，开启监控

```java
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class SpringBootAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminApplication.class, args);
    }
    
    @Configuration
    public static class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
        private final String adminContextPath;

        public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
            this.adminContextPath = adminServerProperties.getContextPath();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
            successHandler.setTargetUrlParameter("redirectTo");

            http.authorizeRequests()
                    .antMatchers(adminContextPath + "/assets/**").permitAll()
                    .antMatchers(adminContextPath + "/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
                    .logout().logoutUrl(adminContextPath + "/logout").and()
                    .httpBasic().and()
                    .csrf().disable();
        }
    }
}

```
### 配置文件

官方有给出示例，主要是配置 eureka 的地址，这里要强调说明的一点，由于 Spring Boot 2.0 的 Actuator 只暴露了 /health、/info 两个端口（为了安全考虑），所以要配置 management.endpoints.web.exposure.include 的属性，下面的配置文件中暴力了一点，配置暴露了所有的端点，由于 Spring Boot Admin 有 web UI 管理界面，配置了登录的用户名密码如下，使用了 security.user 的属性，其他的详细配置，可以查看官方文档。

```yaml
server:
  port: 8888
spring:
  application:
    name: springboot-admin
  profiles: eureka
  security:
    user:
      name: "root"
      password: "root"
eureka:
  client:
    service-url:
      #      defaultZone: http://root:root@localhost:18761/eureka/
      defaultZone: http://peer1:18760/eureka/,http://peer2:18761/eureka/
  instance:
    metadata-map:
      user.name: "root"
      user.password: "root"
management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: always
```

