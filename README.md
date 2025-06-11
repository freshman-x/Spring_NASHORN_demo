# Spring Boot Nashorn 脚本执行示例

这是一个基于 Spring Boot 的示例项目，展示了如何在 Java 应用中使用 Nashorn JavaScript 引擎执行 JavaScript 代码。

## 技术栈

- Java 8
- Spring Boot 2.6.13
- Nashorn JavaScript 引擎
- Maven

## 主要依赖

- spring-boot-starter-web
- guava 32.1.3-jre
- commons-lang3 3.12.0

## 项目结构

```
src/main/java/org/example/super_demo/
├── script/          # JavaScript 脚本文件目录
└── SuperDemoApplication.java  # 应用程序入口
```

## 功能特性

- 支持执行 JavaScript 脚本
- 支持脚本的动态加载和执行

## 注意事项

- 本项目使用 Java 8，确保您的开发环境已安装 JDK 8