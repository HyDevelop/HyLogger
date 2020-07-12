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

Java: 

```java
HyLogger logger = new HyLogger("Scenario Name");
```

Kotlin:

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

Formatting like SLF4J (This is completely useless in Kotlin):

```java
logger.log("{}! {} Everywhere!", "Brackets", "Brackets");
logger.debug("User {} just posted {} on {}", user, json, board);
```

In **Kotlin** you can do this:

```kotlin
logger.debug("User $user tried to inject a SQL sequence: ${json.value}")
```

### 3. Preset Color Logging:

#### A. Use [**Minecraft Color-code**](https://www.spigotmc.org/attachments/example2-png.188806/):

Java:

```java
logger.log(HyLoggerUtilsKt.parseFormats("&cHeck &eyeah!"));
```

Kotlin:

```kotlin
logger.log("&cHeck &eyeah!".parseFormats())
```

#### B. Use Color Constants:

Java: (Import static AnsiColor.* first)

```java
logger.log("{}I'm blue {}da ba dee da ba daa", BLUE, CYAN);
```

Kotlin: (Import AnsiColor.* first)

```kotlin
logger.log("${BLUE}I'm blue ${CYAN}da ba dee da ba daa")
```

### 4. RGB!!!! Logging:

#### Terminals' compatibility with TrueColor

Unlike the regular logging features, 
these fancy logging features need proper support by the terminal program you're using. 
The terminal programs with **perfect compatibility** for each platform are following:
(If you found a new compatible program, please post an issue)

- All Platform: [Terminus Alpha](https://github.com/Eugeny/terminus)
- MacOS: [iTerm](https://www.iterm2.com/)
- Windows: Git Bash / MinTTY
- Linux: XFCE4 Terminal Emulator / Gnome-Terminal

Here is a list of **incompatible** programs:

- Windows: CMD / Powershell / Babun 1.2.0 / PowerCmd 2.2 / Xshell 5
- All Platform: Hyper

Here is a list of **partially compatible** programs: 
(Might not display TrueColor properly or have some bugs)

- Windows: Xshell 5 / Cmder 1.3.6 / CmdEmu 180626

How to add support for **Eclipse terminal**:

- Just use IntelliJ! It's 100% better.
- Or install `ANSI Escape in Console` plugin in Eclipse Marketplace.

#### Use RGB:

Java:

```java
logger.log("{}Awwwww", HyLoggerUtilsKt.foreground(new Color(252, 168, 187)));
```

Kotlin:

```kotlin
logger.log("${new Color(252, 168, 187).foreground()}Awwwww");
```

#### Gradients:

LinearGradient defines a gradient pattern on a single line. (Eg. Red to Blue)
There are several presets in the `GradientPresets` class,
and you can create your own too.

Please see [LoggerTest.kt](https://github.com/HyDevelop/HyLogger/blob/master/src/test/java/LoggerTest.kt) for examples!

Eg.

```kotlin
logger.fancy.gradient("Orange to Pink Gradient", Color(255, 140, 0), Color(255, 0, 128))
```




<br>

<a name="license"></a>
Open-source License: [MIT License](/LICENSE)
--------
