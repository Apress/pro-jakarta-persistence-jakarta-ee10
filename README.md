# Add a maven build system for:  *Pro Jakarta Persistence in Jakarta EE 10*

# To use
Import the pom.xml into your IDE.

Status : 
* Chapter 2 complete.
* Chapter 3 : Example 01,02
              03 - Seems to not be working under GlassFish and TomEE 10.0.21


# Goal: It Just works
To provide a 'it just works' experience for myself that others should be able to use.
This means:
* Required libraries will be downloaded
* DB will be run in memory to remove setup and installation effort whenever possible
* DDL, Tables, will be auto created
* Generated files will be ignored by Git

## Other changes
* Target Java 17
* Ability to switch between derby and hsqldb

## Switching between Databases
By default, *derby* is used as the underlying Database.

    mvn clean package

If you want to use *hsqldb* , activate the *hsqldb* maven profile. 

    mvn -P hsqldb clean package

During this process I often had to use hsqldb initially to get it to work due to its better documentation and adoption.
then switch back to derby afterwards. Similarly, H2 could be an alternate target since its adoption has accelerated 
due to its Spring Boot connection.

## Maven vs Gradle
While I have used both, maven by its very design is a constrained, somewhat inflexible build system out of the box.
However, that inflexibility by its very nature makes debugging problems straight forward, predictable, 
and hence its use. In addition, while IDE's can run/build Gradle projects using gradle, the use of maven allows your
IDE to convert a project it into its internal build system, which is an advantage.

## Weaknesses of the original book environment

**Code Organization**

The book used ANT patterns such as separating logically different groups of code, architectural layers,  
such as the business model, client, servlet, into different directory trees yet still under the same build directory.
In real applications these would be libraries or services and the current layout is not easily maintainable or tractable as organized.
We could of use maven plugins such as *build-helper-maven-plugin* to retain the existing directory structure. However,  
IDE's have a limited support of 3rd party plugins when importing. The easiest alternatives are:
1) Combine source directories together for convenience.
2) Use separate maven modules for each layer

The former approach most closely matches the authors original intent to show a layered architecture, and will be followed throughout.
likely
**Stale Tech Stack**

With no dependency management system and JARS checked into Git, knowing what is used is difficult.
Technology changes quickly, vulnerabilities seem to pop up everywhere, so professional developers eventually learn
the wisdom of keeping their tech stack up to date for applications. Unfortunately, this repository uses EOL  
libraries such as JUnit 4 for their development. There is also an Oracle stored procedure JAR from almost 20 years ago I 
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