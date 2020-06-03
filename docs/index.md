---
title: Simple curriculum module
description: "Description, code fragments, etc."
layout: default
---

Organize content into sections using Markdown headlines, which will automatically be used in the navigation links to the left. A few example sections (with explanatory text for how each section might be used) are shown below.

## Value

If the module is an assignment or practical exam problem, show point value&mdash;broken down into separate components, if appropriate.

## Summary

Describe the module, exercise, or activity. In general, this section should not include a lot of code, but should instead provide a conceptual overview. If mathematical expressions are needed in this section (or any others), use LaTeX syntax in `$$…$$` blocks;[^1] these will be rendered by MathJax in the browser. For example, we can write

```
$$
x = \frac{-b \pm \sqrt{b^2 - 4ac}}{2a}
$$
```

which will be rendered as 

$$
x = \frac{-b \pm \sqrt{b^2 - 4ac}}{2a}
$$

Simple mathematical expressions may also be written inline, using `$…$`. For example, `$c^2 = a^2 + b^2$` will be rendered as $c^2 = a^2 + b^2$.

[^1]: When the intention is to show a computation as expressed in code, a code block (or inline code statement) should be used; otherwise, mathematical notation is preferred. 

## Requirements

Include functional and technical requirements. Include code fragments and test cases, as appropriate. For code fragments, use fenced code blocks, with language tags; inline references to variables, methods, classes, etc. should use backticks to indicate code elements.

For example, a Java code fragment would be written in a fenced code block as 

    ```java
    public static void main(String[] args) {
      System.out.println("Hello, world!");
    }
    ```

This will be rendered as

```java
public static void main(String[] args) {
  System.out.println("Hello, world!");
}
```

## Hints

If the module is an assignment or practical exam problem&mdash;particularly if it's especially challenging or requires an approach that's not very obvious&mdash;it may be a good idea to include some general tips.

## Attachments 

If the module includes `.pdf` or other attachments, link them here&mdash;as well as linking them inline (in the text), if appropriate. If there is more than one attachment, these should be formatted as an ordered or unordered list. 

## Links 

Links to content outside the curriculum module should appear here, as well as inline (if appropriate). If there is more than one link, an ordered or unordered list should be used. In general, it's recommended not only to include a standard Markdown link (specified with the `[…](…)` syntax), but also display the URL as a link (enclosed with `<…>`). For example,

```
* [Deep Dive Coding Java+Android Bootcamp Curriculum Template](
    https://github.com/ddc-java/curriculum-template/) 
    (<https://github.com/ddc-java/curriculum-template/>)
```

will be rendered as

* [Deep Dive Coding Java+Android Bootcamp Curriculum Template](
    https://github.com/ddc-java/curriculum-template/) 
    (<https://github.com/ddc-java/curriculum-template/>)

(Note that the line breaks are used here only to make the example easier to read. It is neither necessary nor recommended to break lines in this fashion in Markdown.)

## Footnotes

If any footnotes are defined &amp; referenced in the content, they will be displayed here. For example, note the footnote defined and referenced in the [Summary](#summary) section, above.