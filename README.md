<h1 align="center">
  <a href="#!">
    <img src="https://i.imgur.com/4dkZaN1.png" height="200">
  </a>
</h1>
<h4 align="center">
  妈妈快看, 那个人的日志会变色!!
</h4>
<h5 align="center">
  <a href="#maven">Maven导入</a>&nbsp;&nbsp;
  <a href="#introduction">介绍</a>&nbsp;&nbsp;
  <a href="#development">开发</a>&nbsp;&nbsp;
  <a href="#license">开源条款</a>
</h5>

<br>

<p align="center">
  <img src="https://i.imgur.com/TqgyUJt.png">
</p>

<br>

<a name="introduction"></a>
介绍 (v2.5.24):
--------

这是一个支持颜色和文件输出的日志工具, 理论上颜色输出支持所有支持ANSI ESC Code的控制台.

#### 已实现功能:

* **v2.5.19**
* 同时向多个环境实例输出日志
* 控制台环境
* 带颜色的控制台环境 (如果控制台不支持颜色也不会乱码)
* 文件输出环境
* 带颜色的文件输出环境 (不推荐, 因为文件编辑器不支持颜色的话会乱码)
* 四个日志级别 (Log, Debug, Error, Warning)
* 日志显示时间
* Debug和Error级别日志显示当前执行的类路径和行数
* 可以有多个实例
* 每个实例可以用同样的环境同时有不同的前缀
*  
* **v2.5.20**
* 用RGB获取ANSI颜色代码
* 定向单行渐变 (影响一行, 或一串字)
*  
* **v2.5.21**
* 自定义每个日志级别的输出格式
* 可以调是否强制输出颜色
*  
* **v2.5.22**
* 自定义颜色支持级别 (Default, Preset Only, Passthrough, Forced)
* 多点单行渐变
*
* **v2.5.23**
* 定向二维渐变 (影响一个字符串数组, 输出字符画的时候特别有用)
* 多点二维渐变 (带渐变角度)
* 计时器Logger

#### 待实现(TODO)的功能:

* 无视原有颜色的全局渐变

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
    <version>2.5.24</version>
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
    implementation 'com.github.hydevelop:HyLogger:2.5.24'
}
```

<!-- 每次更新都要手动改这些版本号好烦的_(:з」∠)_... -->

#### [其他导入(SBT / Leiningen)](https://jitpack.io/#hydevelop/HyLogger/2.5.24)

<br>

<a name="development"></a>
开发:
--------

### 1. 创建实例管理器:

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

### 2. 创建/获取实例:

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

### 3. 使用实例:

```java
logger.log("一条Log消息"); // 这些是不同输出级别的日志
logger.debug("一条Debug消息"); // Debug日志只有开了debug开关才会输出
logger.error("一条Error消息");
logger.warning("一条Warning消息");

// 这个渐变详细教程在下面
logger.fancy.logGradient("一条从橙色渐变到粉色的Log消息\n",
        new Color(255, 140, 0),
        new Color(255, 0, 128)); 
```

### 4. 更改输出格式:

```java
lim.setFormat(日志级别, 新的格式); // 给某个日志级别设置输出格式
```

格式里可用的变量:

| 变量占位符   | 用处              | 例子 |
| :------------: | :------------: | :------------: |
| {time}           | 当前时间        | 18-07-06 22:05:41 |
| {prefix}         | 日志前缀        | Thread-1 |
| {message}    | 输出内容        | 竜神の剣を喰らえ! |
| {st.class}       | 发日志的类     | cc.moecraft.Test |
| {st.method}  | 发日志的方法 | main |
| {st.line}         | 发日志的行数 | 31 |
| {st.full}          | 上面三个一起 | cc.moecraft.Test.main:31 |

颜色用了简写替换, 用的颜色码和Minecraft的颜色码一样, &1 到 &f.
[完整颜色码表](https://i.imgur.com/MSdHuMW.jpg)

格式表:

| 格式简写 | 代表着什么 |
| :------------: | :------------: |
| &l | 加粗 |
| &o | 斜体 |
| &n | 下划线 |

例子 (默认格式):

```java
setFormat(LOG,     "&f[&5{time}&f] [&1{prefix}&f] [&aINFO&f] &r{message}&r");
setFormat(DEBUG,   "&f[&5{time}&f] [&1{prefix}&f] [&bDEBUG&f(&e{st.full}&f)] &b{message}&r");
setFormat(ERROR,   "&f[&5{time}&f] [&1{prefix}&f] [&cERROR&f(&e{st.full}&f)] &c{message}&r");
setFormat(WARNING, "&f[&5{time}&f] [&1{prefix}&f] [&cWARNING&f] &e{message}&r");
```

### 5. 添加颜色和ANSI格式预设:

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

### 6. 添加自定义RGB颜色:

```java
// 注意: RGB颜色不是所有后台都支持
//       大部分有独立主题配置的后台都不支持
//       比如IntelliJ IDEA自带的那个就不支持

