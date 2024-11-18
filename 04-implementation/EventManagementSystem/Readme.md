# 配置文档

## 为了执行该应用程序，您需要安装以下内容：
1. Java 17（不需要下载）
2. Maven

## 运行应用程序
假设您已正确安装并设置上述内容，则可以按照以下步骤运行应用程序：
1. Checkout Dev/gyh分支，确保update到最新版本
2. 在idea重新打开04-implementation下的EventManagementSystem文件夹作为新项目
3. 在高级系统设置里配置MAVEN_HOME环境变量，位置是Maven解压后bin的上一级目录
4. 使用Maven下载项目所有依赖，在终端运行`mvn clean install -U`
```bash
mvn clean install -U
```

