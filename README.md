# docker-maven-build

### Dockerfile:  
```
FROM openjdk:8-jdk-alpine  
VOLUME /tmp  
//Docker, one or more build-args where not consumed [JAR_FILE]  
ARG JAR_FILE=/target/dockerapp.jar  
COPY ${JAR_FILE} /opt/app.jar  
ENTRYPOINT ["java","-jar","/opt/app.jar"]  
```
### CMD Docker build:  
//to build docker image using the dockerfile and tag it as test/dockerapp:1.0  
> docker build -t test/dockerapp:1.0 .  

### CMD Docker Run:  
//run the build image as container  
> docker run -d -rm --hostname localhost -p 8080:8080 test/dockerapp:1.0  

### Maven Docker build:  
```

<build>
 <finalName>${project.name}-${project.version}</finalName>
 <plugins>
     <plugin>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-maven-plugin</artifactId>
         <configuration>
             <outputDirectory>target</outputDirectory>
         </configuration>
     </plugin>
     <plugin>
         <groupId>org.codehaus.mojo</groupId>
         <artifactId>exec-maven-plugin</artifactId>
         <version>1.6.0</version>
         <executions>
             <execution>
                 <id>docker-package</id>
                 <phase>package</phase>
                 <goals>
                     <goal>exec</goal>
                 </goals>
                 <configuration>
                     <executable>docker</executable>
                     <workingDirectory>${project.basedir}</workingDirectory>
                     <arguments>
                         <argument>build</argument>
                         <argument>.</argument>
                         <argument>-t</argument>
                         <argument>${project.groupId}/${project.artifactId}:${project.version}</argument>
                         <argument>--build-arg</argument>
                         <argument>JAR_FILE=target/${project.build.finalName}.jar</argument>
                     </arguments>
                 </configuration>
             </execution>
         </executions>
     </plugin>
 </plugins>
</build>

```
