# elfinder-2.x-spring-boot
a web image manager based on spring boot + freemarker + elfinder 2.x + jquery ui

Technologies:
1)Java Development Kit 8 ;
2)Spring Boot 1.5.22 with freemarker ;
3)Jquery-ui 1.12.1 ;
4)Elfinder  2.1.39 ;


Overview of differences and usage with servlet versions:

1.the servlet .xml file split into 2 spring-boot config files.
"ElfinderBeansConfiguration.java": define all the beans the system needs
"ElfinderConnectorServlet.java": the servlet used by the original program. 

2.add a file download servlet, turn url from some kind of hash code to clear text, reduce the complexity of file references.
such like from "some-servlet/connector?cmd=file&target=images_L3Bob3RvLzE1MjUzMTM5NDg1MjkuanBn&_t=1591144312&exculsiveFolder=photo" change to "/another-servlet/images/photo/1525313948529.jpg". this allows front-end developers to assemble file URLs as they did in good old times, making it easier for them to accept the module import and operation changes

3.modified the entrance to the volume configuration. we can customize all volume initialization operations above, such as reading configuration information from properties/json/database,and here we define the volume using the configuration "VolumesProperties.java" & "volumes.properties" to define volumes of the web site.

4.if a more advanced volume configuration is required, it is recommended to refer to "StaticFsServiceFactory.java" to customize your scenario, such as setting the right path based on user profiles

5.used an advanced elfinder webjar package to solve the problem of manual downloading of elfinder files that will be named "connector",these can be referred to in the "pom.xml".

6.don't forget to put "mime.types" in the resources directory *_^

Next plan:
The implementation of "elfinder-2.x-servlet" is straightforward, so we made this spring boot demo based on this branch.
I'm going to modify all the servlet entrances to the controller, are there partners interested in this? We can learn and solve the problems we all encounter.
