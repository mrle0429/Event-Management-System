# Event Management System

## Overview
The event management system is a web application that uses the Spring Boot framework. This event management system includes account management, venue management, event management, and ticket management. There are three types of users on this platform: administrators, organizers, and customers.

## Project DEMO
Url: [Event Management System](https://exampleUrl.com/)

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

## Technical Support
For support, please email le.liu1@ucdconnect.ie or create an issue in the repository.

# Event Management System

## 概述
活动管理系统是一个web应用程序使用spring boot框架。 这个活动管理系统包括账户管理，场馆管理，事件管理，票务管理。这个平台有三种类型的用户：管理员，组织者和客户。

## 快速开始
### 项目DEMO
网址
### 本地部署（Docker）
#### 环境要求
- JDK 
- Maven 
- MySQL 

#### 安装步骤

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

## 技术支持
如需支持，请发送邮件至 le.liu1@ucdconnect.ie 或在仓库中创建 issue。