[![Releases](https://img.shields.io/github/release/HTTP-RPC/Sierra.svg)](https://github.com/HTTP-RPC/Sierra/releases)
[![Maven Central](https://img.shields.io/maven-central/v/org.httprpc/sierra.svg)](https://repo1.maven.org/maven2/org/httprpc/sierra/)

# Introduction
Sierra is an open-source framework for simplifying development of Java Swing applications. It provides a convenient DSL for declaratively instantiating Swing component hierarchies. The framework is extremely lightweight (3KB) and has no external dependencies. 

The project's name comes from the nautical _S_ or _Sierra_ flag, representing the first letter in "Swing":

![](sierra.png)

This guide introduces the Sierra framework and provides an overview of its key features.

# Contents
* [Getting Sierra](#getting-sierra)
* [Sierra Classes](#sierra-classes)
* [Examples](#examples)
* [Additional Information](#additional-information)

# Getting Sierra
Sierra is distributed via Maven Central at [org.httprpc:sierra](https://repo1.maven.org/maven2/org/httprpc/sierra/). Java 11 or later is required.

# Sierra Classes
Sierra provides a single class named `SwingUIBuilder` whose methods can be used to declaratively establish a hierarchy of user interface components. The methods provided by this class form a DSL, or "domain-specific language", that makes it easy to visualize the resulting output. 

`SwingUIBuilder` includes the following static methods for declaring common layout containers:

* `flowPanel()` - declares a panel with a flow layout
* `borderPanel()` - declares a panel with a border layout
* `gridPanel()` - declares a panel with a grid layout
* `cardPanel()` - declares a panel with a card layout
* `boxPanel()` - declares a panel with a box layout

Additionally, `SwingUIBuilder` provides the following method for defining a panel's contents:

```java
public static <C extends JComponent> Cell<C> cell(C component) { ... }
```

This method returns an instance of `SwingUIBuilder.Cell` that can be used to further customize the provided component:

* `constrainedBy()` - applies a layout constraint to the component
* `with()` - accepts a callback that can be used to set properties or invoke methods on the component

For example, the following code creates a flow panel containing a button instance. The button is associated with a simple action handler that writes a message to the console:

```java
JPanel flowPanel = new JPanel(new FlowLayout());

JButton button = new JButton("Press Me");

button.addActionListener(event -> System.out.println("Button pressed"));

flowPanel.add(button);
```

Using `SwingUIBuilder`, the code could be rewritten as follows:

```java
JPanel flowPanel = flowPanel(new FlowLayout(),
    cell(new JButton("Press Me"))
        .with(button -> button.addActionListener(event -> System.out.println("Button pressed")))
);
```

In this simple example, both versions are fairly readable. However, with more deeply nested component hierarchies, the first approach quickly becomes untenable. Using `SwingUIBuilder`, the declaration of sub-components mirrors the resulting hierarchy, making it much easier to read and write. 

# Examples
This section includes examples demonstrating usage of `SwingUIBuilder`.

## Flow Layout
Based on Java [flow layout](https://docs.oracle.com/javase/tutorial/uiswing/layout/flow.html) example.

[FlowLayoutTest.java](https://github.com/HTTP-RPC/Sierra/blob/master/sierra-test/src/main/java/org/httprpc/sierra/FlowLayoutTest.java)

<img src="README/flow-layout.png" width="692px"/>

## Border Layout
Based on Java [border layout](https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html) example.

[BorderLayoutTest.java](https://github.com/HTTP-RPC/Sierra/blob/master/sierra-test/src/main/java/org/httprpc/sierra/BorderLayoutTest.java)

<img src="README/border-layout.png" width="683px"/>

## Grid Layout
Based on Java [grid layout](https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html) example.

[GridLayoutTest.java](https://github.com/HTTP-RPC/Sierra/blob/master/sierra-test/src/main/java/org/httprpc/sierra/GridLayoutTest.java)

<img src="README/grid-layout.png" width="480px"/>

## Card Layout
Based on Java [card layout](https://docs.oracle.com/javase/tutorial/uiswing/layout/card.html) example.

[CardLayoutTest.java](https://github.com/HTTP-RPC/Sierra/blob/master/sierra-test/src/main/java/org/httprpc/sierra/CardLayoutTest.java)

<img src="README/card-layout.png" width="423px"/>

## Box Layout
Based on Java [box layout](https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html) example.

[BoxLayoutTest.java](https://github.com/HTTP-RPC/Sierra/blob/master/sierra-test/src/main/java/org/httprpc/sierra/BoxLayoutTest.java)

<img src="README/box-layout.png" width="322px"/>

# Additional Information
This guide introduced the Sierra framework and provided an overview of its key features. For additional information, see the [source code](https://github.com/HTTP-RPC/Sierra/tree/master/sierra/src/main/java/org/httprpc/sierra).