AnsiRGB.toAnsi(红, 绿, 蓝); // 用RGB获取ANSI颜色码

new AnsiRGB(颜色对象).toAnsi(); // 用java.awt.Color颜色对象获取ANSI颜色码
```

例子:

```java
logger.log(AnsiRGB.toAnsi(45, 194, 80) + "当然是选择原谅她!");
```

### 7. Logger.fancy 特效日志:

#### 7.1. 双向/多点 线性渐变:

```java
// 线性渐变:

logger.fancy.logGradient(消息, 颜色或渐变对象); 

// 注意: 必须要支持RGB颜色的后台才支持定向渐变
// 注意: 颜色对象的话至少要两个才行

// 渐变预设在GradientPresets类里
```

例子:

```java
// 使用颜色渐变:
logger.fancy.logGradient("测试橙色渐变到粉色", new Color(255, 140, 0), new Color(255, 0, 128)); 

// 使用渐变预设渐变:
logger.fancy.logGradient("##############测试蓝到紫到红多点渐变##############", GradientPresets.BPR);
```

#### 7.2. 二维文字块渐变:

```java
// 先创建一个文字块对象:
// 注意: 句子不用加换行, 每句直接逗号分开
Paragraph paragraph = new Paragraph(句子, 句子, 句子 ...);

// 渐变输出
// 注意: 这个角度单位不是radian, 而是degrees
logger.fancy.logGradient(paragraph, 渐变对象, 角度);
```

例子:

```java
// 创建文字块对象
Paragraph paragraph = new Paragraph(
        "┬ ┬┬ ┬┬  ┌─┐┌─┐┌─┐┌─┐┬─┐",
        "├─┤└┬┘│  │ ││ ┬│ ┬├┤ ├┬┘",
        "┴ ┴ ┴ ┴─┘└─┘└─┘└─┘└─┘┴└─"
);

// 用渐变预设输出
logger.fancy.logGradient(paragraph, GradientPresets.BPR, 15);

// 用自定义颜色创建渐变对象输出
logger.fancy.logGradient(paragraph, 
        new MultiPointLinearGradient(
                new Color(64, 224, 208), 
                new Color(255, 140, 0), 
                new Color(255, 0, 128)), 15);
```

### 8. Logger.timing 计时器:

```java
// 用之前先init:
logger.timing.init();

// 输出一次时间点:
logger.timing.time();

// 重置计时器时间:
logger.timing.reset();

// 输出并重置:
logger.timing.timeAndReset();

// 用完后clear:
logger.timing.clear();
```

### 9. 测试过的兼容和不兼容颜色的后台程序:

如果有新的测试结果欢迎Email我(me@hydev.org), 我会加进这个列表的!
注意: Independent不是一个系统的名字, 它是指这个后台程序兼容多个系统.

完全兼容:
* Ubuntu 17   - XFCE4 Terminal Emulator
* Ubuntu 17   - Gnome-Terminal
* Independent - Git Bash
* Independent - MinGW / MinTTY
* Independent - Termius 2.1.6

色段兼容: (这些会把RGB颜色分成更概括性的颜色段, 不会乱但是显示效果没有完全兼容的好)
* Windows 10  - Cmder 1.3.6
* Windows 10  - CmdEmu 180626

预设兼容: (这些只会兼容预设颜色, 不兼容完整RGB, 所以RGB码会被解析成很乱的普通颜色码)
* Windows 10  - 命令提示符
* Independent - IntelliJ IDEA Run/Debug Console 2018.1.5
* Independent - Xshell 5

不兼容: (这些完全不兼容颜色, 会乱码, 或者兼容得不完整到无法算为兼容预设的程度)
* Windows 10  - Powershell
* Windows 10  - Babun 1.2.0
* Independent - PowerCmd 2.2
* Independent - Hyper
* Independent - Terminus Alpha

<br>

<a name="license"></a>
开源条款: [MIT License](/LICENSE)
--------
