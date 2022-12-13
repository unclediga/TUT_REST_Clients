# TUTUORIAL 
**Frameworks for REST-services testing**

## DBUnit examples

Introduction to DBUnit
Last modified: May 14, 2020
https://www.baeldung.com/java-dbunit
https://github.com/eugenp/tutorials/tree/master/libraries-testing/src/test/java/com/baeldung/dbunit


## Embedded Servers examples

### Apache TomEE (OpenEJB) 

[Сравнение версий сервера TomEE](https://tomee.apache.org/comparison.html)

Main differences between versions
Apache TomEE is an open source software implementation of the Jakarta EE (formally Java EE) technologies.
Different versions of Apache TomEE are available for different versions of the specifications.
The mapping between the specifications and the respective Apache TomEE versions is:

| TomEE version | Based on Tomcat | Java SE version | Micro Profile version | Jakarta EE version                              | Full list of specifications versions and implementations choices |
|---------------|-----------------|-----------------|-----------------------|-------------------------------------------------|------------------------------------------------------------------|
| 9.x           | 10.0.x          | 11              | 5.0                   | 9.1                                             | specifications versions implementations details                  |
| 8.x           | 9.0.x           | 8               | 2.0                   | 8.0                                             | specifications versions implementations details                  |
| 7.1.x         | 8.5.x           | 7 or 8          | 1.4                   | 7.0                                             | specifications versions implementations details                  |
| 7.0.x         | 8.5.x           | 7 or 8          | 7.0                   | specifications versions implementations details |                                                                  |

Начальный пример [Тестирование EJB с помощью JUnit и OpenEJB Embedded
17 ноября 2016 ](https://www.tune-it.ru/web/leksa/blog/-/blogs/тестирование-ejb-с-помощью-junit-и-openejb-embedded).
Я его не делал, но идея и ссылки пригодились.

В этом примере устарела зависимость

```xml
  <dependency>
      <groupId>org.apache.openejb</groupId>
      <artifactId>tomee-embedded</artifactId>
      <version>1.7.2</version>
      <!--1.7.2-->
      <scope>test</scope>
  </dependency>  
```
Это версия JavaEE 6 с последним релизом 1.7.5 Sep 27, 2017

Следующий сервер на JavaEE 7 релиз Sep 25, 2020

```xml
<dependency>
    <groupId>org.apache.tomee</groupId>
    <artifactId>tomee-embedded</artifactId>
    <version>7.1.4</version>
</dependency>
```

Решил взять более свежий пример [JPA Eclipselink](https://tomee.apache.org/tomee-7.1/examples/jpa-eclipselink.html) из большого [списка примеров](https://tomee.apache.org/tomee-7.1/examples/) для TomEE 7 (JavaEE 7)

```java
@Entity
public class Movie {
    <...>
}

@Stateful
public class Movies {

    @PersistenceContext(unitName = "movie-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;
    <...>
}   
```

persistece.xml
```xml

<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"
             version="2.1">
    <persistence-unit name="movie-unit" transaction-type="JTA">
        <provider>org.eclipse.persistence.jpa.PersistenceProvider</provider>
        <jta-data-source>movieDatabase</jta-data-source>
        <non-jta-data-source>movieDatabaseUnmanaged</non-jta-data-source>
        <class>ru.unclediga.example.openejb.Movie</class>
        <exclude-unlisted-classes>false</exclude-unlisted-classes>
        <properties>
            <property name="eclipselink.target-database" value="org.eclipse.persistence.platform.database.H2Platform"/>
            <property name="eclipselink.ddl-generation" value="create-tables"/>
            <property name="eclipselink.ddl-generation.output-mode" value="database"/>
        </properties>
    </persistence-unit>
</persistence>
```

<dl>
<dt>Если ставим "RESOURCE_LOCAL"</dt>

`<persistence-unit name="movie-unit" transaction-type="RESOURCE_LOCAL">`

<dd>то получаем ошибку 
   
>INFO - PersistenceUnit 'movie-unit' is a RESOURCE_LOCAL one, you'll have to manage @PersistenceContext yourself.
</dd>

<dt>Если пропускаем явное указание на Entity-классы</dt>

`<class>ru.unclediga.example.openejb.Movie</class>`

<dd>то получаем ошибку: 

```
<...>
< см. правильный лог >
<...>
INFO - All injection points were validated successfully.
INFO - OpenWebBeans Container has started, it took 331 ms.
INFO - Created Ejb(deployment-id=Movies, ejb-name=Movies, container=Default Stateful Container)
INFO - Started Ejb(deployment-id=Movies, ejb-name=Movies, container=Default Stateful Container)
INFO - Deployed Application(path=C:\Users\Doro\WORK\MyJava\TUT_REST_Clients)
[EL Info]: 2022-12-13 19:15:51.587--ServerSession(31382461)--EclipseLink, version: Eclipse Persistence Services - 2.7.9.v20210604-2c549e2208
[EL Warning]: 2022-12-13 19:15:51.896--The collection of metamodel types is empty. Model classes may not have been found during entity search for Java SE and some Java EE container managed persistence units.  Please verify that your entity classes are referenced in persistence.xml using either <class> elements or a global <exclude-unlisted-classes>false</exclude-unlisted-classes> element
SEVERE - EjbTransactionUtil.handleSystemException: Object: ru.unclediga.example.openejb.Movie@1f2eb04 is not a known Entity type.
java.lang.IllegalArgumentException: Object: ru.unclediga.example.openejb.Movie@1f2eb04 is not a known Entity type.
	at org.eclipse.persistence.internal.sessions.UnitOfWorkImpl.registerNewObjectForPersist(UnitOfWorkImpl.java:4380)
	at org.eclipse.persistence.internal.jpa.EntityManagerImpl.persist(EntityManagerImpl.java:507)
	at org.apache.openejb.persistence.JtaEntityManager.persist(JtaEntityManager.java:193)
	at ru.unclediga.example.openejb.Movies.addMovie(Movies.java:17)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	<...>
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:54)

javax.ejb.EJBException: The bean encountered a non-application exception; nested exception is: 
	java.lang.IllegalArgumentException: Object: ru.unclediga.example.openejb.Movie@1f2eb04 is not a known Entity type.

	at org.apache.openejb.core.ivm.BaseEjbProxyHandler.convertException(BaseEjbProxyHandler.java:447)
	<...>

```
</dd>
</dl>

Нормальный лог:
```
INFO - ********************************************************************************
INFO - OpenEJB http://tomee.apache.org/
INFO - Startup: Tue Dec 13 18:36:56 MSK 2022
INFO - Copyright 1999-2018 (C) Apache OpenEJB Project, All Rights Reserved.
INFO - Version: 7.1.4
INFO - Build date: 20200805
INFO - Build time: 04:01
INFO - ********************************************************************************
INFO - openejb.home = C:\Users\Doro\WORK\MyJava\TUT_REST_Clients
INFO - openejb.base = C:\Users\Doro\WORK\MyJava\TUT_REST_Clients
INFO - Created new singletonService org.apache.openejb.cdi.ThreadSingletonServiceImpl@e048a
INFO - Succeeded in installing singleton service
INFO - Using 'javax.ejb.embeddable.EJBContainer=true'
INFO - Cannot find the configuration file [conf/openejb.xml].  Will attempt to create one for the beans deployed.
INFO - Configuring Service(id=Default Security Service, type=SecurityService, provider-id=Default Security Service)
INFO - Configuring Service(id=Default Transaction Manager, type=TransactionManager, provider-id=Default Transaction Manager)
INFO - Configuring Service(id=movieDatabase, type=Resource, provider-id=Default JDBC Database)
INFO - Creating TransactionManager(id=Default Transaction Manager)
INFO - Creating SecurityService(id=Default Security Service)
INFO - Creating Resource(id=movieDatabase)
INFO - Inspecting classpath for applications: 47 urls. Consider adjusting your exclude/include.  Current settings: openejb.deployments.classpath.exclude='', openejb.deployments.classpath.include='.*'
INFO - Found EjbModule in classpath: c:\users\Doro\work\myjava\tut_rest_clients\target\classes
INFO - Found PersistenceModule in classpath: c:\users\Doro\work\myjava\tut_rest_clients\target\test-classes
INFO - Searched 47 classpath urls in 1823 milliseconds.  Average 38 milliseconds per url.
INFO - Beginning load: c:\users\Doro\work\myjava\tut_rest_clients\target\classes
INFO - Beginning load: c:\users\Doro\work\myjava\tut_rest_clients\target\test-classes
INFO - Configuring enterprise application: C:\Users\Doro\WORK\MyJava\TUT_REST_Clients
INFO - Auto-deploying ejb Movies: EjbDeployment(deployment-id=Movies)
INFO - Configuring Service(id=Default Stateful Container, type=Container, provider-id=Default Stateful Container)
INFO - Auto-creating a container for bean Movies: Container(type=STATEFUL, id=Default Stateful Container)
INFO - Creating Container(id=Default Stateful Container)
INFO - Using directory C:\Users\DORO~1\AppData\Local\Temp for stateful session passivation
INFO - Configuring Service(id=Default Managed Container, type=Container, provider-id=Default Managed Container)
INFO - Auto-creating a container for bean ru.unclediga.example.openejb.MoviesTest: Container(type=MANAGED, id=Default Managed Container)
INFO - Creating Container(id=Default Managed Container)
INFO - Using directory C:\Users\DORO~1\AppData\Local\Temp for stateful session passivation
INFO - Configuring PersistenceUnit(name=movie-unit, provider=org.eclipse.persistence.jpa.PersistenceProvider)
INFO - Auto-creating a Resource with id 'movieDatabaseNonJta' of type 'DataSource for 'movie-unit'.
INFO - Configuring Service(id=movieDatabaseNonJta, type=Resource, provider-id=movieDatabase)
INFO - Creating Resource(id=movieDatabaseNonJta)
INFO - Adjusting PersistenceUnit movie-unit <non-jta-data-source> to Resource ID 'movieDatabaseNonJta' from 'movieDatabaseUnmanaged'
INFO - Enterprise application "C:\Users\Doro\WORK\MyJava\TUT_REST_Clients" loaded.
INFO - Assembling app: C:\Users\Doro\WORK\MyJava\TUT_REST_Clients
INFO - JAVA AGENT NOT INSTALLED. The JPA Persistence Provider requested installation of a ClassFileTransformer which requires a JavaAgent.  See http://tomee.apache.org/javaagent.html
INFO - PersistenceUnit(name=movie-unit, provider=org.eclipse.persistence.jpa.PersistenceProvider) - provider time 476ms
INFO - Jndi(name="java:global/TUT_REST_Clients/Movies!ru.unclediga.example.openejb.Movies")
INFO - Jndi(name="java:global/TUT_REST_Clients/Movies")
INFO - Existing thread singleton service in SystemInstance(): org.apache.openejb.cdi.ThreadSingletonServiceImpl@e048a
INFO - OpenWebBeans Container is starting...
INFO - Adding OpenWebBeansPlugin : [CdiPlugin]
INFO - All injection points were validated successfully.
INFO - OpenWebBeans Container has started, it took 299 ms.
INFO - Created Ejb(deployment-id=Movies, ejb-name=Movies, container=Default Stateful Container)
INFO - Started Ejb(deployment-id=Movies, ejb-name=Movies, container=Default Stateful Container)
INFO - Deployed Application(path=C:\Users\Doro\WORK\MyJava\TUT_REST_Clients)
[EL Info]: 2022-12-13 18:37:00.725--ServerSession(29396209)--EclipseLink, version: Eclipse Persistence Services - 2.7.9.v20210604-2c549e2208

```

