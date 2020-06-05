---
title: Recursion
description: "An introduction to recursion with examples and code exercises"
layout: default
---

## Introduction

In computing, _recursion_ is a general approach in which the solution to a problem with a given set of input values is defined in terms of solutions to the same problem with a reduced set of input values, or with input values that are smaller in magnitude. By re-invoking with progressively smaller inputs, until we reach some specified _stopping condition_, we can then incorporate the solutions to the smaller problems into the solutions to the larger problems.

## Mathematical recurrence

Some mathematical sequences are defined (at least in part) according to a _recurrence relation_ between the terms of the sequence. In this type of definition, the value of the n<sup>th</sup> term of a sequence is defined as a function of the preceding terms. Typically, this type of definition is used for infinite sequences.

Given the sequence $A$, where 

$$
\begin{aligned}
A &= \left( a_0, a_1, \ldots \right),
\end{aligned}
$$

a recurrence relation may be defined (in very general terms) as 

$$
\begin{aligned}
a_n &= f\left( a_0, a_1, \ldots, a_{n-1} \right).
\end{aligned}
$$

In practice, the function $f$ usually isn't expressed as a function of _all_ the preceding terms, but of a small number of terms immediately preceding $a_n$. Also, note that the recurrence relation usually doesn't define $A$ completely: the definition generally includes one or more _initial values_, as well. 
 
Problems that can be defined using recurrence relations are prime candidates for recursive implementations.
 
### Example: Factorials

<a id="factorial-iterative"></a>The _factorial_ function (denoted by an exclamation mark after a non-negative integer, or after a symbol representing such an integer) is used in combinatorics, probability, and many other branches of mathematics. It is most often defined as

$$
\tag{1}
n! = 
\begin{cases}
1, & n = 0; \\
\prod_{i=1}^n i , & n \in \{1, 2, \ldots \}.
\end{cases}
$$

Note that the $\prod$ symbol is the product operator. In this case, we would read 

$$
\prod_{i=1}^n i
$$

as "the product, as $i$ varies from 1 to $n$ (inclusive), of $i$." Without using the product operator, we could write this as $1 \cdot 2 \cdot \ldots \cdot n$.

<a id="factorial-recursive"></a>There are other ways to define the factorial function, including this one that employs a recurrence relation:  

$$
\tag{2}
n! = 
\begin{cases}
1, & n  = 0; \\
n (n - 1)!, & n \in \{1, 2, 3, \ldots \}.
\end{cases}
$$

Using this form, we might compute $5!$ in the following manner. (Don't worry about the crazy number of parentheses: they're not necessary for the computation, but they're useful in the explanation after the computations.)

$$
\begin{aligned}
5! &= \Bigg (5 \cdot 4! \Bigg ) \\
&= \Bigg (5 \cdot \bigg (4 \cdot 3! \bigg ) \Bigg ) \\
&= \Bigg (5 \cdot \bigg (4 \cdot \Big ( 3 \cdot 2! \Big ) \bigg ) \Bigg ) \\
&= \Bigg (5 \cdot \bigg (4 \cdot \Big ( 3 \cdot \big ( 2 \cdot 1! \big ) \Big ) \bigg ) \Bigg ) \\
&= \Bigg (5 \cdot \bigg (4 \cdot \Big ( 3 \cdot \big ( 2 \cdot \left (1 \cdot 0! \right ) \big ) \Big ) \bigg ) \Bigg ) \\
&= \Bigg (5 \cdot \bigg (4 \cdot \Big ( 3 \cdot \big ( 2 \cdot \left (1 \cdot 1 \right ) \big ) \Big ) \bigg ) \Bigg ) \\
&= \Bigg (5 \cdot \bigg (4 \cdot \Big ( 3 \cdot \big ( 2 \cdot 1  \big ) \Big ) \bigg ) \Bigg ) \\
&= \Bigg (5 \cdot \bigg (4 \cdot \Big ( 3 \cdot  2   \Big ) \bigg ) \Bigg ) \\
&= \Bigg (5 \cdot \bigg (4 \cdot 6 \bigg ) \Bigg ) \\
&= \Bigg ( 5 \cdot 24  \Bigg ) \\
&= 120 \\
\end{aligned}  
$$

Imagine that each set of parentheses represents execution of a computational method for evaluating the factorial function. In order to compute $5!$, we multiply $5$ and $4!$. But in order to compute that, we need to compute $4!$; this adds an inner set of parentheses in the second line. In the third line, we see another set of parentheses, in which we will compute $3!$ (in order to compute $4!$), and so on.

When we get to an expression that includes $0!$, we don't need to break that term down any further, since our definition tells us that $0! = 1$. (This is a _stopping condition._) From that point, we can start completing the computations that are pending: in each of the last 5 lines, we're replacing a set of parentheses with the result obtained by computing the product inside those parentheses; we can think of this operation as completing execution of one of our factorial computations.

What we're seeing here is&mdash;essentially&mdash;the internal workings of computing $5!$ by recursion: each time we replace $m!$ with $(m \cdot (m - 1)!)$, we're starting a new recursive computation. Each time we replace a set of parentheses with the computed value, we're completing a recursive computation. Eventually (if we've done our job correctly), we have no parentheses left&mdash;instead, we have the result, which (in this case) is $120$.
 
