---
title: Recursion
description: "An introduction to recursion using 3 simple problems"
layout: default
---

## Summary

In computing, _recursion_ is a general approach in which the solution to a problem with a given set of input values is defined in terms of solutions to the same problem with a reduced set of input values, or with input values that are smaller in magnitude. By re-invoking with progressively smaller problems, until we reach some specified _stopping condition_, we can then incorporate the solutions to the smaller problems into the solutions to the larger problems.

## Recurrence relations in mathematics

### Introduction

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
 
### Example: The factorial function

The _factorial_ function (denoted by an exclamation mark after a non-negative integer, or after a symbol representing such an integer) is used in combinatorics, probability, and many other branches of mathematics. It is most often defined as

$$
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

<a name="fn1"></a>There are other ways to define the factorial function, including this one that employs a recurrence relation:  

$$
\tag{1}
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

Imagine that each set of parentheses, on the right side of the equals signs, represents execution of a computational method for evaluating the factorial function. In order to compute $5!$, we start executing the method: Using the recurrence relation above, we compute $5!$ by computing the product of $5$ and $4!$. But in order to compute that product, we must first compute $4!$; this adds an inner set of parentheses in the second line. In the third line, we see another set of parentheses, in which we will compute $3!$ (in order to compute $4!$), and so on.

When we get to an expression that includes $0!$, we don't need to break that term down any further, since our definition tells us that $0! = 1$. (This is a _stopping condition._) From that point, we're actually able to start completing the computations that are pending: in each of the last 5 lines, we're replacing a set of parentheses with the result obtained by computing the product inside those parentheses; we can think of this operation as completing execution of one of our factorial computations.

What we're seeing here is&mdash;essentially&mdash;the internal workings of computing $5!$ by recursion: each time we replace $m!$ with $(m \cdot (m - 1)!)$, we're starting a new recursion computation. Each time we replace a set of parentheses with the computed value, we're completing a recursive computation. Eventually (if we've done our job correctly), we have no parentheses left&mdash;instead, we have the result, which (in this case) is $120$.
 
As it turns out, the expression for the factorial function specified in [(1)](#fn1) can be translated to code quite easily&mdash;not just Java, but almost any programming language. Even better, the expression in code looks so much like the mathematical expression that it is very easy to verify that the former correctly expresses the latter.
  
## Non-mathematical problems

Recursion isn't limited to mathematical problems. Many types of non-mathematical problems can be expressed in recursive terms. A number of puzzles&mdash;such as the _Tower of Hanoi_&mdash;can best be understood in recursive terms. The task of parsing the source code of many programming languages, during compilation, is often expressed&mdash;in the design as well as the implementation&mdash;as a recursive process. Even parsing natural language is&mdash;in part&mdash;a recursive task.

### Example: Palindromes

A _palindrome_ is a sequence of characters that reads the same forward and backward. Usually, we qualify this definition a bit, by skipping non-alphanumeric characters (punctuation, whitespace, special symbols like &reg;, etc.) and ignoring character casing. (As a rule, we also skip subscripts and superscripts, even though they are usually alphanumeric.)

With the qualified definition above, we would consider all of the following to be palindromes:

* Radar
* ABBA
* Madam, I'm Adam.
* A man, a plan, a canal&mdash;Panama!

Somewhat less obviously, we also consider these to be palindromes:

* X
* 

That is, any single character, or even an empty string, reads the same forward and backward.

As we move from the basic definition to code (e.g. if we want to write a method that checks to see if a `String` specified in a parameter is a palindrome, and return the corresponding `boolean` result), the latter might look very different from the definition in natural language. But a recursive implementation is often very close to the natural language expression&mdash;as long as that natural language expression is also recursive.

<a name="fn2"></a>Let's take a new definition as a starting point (ignoring the whole question of whitespace, punctuation, and special symbols for now):

> A string $S$ is a palindrome if and only if 
> * the number of characters in the string is 0 or 1; 
> * **OR** 
>     * the first and last characters of the string are the same,
>     * **AND** the entire substring between the first and last characters (exclusive) <u>is a palindrome</u>.

Please note these key points about the definition:

* The logical structure (indicated by the indentation and the all-caps words) of the bullets is a Boolean expression, of the form $A$ OR ($B$ AND $C$). We might also write this using special symbols for the logical operators: $A \lor (B \land C)$. 

    In this context (logical rather than arithmetic), the logical operators should be considered _short-circuit_ operators. When using short-circuit logical operators, the rules about parentheses are modified a bit: In this case, $A$ would be evaluated _first_; only if $A$ is $\text{false}$ do we have to evaluate the expression in parentheses. 

* The definition is recursive. Note the final underlined portion: It is saying that a string is a palindrome if a specified substring is a palindrome. (Of course, that is only the case if the previous condition&mdash;that the first and last characters are the same&mdash;is also satisfied.)

Take a few minutes to read and understand the definition. Then try to apply it to one of the palindromes listed above, or so some other palindrome you know&mdash;or some synthetic palindrome you come up with, such as "XYX". Try also to applying it to a string that's not a palindrome.  
    
Though the definition specified in [(2)](#fn2) is more verbose than "a palindrome is a string that reads the same forward and backward," it is still reasonably clear; more importantly, it can be translated to code in a very direct fashion, resulting in an implementation that will be easy for us to compare for correctness with the original definition.

## Advantages of recursion

The last paragraph of both of the 2 examples above get at some of the key benefits of recursion:

* If we have a problem that can expressed fairly simply (in natural language) in recursive terms, then going from that to a recursive implementation is often significantly simpler than going from a non-recursive expression of the problem to a non-recursive implementation.

* When going from a recursive definition in a natural language to a recursive implementation in a programming language, verifying the resulting code for correctness is usually easier than when using non-recursive definitions and implementations.    
 
## Disadvantages of recursion

The main disadvantages of recursion (apart from the difficulty we might have wrapping our heads around the concept at first) have to do with method invocation and the corresponding stack space. 

* In Java (and many other languages), recursive method invocation is&mdash;just as the name implies&mdash;invocation. That means that each invocation requires additional stack space and a small amount of processing time for the invocation itself.

    With some language/compiler combinations, if the recursive implementation is written in a particular fashion, the compiler may be able to compile the recursive code into an iterative form in the byte code or machine code. Currently, Java does not do that&mdash;though some other languages running on the JVM, such as Scala, are capable of this. In any case, this does require that our code is written in a very specific way.
    
* We must be even more careful than usual about stopping conditions when using recursion. Notice that both of our example definitions, [(1)](#fn1) and [(2)](#fn2), have stopping conditions. In the [factorial](#fn1) case, if $n \in \{0, 1\}$ (that is if $n = 0$ or $n = 1$), then we don't need to perform any recursive evaluation. Similarly, in the [palindrome](#fn2) case, if the string length is 0 or 1, we don't have to perform any recursive evaluation. If, in our implementations, we were to check for our stopping conditions _after_ we perform recursion (or not check them at all), _we'd never reach our stopping conditions_, and the evaluation would not terminate (at least not in a useful fashion). 

    In an iterative implementation, bad or missing stopping conditions can result in an infinite loop&mdash;but the machine (virtual or otherwise) doesn't crash. Code on other threads (particularly those threads assigned to cores other than that assigned to the infinitely-looping thread) will continue to run.
    
    In a recursive implementation, bad/missing stopping conditions will most often result in stack memory being exhausted. In the best case,  
