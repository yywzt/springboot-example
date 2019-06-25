

### 通过this.xxx()调用当前类的方法导致事务失效问题

> 同一个方法内 非事务方法内部调用事务方法（@Transactional）会导致事务失效

#### 原因：

在应用系统调用声明@Transactional 的目标方法时，Spring Framework 默认使用 AOP 代理，在代码运行时生成一个代理对象，再由这个代理对象来统一管理，当在Service实现类直接调用内部方法时，其本质是通过this对象来调用的方法，而不是代理对象，因为会出现事务失效的情况

#### 解决方法：

1. 遇到方法自调用问题，显示通代理对象调用

   ```java
   public void noTransation2_1() {
   	TestTransactionService testTransactionService = (TestTransactionService)AopContext.currentProxy();
       testTransactionService.transation();
   }
   ```

2. 通过SpringUtils.getBean(this.getClass());然后调用 其方法

    ```java
    public void noTransation2_2() {
        TestTransactionService testTransactionService = SpringUtils.getBean(this.getClass());
        testTransactionService.transation();
    }
    ```
3. 事务与非事务方法至于不同类中

   test1.java

   ```java
   @Autowired
   private TestTransactionService testTransactionService;

   public void noTransation2() {
       testTransactionService.transation();
   }
   ```

   TestTransactionService.java

   ```java
   @Transactional
   public void transation() {
       log.info("method transation start");
       TestTransaction testTransaction = initData();
       int insert = testTransactionMapper.insert(testTransaction);
       log.info("method transation end");
   }
   ```