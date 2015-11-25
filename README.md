dbbeans-util
============

Over some 15 years of Java coding, I came up with a lot of small utility
functions to fill recurring needs in my programs.

In the java tradition, I collected most of them as static functions
in classes named after their area of application.

I also created a few utility classes that are included in this library.

Most of the tools here were written in the context of database driven
web application development. They will probably be most useful to you
in a similar context.

Java 6 or higher is required. (Java 5 might work if you recompile the
sources. I haven't tried it recently.)

## What's included

Please see the
[Javadoc pages](http://software.pythoud.net/javadoc/dbbeans-util/)
for detailed information.

You might find that some functions duplicate facilities that now exist
as part of the standard library or Apache Common libraries. Please
remember that I started collecting these functions in the days of
Java 1.3 and that I had to keep most of them in the library for backward
compatibility.

### String tools

The functions I use the most are the ones in the _Strings_ class.

They will allow you to transform, parse, convert, concatenate and
replace parts of strings.

### Dates

I also use a lot the functions in the _Dates_
class, with their companion classes _SimpleInputDateFormat_,
_SimpleInputTimeFormat_ and _SimpleInputTimestampFormat_.

Please note that the _Date_ class used in creating and manipulating dates
is _java.sql.Date_ and not _java.util.Date_. This library was created to
be used in database driven applications.

The functions in _Dates_ will allow you to parse, check the formatting
and extract partial information from dates, times and timestamps (as
represented by the _Date_, _Time_ and _Timestamp_ classes in the
_java.sql_ package).

### _Money_ and _MoneyFormat_

Those two classes allow you to parse and print currency values.

### Pairs

The _Pair_ class is a trivial implementation of the pair data structure.

### Emails

The _Email_ class is is a wrapper around
_org.apache.commons.mail.HtmlEmail_. It's intent is to make it quick
and easy to send HTML emails from a web application. The class can be
used as is or subclassed. I often do the latter.

_EmailValidator_ allows basic validation of email address format.

### HTML

_HTMLText_ contains function to manipulate text in an HTML/web
application context.

It mostly contains function to manipulate or convert unicode characters,
HTML entities and accented letters, and escape some HTML constructs.

### Images

The _Images_ class contains function to manipulate and resize images.

### Files

The _Files_ class contains function to manipulate file name (mostly to
manage file extensions).

### JSON

The _json_ subpackage contains classes that can be used to represent
a JSON document.

The base class is _JsonObject_. Once you have created a JsonObject, you
can add subobjects of the different types to it to compose your document.
Once this is done, you can call the _print()_ function on the JsonObject
to create the JSON string representation.

I'll add some code examples in the future.

I'm also planning to create a JSON parser that will produce JsonObjects.
At that point, I might spin off the json classes into their own library.

## Code using this library

This library is used in the following projects:

- [dbbeans-sql](https://github.com/cpythoud/dbbeans-sql):
  a wrapper library around JDBC, best used with lambdas in Java 8
  but will work with Java 6+ and inner classes
  (full documentation coming soon, Javadoc available
  [here](http://software.pythoud.net/javadoc/dbbeans-sql/));
- [html-codegen](https://github.com/cpythoud/html-codegen):
  a Java library to generate HTML code;
- [java-codegen](https://github.com/cpythoud/java-codegen):
  a Java library to generate Java code;
- [Beanmaker](https://github.com/cpythoud/beanmaker):
  a Java library and framework to generate Java code in relation
  with a relational database; this project uses all the above
  libraries;
- CMSAlpha: CMS (Content Management System), for use by
  professional wed designers and web application developers,
  based on Servlets, JSP and the above libraries;
  GitHub repository coming soon.
