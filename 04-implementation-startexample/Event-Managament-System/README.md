[English](#event-management-system)
[中文](#演唱会管理系统)

# Event Management System

## Overview
The event management system is a web application that uses the Spring Boot framework. This event management system includes account management, venue management, event management, and ticket management. There are three types of users on this platform: administrators, organizers, and customers.

## Project DEMO
Url: [Event Management System](https://exampleUrl.com/) (Temporarily unavailable)

## tips
EMS seems to work differently on different browsers, so it's recommended to use Chrome or Edge.

Since we have deployed the database on an Alibaba Cloud server, there may be network latency. If you encounter any issues related to the remote server, please contact us promptly, and we will assist in resolving them

## Screenshots
Login Page
![Log In](/04-implementation-startexample/Event-Managament-System/img/login.png)

Sign Up Page
![Sign Up](/04-implementation-startexample/Event-Managament-System/img/register.png)

Admin Page
![Admin Dashboard](/04-implementation-startexample/Event-Managament-System/img/admin.png)

Organiser Page
![Organiser Dashboard](/04-implementation-startexample/Event-Managament-System//img/org.png)

Customer Page
![Customer Dashboard](/04-implementation-startexample/Event-Managament-System//img/customer.png)

## Local Deployment (Docker)
### Environment Requirements
1. Java 17
2. Maven
3. Docker
4. Docker Compose

### Running the Application
Assuming that you have correctly installed and set up the above, you can run the application by following these steps:
1. Use maven to create a jar file of the application by running `mvn clean package` in the root directory of the project.
```bash
mvn clean package
```

2. Get the Java 17 by running `docker pull amazoncorretto:17-alpine`
```bash 
docker pull amazoncorretto:17-alpine
```

3. Run the docker compose command to build and run the application.
```bash
docker-compose up --build -d
```

This will build the application and run it on port 8080. You can then access the application by navigating to `localhost:8080` in your browser. This also starts the database, as such any changes you make shoudl persist between runs.

### Stopping the Application
When you are finished and want to stop the application, you can use the following command:
```bash
docker-compose down
```

## Default accounts

### Admin:
- Email: admin@ucd.ie
- Password: admin

### Organiser:
- Email: organiser@ucd.ie
- Password: organiser

### Customer:
- Email: customer@ucd.ie
- Password: customer

## Licence
MIT

## Technical Support
For support, please email le.liu1@ucdconnect.ie or create an issue in the repository.

# 演唱会管理系统

## 概述
活动管理系统是一个web应用程序使用spring boot框架。 这个活动管理系统包括账户管理，场馆管理，事件管理，票务管理。这个平台有三种类型的用户：管理员，组织者和客户。

## 快速开始
### 项目演示
网址
### 本地部署（Docker）
#### 环境要求
- JDK 17
- Maven 
- Docker
- Docker Compose

#### 运行程序
``` bash
cd 04-implementation-startexample/Event-Managament-System

mvn clean package

docker pull amazoncorretto: 17-alpine

```

这将构建应用程序并在 8080 端口运行。然后，您可以在浏览器中访问 localhost:8080 来访问应用程序。这也会启动数据库，因此您所做的任何更改都应在运行之间持久化

#### 停止应用程序
```bash
docker-compose down
```


### 默认账户

管理员:
- 邮箱: admin@ucd.ie
- 密码: admin

组织者:
- 邮箱: organiser@ucd.ie
- 密码: organiser

客户:
- 邮箱: customer@ucd.ie
- 密码: customer

## 许可证
MIT Licence

## 技术支持
如需支持，请发送邮件至 le.liu1@ucdconnect.ie 或在仓库中创建 issue。