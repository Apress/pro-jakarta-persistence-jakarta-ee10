# Add a maven build system for:  *Pro Jakarta Persistence in Jakarta EE 10*
# Status :

Most Examples have an added README.md which documents the startup URL.

* Chapter 2 complete.

| Example                                | TomcatEE 9 | GlassFish 6 | WildFly 26 | Derby |   Hssqldb   | Comments                                                                                                                                                                                                                                                  |
|----------------------------------------|:----------:|:---------:|:----------:|:-----:|:-----------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Chapter 3**                          |
| 01 Stateless Session Bean              |     Y      |      Y    |            |  NA   |     NA      |
| 02 Stateless Session Bean No Interface |     Y      |      Y    |            |  NA   |     NA      |
| 03 Stateless Session Bean Lifecycle    |     Y      |      Y    |            |  NA   |     NA      |
| 04 Stateful Session Bean               |     Y      |      Y    |            |  NA   |     NA      |
| 05 Stateful Session Bean Lifecycle     |     Y      |      Y    |            |   Y   |      N      | Only works with Derby due to [annotations](examples/Chapter3/05-sfsbLifecycleExample/model/src/main/java/examples/stateful/OrderBrowser.java) hardcoding db info.<br/> This could be resolved using a Spring profile, or by using XML as Example 02 does. |
| 06 Singleton Session Bean              |     N      |      N    |            |  NA   |     NA      | Also tried EE5 version of source code with GlassFish 05, no luck.                                                                                                                                                                                         |
| 07 Servlet Example                     |     Y      |      Y    |            |  NA   |     NA      |
| 08 Dependency Lookup                   |     Y      |      Y    |     Y      |  NA   |     NA      | This Code was not an InitialContext example but instead used an EJB Context like Example 09. Rewritten to use the InitialContext approach to match book. <br/>WildFly made it easy to find correct context string                                         |
| 09 EJB Lookup                          |     N      |      Y    |     Y      |  NA   |     NA      |                                                                                                                                                                                                                                                           |                                                                                                                                                         |
| 10 Field Injection                     |     Y      |      Y    |     Y      |  NA   |     NA      |                                                                                                                                                                                                                                                           |
| 11 Setter Injection                    |     Y      |      Y    |     Y      |  NA   |     NA      |                                                                                                                                                                                                                                                           |
| 12 CDI                                 |     N      |     N*    |     N      |   Y   |             | Original code as written will only work in Glassfish if persistence.xml is NOT deployed in WAR.  Use `mvn -P skip-persistence clean package'  to build this version<br/> For now I am skipping all other Chapter3 code.                                   |
| 13 Container Managed Transaction       |            |           |            |       |             | Skipped                                                                                                                                                                                                                                                   |
| 14 Transactional Interceptor           |            |           |            |       |             | Skipped                                                                                                                                                                                                                                                   |
| 15 User Transaction                    |            |           |            |       |             | Skipped                                                                                                                                                                                                                                                   |
| 16 Employee Service                    |            |           |            |       |             |                                                                                                                                                                                                                                                           |



* Chapter 4:
* Chapter 5:
* Chapter 6:
* Chapter 7:
* Chapter 8:
* Chapter 9:
* Chapter 10:
* Chapter 11:
* Chapter 12:
* Chapter 13:
* Chapter 14:
* Chapter 15:

GlassFish =- GlasFish 6.2.5
TomcatEE 9 = TomEE 9 plus M-9
WildFly 6 = Version 26.1.2

# Building

To Build targeting Derby Db

    mvn clean package

There are other build options described [later](#other-build-options)

# IDE Usage
Import the pom.xml into your IDE.


# Goal: It Just works
To provide a 'it just works' experience for myself that others should be able to use.
This means:
* Required libraries will be downloaded
* DB will be run in memory to remove setup and installation effort whenever possible
* DDL, Tables, will be auto created
* Generated files will be ignored by Git

## Other changes
* Target Java 17 - But should build under Java 11 if desired.
* Ability to switch between derby and hsqldb

## Other build options
By default, *derby* is used as the underlying Database.

    mvn clean package

If you want to use *hsqldb* , activate the *hsqldb* maven profile. 

    mvn -P hsqldb clean package

During this process it was useful to use hsqldb initially to get it to work due to its better documentation and adoption.
then switch back to derby afterwards. Similarly, H2 is also a good alternate db for learning
due to its Spring Boot connection.

## Maven vs Gradle
While I have used both, maven by its very design is a constrained, somewhat inflexible build system out of the box.
However, that inflexibility by its very nature makes debugging problems straight forward, predictable, 
and hence its use. In addition, while IDE's can run/build Gradle projects using gradle, the use of maven allows your
IDE to convert a project into its internal build system, which is an advantage.

## Original book environment

**Code Organization**

The book used ANT patterns such as separating logically different groups of code, architectural layers,  
such as the business model, client, servlet, into different directory trees yet still under the same build directory.
In real applications these would be libraries or services. That layout while convenient,  is tractable as organized.
We could use a maven plugins such as *build-helper-maven-plugin* to retain the existing directory structure. However,  
IDE's have a limited support of 3rd party plugins when importing. The easiest alternatives are:
1) Combine source directories together for convenience under src/main/java.
2) Use separate maven modules for each layer

The former approach most closely matches the authors original intent to show a layered architecture, and will be used.

**Unknown dependency versions**

With no dependency management system and JARS checked into Git, knowing what versions are used is very time consuming.
Technology changes quickly, and vulnerabilities seem to pop up everywhere. Professional developers eventually learn
the wisdom and sanity of using dependency management to keep their tech stack up to date.

There is also an Oracle stored procedure JAR from almost 20 years ago I 
will eventually have to come to terms with. So do take note of whether a library is still supported before importing to 
your project. Given time, I hope to address these items. 

## Finally
This will be a slow linear process, added as I go through the book. I will start at chapter 2, and march forward.
Eventually , a complete parent pom will emerge.

# Apress Source Code

This repository accompanies [*Pro Jakarta Persistence in Jakarta EE 10*](https://link.springer.com/book/10.1007/978-1-4842-7443-9) by  Lukas Jungmann, Mike Keith, Merrick Schincariol, and Massimo Nardone (Apress, 2022).

![Cover image](978-1-4842-7442-2.jpg)

Download the files as a zip using the green button, or clone the repository to your machine using Git.

## Releases

Release v1.0 corresponds to the code in the published book, without corrections or updates.

## Contributions

See the file Contributing.md for more information on how you can contribute to this repository.