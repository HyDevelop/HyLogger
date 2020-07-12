<h1 align="center">
  <a href="#!">
    <img src="https://i.imgur.com/4dkZaN1.png" height="200">
  </a>
</h1>
<h4 align="center">
  RGB, even in the console outputs!
</h4>
<h5 align="center">
  <a href="#introduction">Introduction</a>&nbsp;&nbsp;|&nbsp;
  <a href="#development">Getting Started</a>&nbsp;&nbsp;|&nbsp;
  <a href="#license">License</a>
</h5>

<br>

<p align="center">
  <img src="https://i.imgur.com/TqgyUJt.png">
</p>

<br>

<a name="introduction"></a>
What Is This?
--------

HyLogger is a console logging library designed for human programmers to read. Its APIs are simple and clean, and its color-coding features make logging more readable. It focuses on coloring features like color-coding.


<br>

<a name="development"></a>
## Getting Started

### 1. Import using Gradle / Maven:

```xml
<dependency>
    <groupId>org.hydev</groupId>
    <artifactId>HyLogger</artifactId>
    <version>2.0.0.325</version>
</dependency>
```

It'll be available after clicking the reload button in IDEA!

### 1. Create Logger:

```java
HyLogger logger = new HyLogger("Scenario Name");
```

```kotlin
val logger = HyLogger("Scenario Name")
```

### 2. Basic Logging:

Basic logging (they are basically the same for Kotlin and Java):

```java
logger.log("Oak logs are the best.");
logger.debug("This is not logged if HyLoggerConfig.debug is off.");
logger.error("This could never happen in theory, right?");
logger.warning("Nerf this!");
```

Formatting with `String.format()`:

```java
logger.logf("Player %s just ate %.2f stardrops.", username, amount);
logger.errorf("Time has just been distorted by %.5f picoseconds.", timeDiff);
```



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

### 5. 添加颜色和 ANSI 格式预设:

```java
// 颜色其实就是一个 Enum 啦...
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

### 6. 添加自定义 RGB 颜色:

```java
// 注意: RGB 颜色不是所有后台都支持
//       大部分有独立主题配置的后台都不支持
//       比如 IntelliJ IDEA 自带的那个就不支持

AnsiRGB.toAnsi(红, 绿, 蓝); // 用 RGB 获取 ANSI 颜色码

new AnsiRGB(颜色对象).toAnsi(); // 用 java.awt.Color 颜色对象获取 ANSI 颜色码
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

// 注意: 必须要支持 RGB 颜色的后台才支持定向渐变
// 注意: 颜色对象的话至少要两个才行

// 渐变预设在 GradientPresets 类里
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
// 注意: 这个角度单位不是 radian, 而是 degrees
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
// 用之前先 init:
logger.timing.init();

// 输出一次时间点:
logger.timing.time();

// 重置计时器时间:
logger.timing.reset();

// 输出并重置:
logger.timing.timeAndReset();

// 用完后 clear:
logger.timing.clear();
```

<br>

<a name="license"></a>
开源条款: [MIT License](/LICENSE)
--------