As it turns out, the expression for the factorial function specified in [(2)](#factorial-recursive) can be translated to code quite easily&mdash;not just Java, but almost any programming language. Even better, the expression in code looks so much like the mathematical expression that it is very easy to verify that the former correctly expresses the latter.
  
## Other applications

Recursion isn't limited to computing mathematical functions. Many problems that are not strictly mathematical in nature can be specified in recursive terms, with potential for recursive implementation. The most widely used sorting algorithms are usually implemented in recursive forms. A depth-first search in a tree data structure is easily implemented as a recursive operation. A number of puzzles, such as the _Tower of Hanoi_, can best be understood in recursive terms. The task of parsing (for compilation) the source code of many programming languages is often specified and implemented as a recursive process. Even processing natural language is at least partially a recursive task.

### Example: Palindromes
<a id="palindrome-traditional"></a>
$$\tag{3}$$A _palindrome_ is a sequence of characters that reads the same forward and backward.

Usually, we qualify this definition a bit, by skipping non-alphanumeric characters (punctuation, whitespace, special symbols like &reg;, etc.) and ignoring character casing. (As a rule, we also skip subscripts and superscripts, even though they are usually alphanumeric.)

With the qualified definition above, we would consider all of the following to be palindromes:

* Radar
* ABBA
* Madam, I'm Adam.
* A man, a plan, a canal&mdash;Panama!

Somewhat less obviously, we also consider these to be palindromes:

* X
* 

That is, any single character, or even an empty string, reads the same forward and backward, and is thus a palindrome (though certainly a trivial one).

As we move from the basic definition to code (if, for example, we want to write a method that checks to see if a `String` specified in a parameter is a palindrome, and return the corresponding `boolean` result), the latter might look very different from the definition in natural language. But a recursive implementation is often very close to the natural language expression&mdash;as long as that natural language expression is also recursive.

<a id="palindrome-recursive"></a>Let's take a new definition as a starting point (ignoring the whole question of whitespace, punctuation, and special symbols for now):

> $$\tag{4}$$A string $S$ is a palindrome if and only if 
> * the number of characters in the string is 0 or 1; 
> * **OR** 
>     * the first and last characters of the string are the same,
>     * **AND** the entire substring between the first and last characters (exclusive) <u>is a palindrome</u>.

Please note these key points about the definition:

* The logical structure (indicated by the indentation and the all-caps words) of the bullets is a Boolean expression, of the form $A$ OR ($B$ AND $C$). We might also write this using special symbols for the logical operators: $A \lor (B \land C)$. 

    In this context (logical rather than arithmetic), the logical operators should be considered _short-circuit_ operators. When using short-circuit logical operators, the rules about parentheses are modified a bit: In this case, $A$ would be evaluated _first_; if (and only if) $A$ is $\text{false}$, then we have to evaluate the expression in parentheses. 

* The definition is recursive. Note the final underlined portion: It is saying that a string is a palindrome if a specified substring is a palindrome. (Of course, that is only the case if the previous condition&mdash;that the first and last characters are the same&mdash;is also satisfied.)

Take a few minutes to read and understand the definition. Then try to apply it to one of the palindromes listed above, or so some other palindrome you know&mdash;or some synthetic palindrome you come up with, such as "XYX". Try also to applying it to a string that's not a palindrome.  
    
Though the definition specified in [(4)](#palindrome-recursive) is more verbose than "a palindrome is a string that reads the same forward and backward," it is still reasonably clear; more importantly, it can be translated to code in a very direct fashion, resulting in an implementation that will be easy for us to compare for correctness with the original definition.

## Advantages

The last paragraph of both of the 2 examples above get at some of the key benefits of recursion:

* If we have a problem that can expressed fairly simply (in natural language) in recursive terms, then going from that to a recursive implementation is often significantly simpler than going from a non-recursive expression of the problem to a non-recursive implementation.

* When going from a recursive definition in a natural language to a recursive implementation in a programming language, verifying the resulting code for correctness is usually easier than when using non-recursive definitions and implementations.    
 
## Disadvantages

The main disadvantages of recursion (apart from the difficulty we might have in wrapping our heads around the concept at first) have to do with method invocation and the corresponding stack space. 

* In Java (and many other languages), recursive method invocation is&mdash;just as the name implies&mdash;method invocation. That means that each invocation add a stack frame to the stack and requires a small amount of processing time for the invocation itself.

    With some language/compiler combinations, if the recursive implementation is written in a particular fashion, the compiler is able to compile the recursive code into an iterative form in the byte code or machine code. Currently, Java does not do that&mdash;though some other languages running on the JVM, such as Scala, are capable of this. In any case, this does require that our code is written in a very specific way. (It can be proven that _any_ recursive implementation can be transformed into an iterative one. However, performing such a transformation isn't a trivial process.)
    
* When using recursion, we have to be even more careful than usual with stopping conditions. Notice that both of our example recursive definitions, [(2](#factorial-recursive) and [(4)](#palindrome-recursive), have stopping conditions: in the [factorial](#factorial-recursive) case, if $n = 0$, we don't have to perform any recursive evaluation; in the [palindrome](#palindrome-recursive) case, if the string length is 0 or 1, there's no need for recursive evaluation. If, in our implementations, we were to check for our stopping conditions _after_ we perform recursion (or not check them at all), _we'd never reach our stopping conditions_, and the evaluation would never terminate (at least not in a useful fashion). 

    In an iterative implementation, bad or missing stopping conditions can result in an infinite loop&mdash;but the virtual machine doesn't terminate. Code on other threads will continue to run; if those threads are running on on other CPUs or cores, they might not even be affected.
    
    In a recursive implementation, bad/missing stopping conditions will most often result in stack memory being exhausted (in Java, this causes a `StackOverflowError`). In the best case, this won't case the JVM to terminate (since each thread has its own stack)&mdash;but it usually does.

## Tasks

To explore recursion in Java, we'll implement the the 2 examples above: factorials and palindromes.
 
### Preparation

1. Create an IntelliJ Java project with the following characteristics:
 
     * JDK version: 11
     * Project name: `recursion`
     * Project location: `~/Desktop/projects/bootcamp/recursion` 
     
         (`~` represents the current user's home directory, but can only be used in a file path in OS X and Linux; in general, you should simply ensure that the location entered has the specified relationship to the current user's home directory. The slash direction should be adjusted as necessary for the platform.) 
     
2. Use the **File/Project Structure** command to configure the `recursion` module (the only module) as follows: 

     * **Sources** must include a `test` directory (in the project root), configured as a **Tests** (test sources) directory.
     
     * **Dependencies** must include JUnit5 with **Test** scope. 

### Factorials

#### Implementation

1. Create a class with the fully qualified class name `edu.cnm.deedpive.Factorials`.

2. In the class created in step 1, define a method implementing the the recursive approach shown in [(2)](#factorial-recursive), conforming to these specifications:

    * Method name: `computeRecursive`

    * Access level: `public`

    * Scope: `static`

    * Return type: `long`

    * Parameter: 
        * Type: `int` 
        * Name: _(Not dictated by the specification; should be chosen as the developer sees fit.)_
    * Behavior:
        * If parameter value < 0, `IllegalArgumentException` must be thrown;
        * otherwise, if parameter value = 0, a value of 1 must be returned (this is the stopping condition);
        * otherwise, the correct factorial function value must be returned (up to and including a parameter value of 20), using the recursive definition.

    Notes: 
    
     * The implementation shouldn't do anything special if the parameter value is greater than 20; however, the resulting value will overflow the range of `long` in that case; the implementation will thus not be expected to return the correct value above that point.
     
    * You might have noticed&mdash;or maybe you already knew&mdash;that $1! = 0! = 1$. Thus, the number of recursive invocations can be reduced by 1, in most cases, by including a parameter value of 1 in the stopping condition. Whether to make this modification is up to you.
    
#### Tests

1. Create a test class (in the `test` source root) with the fully qualified name `edu.cnm.deepdive.FactorialsTest`.

    <a id="factorial-test-hint"></a>Hint: IntelliJ IDEA can create the test class, with the required name, in the required location, using the **Code/Generate/Test** command, or the **Create Test** _intention action_ (accessed by clicking on the class name in the class declaration, then typing _[Alt]-[Enter]_ on Windows and Linux, or _[Option]-[Return]_ on OS X.) With the appropriate selection of options in the **Create Test** dialog that appears, the first few items of the next point will also be taken care of by IntelliJ IDEA.
    
2. In the test class, define a method conforming to the following:

    * Method name: `computeRecursive`
    
    * Annotation: `@Test`

    * Access level: _(default/package-private)_

    * Return type: `void`

    * Parameters: none 

    * Behavior: Use the appropriate JUnit5 assertion(s) to test the cases in the table below. The first column shows the value that should be passed as an argument to the `Factorials.computeRecursive` method; the second shows the expected value to be returned (if an exception isn't thrown); the third shows the expected exception (if any).
    
         | `n` | Expected return value of `Factorials.computeRecursive(n)` | Expected exception |
         |:-:|:-:|:-:|
         | `0` | `1` | _(none)_ |
         | `1` | `1` | _(none)_ |
         | `5` | `120` | _(none)_ |
         | `10` | `3628800` | _(none)_ |
         | `-1` | _(none)_ | `IllegalArgumentException` |

        Note: If using a `for` loop to iterate over the test cases, the final test case (testing for an exception) should be handled outside of that loop.
        
### Palindromes

#### Implementation

1. Create a class with the fully qualified class name `edu.cnm.deedpive.Palindromes`.

2. In the class created in step 1, define a method implementing the the recursive approach shown in [(4)](#palindrome-recursive), conforming to these specifications:

    * Method name: `testRecursive`
    
    * Access level: `public`
    
    * Scope: `static`
    
    * Return type: `boolean`
    
    * Parameter: 
        * Type: `String` 
        * Name: _(Not dictated by the specification; should be chosen as the developer sees fit.)_
    
    * Behavior:
        * If parameter value is a palindrome, the method must return `true`; otherwise, the method must return `false`.
        * The method may assume that the parameter will not contain any punctuation or spaces, and that all characters will use the same letter case. More correctly, this method must not attempt to deal with (by removing or altering) any whitespace or punctuation characters, or any mixed letter-casing.

#### Tests

1. Create a test class (in the `test` source root) with the fully qualified name `edu.cnm.deepdive.PalindromesTest`.

    (See [hint in test steps for factorials](#factorial-test-hint) for tips on using IntelliJ IDEA shortcuts to simply this task and the one that follows.)
    
2. In the test class, define a method conforming to the following:

    * Method name: `computeRecursive`
    
    * Annotation: `@Test`
    
    * Access level: _(default/package-private)_
    
    * Return type: `void`
    
    * Parameters: none 
    
    * Behavior: Use the appropriate JUnit5 assertion(s) to test the cases in the table below. The first column shows the value that should be passed as an argument to the `Palindromes.computeRecursive` method; the second shows the expected value to be returned.
    
         | `s` | Expected return value of `Palindromes.testRecursive(s)` |
         |:-:|:-:|
         | `"radar"` | `true` |
         | `"sonar"` | `false` |
         | `"abba"` | `true` |
         | `"abb"` | `false` |
         | `"x"` | `true` |
         | `""` | `true` |

        Note: Remember that your current implementation of `Palindromes.testRecursive` is assuming that the characters in the parameter value will all be the same case, and that they will only be alpha-numeric. Be careful not to mix cases in any single argument, and not to include any spaces or punctuation in the arguments.

### Repository

Commit your work to Git after completing each task (if not more often than that), including optional tasks, and push your commits to GitHub.

* Your initial commit &amp; push (only) should be done using the **VCS/Import into Version Control/Share Project on GitHub** command. The remote (GitHub) repository must be named `recursion` (please note the letter casing).

* Subsequent commits should be performed using **VCS/Commit**.

* Pushing to GitHub can be performed when committing, or with the **VCS/Git/Push** command.
  
## Advanced tasks

### Factorials

* <a id="handle-large-values"></a>Handle large values 

    1. Change the return type of `Factorials.computeRecursive` to `BigInteger`. Note that this will require using a method of that class (instead of the `*` operator) for the multiplication.
    
    2. Modify the `FactorialsTest.computeRecursive` method to use the `BigInteger` type for the expected and actual values.

* Implement &amp; test iterative approach

    1. Implement a `Factorials.computeIterative` method, with the same modifiers, return type, and parameter type as `Factorials.computeRecursive`, but using the iterative computation method given in [(1)](#factorial-iterative).
    
    2. Add a `computeIterative` test method to the `FactorialsTest` class. This method should have the same annotation, modifiers, and return type as `FactorialsTest.computeRecursive`, and should use the same test cases&mdash;but the actual values tested (against the expected values) should be those returned from `Factorials.computeIterative`, instead of `Factorials.computeRecursive`.
    
### Palindromes

* Implement &amp; test input normalization method

    Currently, the `Palindromes.testRecursive` method can only correctly handle parameter values that are precise palindromes; that is, all letter casing, whitespace, and punctuation in the parameter value provided by a given invocation must also match when reversed. In most cases, this will not give the desired results unless any argument passed to that method is already normalized&mdash;with whitespace and punctuation removed, and all characters converted to one letter case. This task will address this shortcoming with a _wrapper_ method, capable of coverting a denormalized input string to a normalized form, and then delegating the palindrome testing to the `testRecursive` class.
    
    1. In the `Palindromes` class, define a method to normalize a `String`, and then test it:
 
        * Method name: `testDenormalized`
     
        * Access level: `public`
     
        * Scope: `static`
     
        * Return type: `boolean`
     
        * Parameter: 
            * Type: `String` 
            * Name: _(Not dictated by the specification; should be chosen as the developer sees fit.)_
     
        * Behavior:
            * Invoke `testRecursive`, passing it an argument obtained from the parameter of this method, after removing all punctuation and whitespace, and then converting it to lower-case.
            * Return as a result the value returned from the `testRecursive` invocation. 
            * If parameter value is a palindrome, the method must return `true`; otherwise, the method must return `false`.
 
            Hints 
            
            * The `String.removeAll` method can return a `String` with unwanted characters stripped from an input `String`, by invoking it on the input `String`, and passing arguments specifying the appropriate regular expression pattern to match and an empty replacement `String`.
        
            * The regex pattern `"[\W_]+"` matches any sequence of 1 or more punctuation, whitespace, or underscore characters.
 
     2. In the `PalindromesTest` class, defined a method to test the `Palindromes.testDenormalized` method:
     
        * Method name: `testDenormalized`
        
        * Annotation: `@Test`
        
        * Access level: _(default/package-private)_
        
        * Return type: `void`
        
        * Parameters: none 
        
        * Behavior: Use the appropriate JUnit5 assertion(s) to test the cases in the table below. The first column shows the value that should be passed as an argument to the `Palindromes.computeRecursive` method; the second shows the expected value to be returned.
        
             | `s` | Expected return value of `Palindromes.testDenormalized(s)` |
             |:-:|:-:|
             | `"radar"` | `true` |
             | `"Radar"` | `true` |
             | `"A man, a plan, a canal - Panama!"` | `true` |
             | `"A man, a plan, a dam - Hoover!"` | `false` |
             | `"aBbA"` | `true` |
          
* Implement &amp; test iterative approach 

    1. Implement a `Palindromes.testIterative` method, with the same modifiers, return type, and parameter type as `Palindromes.testRecursive`, but using an iterative approach (e.g. iterating over the characters of a `String`, comparing corresponding characters from the left and right sides of the `String` to each other).
    
    2. Add a `testIterative` test method to the `PalindromesTest` class. This method should have the same annotation, modifiers, and return type as `PalindromesTest.testRecursive`, and should use the same test cases&mdash;but the actual values tested (against the expected values) should be those returned from `Palindromes.testIterative`, instead of `Palindromes.testRecursive`.
 
### Javadocs

1. Add Javadoc comments to the classes and methods in the `edu.cnm.deepdive` package of the project.

    * Every documentation comment should include a summary sentence (at least), and any additional descriptive sentences that you feel necessary to describe the purpose or functionality of the documented element.
    
    * Avoid the _imperative voice_ in your comments; instead use (preferably) the _active voice_ or (when necessary) the _passive voice_.
    
        * Bad: "Compute the factorial function value (n!) for the provided parameter value."
        
        * Better: "Computes the factorial function value (n!) for the provided parameter value."  

        * Even better: "Computes the factorial function value (n!) for the provided parameter value. Since the return type is {@code long}, the value returned for parameter values greater than 20 will not be correct." 
        
            (Note: The additional description sentence above is only applicable if you have not already completed the task to [handle large values](#handle-large-values).)

    * Remember to include summaries (at least) for the `@param` and `@return` tags of the method(s). 

    (Hint: Use the IntelliJ IDEA [automatic comments feature](https://www.jetbrains.com/help/idea/working-with-code-documentation.html#auto-comment) to generate the Javadoc comment structures; then simply add the summary sentences and any additional descriptions.)

2. Use the **Tools/Generate Javadoc** command to generate documentation files in HTML format.

    * Generate Javadoc scope: Whole project
    
    * Include test sources: No (i.e. unchecked)
    
    * Output directory: `docs/api` within the project directory (Use the folder button in the **Output directory** field to browse to and select the project's directory; then edit the resulting path in **Output directory** by appending `\docs\api` or `/docs/api`, for Windows or OS X, respectively).
    
    * Documentation level (slider): protected
    
    * Other command line arguments: `-windowtitle "Recursion examples" -link https://docs.oracle.com/en/java/javase/11/docs/api/`
        
        (Note: The command line arguments must be entered as a single line, regardless of their appearance in this page.)  
    
    * _Other options: (Leave set to default values)_
