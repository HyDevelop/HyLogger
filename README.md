<h1 align="center">
    <a href="#!">
        <img src="https://i.imgur.com/4dkZaN1.png" height="200">
    </a>
    <h4 align="center">
    妈妈快看, 那个人的日志会变色!!
    </h4>
</h1>

<br>

<a name="introduction"></a>
介绍:
--------



<br>

<a name="maven"></a>
Maven 导入:
--------

没有添加JitPack的Repo的话首先添加Repo, 在pom里面把这些粘贴进去:

```xml
<repositories>
    <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
    </repository>
</repositories>
```

然后添加这个库:

```xml
<dependency>
    <groupId>com.github.hydevelop</groupId>
    <artifactId>HyLogger</artifactId>
    <version>2.5.19</version>
</dependency>
```

然后ReImport之后就导入好了!

<br>

<a name="gradle"></a>
Gradle 导入:
--------

没有添加JitPack的Repo的话首先添加Repo, 在pom里面把这些粘贴进去:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

然后添加这个库:

```gradle
dependencies {
    implementation 'com.github.hydevelop:HyLogger:2.5.19'
}
```

<!-- 每次更新都要手动改这些版本号好烦的_(:з」∠)_... -->

#### [其他导入(SBT / Leiningen)](https://jitpack.io/#hydevelop/HyLogger/2.5.19)

<br>

<a name="development"></a>
开发:
--------

#### 1. 创建实例管理器:

```java
LoggerInstanceManager lim = new LoggerInstanceManager(日志环境 ...);
```

例子: 如果需要一边在后台输出带颜色的一边在文件里输出不带颜色的, 写成这样:

```java
LoggerInstanceManager lim = new LoggerInstanceManager(new ConsoleColoredEnv(), new FileEnv("logs", "log"));
```

可用的日志环境:

```java
ConsoleEnv        // 无颜色的控制台环境
ConsoleColoredEnv // 带颜色的控制台环境 (使用Jansi类库, 控制台不支持颜色的话也不会乱码)
FileEnv           // 无颜色的文件环境
FileColoredEnv    // 带颜色的文件环境 (大部分编辑器不支持, 所以像我一样的RGB厨以外不推荐使用ww)
```

#### 2. 创建/获取实例:

```java
// 注意: 可以有多个实例, 每个实例可以有不同的前缀和是否输出Debug,<br> 
//       但是同一个实例管理器下的所有实例都有同样的输出环境.

HyLogger logger = lim.getLoggerInstance(前缀, 是否输出Debug);
```

例子: 如果前缀是Main, 然后不输出Debug: (这个很容易懂吧...

```java
HyLogger logger = lim.getLoggerInstance("Main", false);
```

例子#2: 如果前缀是线程号...:

```java
HyLogger logger = lim.getLoggerInstance("线程#" + Thread.currentThread().getId(), false);
```

#### 3. 使用实例:

```java
logger.log("一条Log消息"); // 这些是不同输出级别的日志
logger.debug("一条Debug消息"); // Debug日志只有开了debug开关才会输出
logger.error("一条Error消息");
logger.warning("一条Warning消息");
logger.logRAINBOW("一条彩虹消息wwwwww"); // TODO: 随机颜色改为定向渐变ww
```

#### 4. 添加颜色和格式:

```java
// 颜色其实就是一个Enum啦...
// 直接用字符串的+就行了_(:з」∠)_

// 注意: 必须有支持颜色的环境才有效 (废话!

AnsiColor.RESET  // 同时重置颜色和格式
AnsiFormat.RESET // 同样是同时重置颜色和格式

AnsiColor.BLACK  // 黑色
AnsiColor.RED    // 红色
AnsiColor.GREEN  // 原谅色 <3
AnsiColor.YELLOW // 黄色
AnsiColor.BLUE   // 蓝色
AnsiColor.PURPLE // 紫色
AnsiColor.CYAN   // 青色
AnsiColor.WHITE  // 白色

AnsiFormat.HIGH_INTENSITY // 加♂粗
AnsiFormat.LOW_INTENSITY  // 变细...?
AnsiFormat.ITALIC         // 斜体
AnsiFormat.UNDERLINE      // 下划线
AnsiFormat.BLINK          // 闪! (意义不明
AnsiFormat.RAPID_BLINK    // 更快的闪 (应该大部分后台都不支持这两个闪...
AnsiFormat.REVERSE_VIDEO  // 反色
AnsiFormat.INVISIBLE_TEXT // 隐身 (意义不明 #2

// 不用看了真的没有中划线和魔法随机ww
```

例子:

```java
logger.log(AnsiColor.GREEN + "" + AnsiFormat.HIGH_INTENSITY + "当然是选择原谅她!");
```

#### 5. 定向渐变, 用RGB码获取ANSI码: TODO

<br>

<a name="license"></a>
开源条款: [MIT License](/LICENSE)
--------
