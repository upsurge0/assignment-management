# Assign.ly‎
## _An Assignment Management Tool_

Assign.ly‎ is an assignment managment solution, designed to help teachers create and manage assignments.
It is made using the following.
- Spring Boot
- Spring JPA
- Shorthand CSS Framework
- Remote MYSQL

Demo: [demo]

## Features

- Create Student and Teacher accounts.
- Teachers can assignments and attach files.
- Students can create submissions.
- Teachers can view Submissions made by students and download files.

## Tech

Assin.ly‎ uses a number of free and open source projects to work properly:

- [Maven] -  Build and manage any Java-based project.
- [Shorthand CSS Framework] - Feature rich CSS framework for the new decade

And of course Assin.ly‎ itself is open source with a [public repository]
 on GitHub.

## Installation

Assin.ly‎ can be built using Maven.

```sh
cd assignment-management
mvn package
```

## Docker

Assin.ly‎ is very easy to install and deploy in a Docker container.

#### Build
```sh
cd assignment-management
docker build . -t assignment-managment
```
#### Run
```sh
docker run -p 80:8080 --it -d assignment-management
```

   [Shorthand CSS Framework]: <https://github.com/shorthandcss/shorthand>
   [Maven]: <https://maven.apache.org/>
   [Srping Boot]: <https://github.com/spring-projects/spring-boot>
   [public repository]: <https://github.com/upsurge0/assignment-management>
   [demo]: <http://ec2-157-175-87-244.me-south-1.compute.amazonaws.com/>

