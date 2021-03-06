# binbatis

## 仿造MyBatis实现了一个简易版ORM框架




## 注意：

```xml
1.testmybatis是测试模块，主要实现模块都在最顶层的src文件中。
2.目前只是实现了查询的功能，其他的功能等以后有时间了可以完善下。
3.做这个项目的初衷就是为了能够亲手体验下写框架的感觉，最重要的还是学习源码
4.限于本人的水平，代码写的比较简陋，误喷。
```



## 背景

**1.自从接触了MyBatis，现在开发任何需要引入Mysql的项目时都会不由自主的想到MyBatis。**

**2.如果你问我为什么要使用MyBatis，我可能会说，这个好像比较火，大家都在用，而且，确实挺方便友好的，所以我就用咯。**

**3.我估计很多人都是这样想的，我们在接触到任何一门技术的时候切记一定不要盲目的去使用它，你连它是什么你都不知道，你只知道好用，别人在用，你就用了，这件事情非常的不严谨。**

**4.在这里我想要好好总结下，关于为什么我们要使用MyBatis，以及它的底层实现原理。**

**5.正所谓，知己知彼，百战不殆，要想真正了解一个新的概念，最好的做法就是自己亲手尝试一下！**

**6.所以在这里根据网上的案例还有结合MyBatis的源码手写了一个MyBatis框架。**





## 项目设计难点

**1.对于ORM框架来说，核心其实无非就是两点，一是动态代理，二是封装JDBC的操作。**

**2.动态代理的目的就是可以不用将sql写在代码中，我们只需要面向接口编程，只需要写好具体sql的接口，即可实现对数据库的CRUD，从而减少代码的污染和组件之间的耦合度。**

**3.通过xml文件的形式将sql配置好，下次只需要动态的加载一下配置文件即可，不需要重新修改代码。**

**4.而传统的使用JDBC的操作都太繁琐了，不仅需要自己控制连接的开关和关闭还需要处理各种返回结果，很是麻烦。**

**5.所以ORM框架诞生了！一种只需要你写好接口和一点简单的sql就能灵活的实现对数据库的CRUD。**





## 项目实现

### 架构图

<img src="https://s1.ax1x.com/2020/05/10/Y8sCLj.jpg" alt="Y8sCLj.jpg" style="zoom:200%;" />





### 实现细节

**1.首先我们需要写一个Configuration来封装所有的配置信息，包括jdbc等基本信息。**

**2.还需要一个MappedStatement对象来封装每一个mapper.xml文件中的sql映射字段。**

**3.因为考虑到每一个xml文件中有多个sql映射字段，所有我们将MappedStatement存在Map中，并将这个Map一起封装到Configuration中。**

**4.通过这些基本信息来生成一个SqlSessionFactory来管理所有的数据库连接实例，为每一个用户生成一个SqlSession。**

**5.SqlSession内部封装了一个MapperProxy对象用来生成用户自定义接口的代理对象，也叫做伪装对象。**

**6.这个代理对象可以拦截用户的所有调用请求，并将请求全部交给Executor。**

**7.Executor内部封装了基本的JDBC操作，从而可以执行用户的调用请求。**

**8.执行完毕之后将请求结果，也就是经过JDBC封装好的ResultSet交给ResultHandler。**

**9.ResultHandler将ResultSet通过反射将结果转换为用户自定义的Bean或者集合类返回给用户。**







## 欢迎学习交流和star！！！

**QQ：1114011786**
**微信：LB21724**



