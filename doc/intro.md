# Introduction to merkki

`merkki` is a library for generating Markdown formatted strings programmatically within Clojure. A number of helper functions are defined in the `block`, `headers`, `misc`, and `span` namespaces for generating strings directly for particular types of elements, however the main show is in the `merkki.core/markdown` function itself.

The `markdown` function takes a vector of unformatted Markdown elements. Each element can be one of the following:

* A string -> This is translated directly to a paragraph
* A vector of strings and span elements -> This is mapped over recursively to translate any elements into strings and then folded together into a paragraph
* A vector beginning with a keyword tag -> This is formatted according to the method attached to that tag
* Anything else -> All other elements are have `str` applied to them in the final reduction to a single string

Tagged elements take their arguments from the remaining elements in the vector, much as a Clojure function takes its arguments from the remaining elements of a list. So `[:link "Dave" "http://foo.com"]` is equivalent to calling `(merkki.span/link "Dave" "http://foo.com")`, or more precisely, to the multimethod linked to that function in the `merkki.span` namespace.

For an example of what this actually looks like, here is the main test example for the `markdown` function:

```clj
(markdown
  [[:uh1 "Markdown Test"]
   "This is a test of the merkki markdown rendering system."
   [:h2 "Span Elements"]
   ["A subvector of strings can be passed, allowing usage of span elements like "
    [:em "emphasized"] ", " [:strong "strong"] ", " [:link "links" "http://www.google.com"] ", and "
    [:code "code"] ". We can also do auto-links like so: " [:auto-link "http://www.github.com/jarcane/merkki"] "."]
   [:image
    "Here's a pug to show off images."
    "https://pbs.twimg.com/profile_images/378800000515894032/0758bced4b2f071fbf9916e15d28e7a8.jpeg"]
   [:h2 "Block Elements"]
   [:ol
    "Here"
    "Is"
    "An"
    "Ordered"
    "List"]
   [:ul
    "Here's"
    "An"
    "Unordered"
    "List"]
   [:code-block
    "(defn some-code [] \"Hello World!\")"]
   [:blockquote
    "Here is a big long block quote that is too long"
    "so we've made it onto multiple lines because that"
    "is just how we roll."]])
```

## Supported tags

As of version 0.2.0, `merkki` supports the following tags:

### Headers

```
:h1..6     arg -> # arg\n
:uh1 & uh2 arg -> arg\n
                  ===\n
```

All header types end their outputs with a new line.

### Span elements

```
:em        x -> *x*
:strong    x -> **x**
:code      x -> ``x``
:auto-link url -> <url>
:link      text url [title] -> [text](url "title")
:image     alt url [title]  -> ![alt](url "title")
```

### Block elements

```
:ul x y z ... -> * x
                 * y
                 * z
                 * ...
                 
:ol x y z ... -> 1. x
                 2. y
                 3. z
                 X. ...
                 
:code-block text [language] -> ```language
                               text
                               ```
                               
:blockquote some lines here ... -> > some
                                   > lines
                                   > here
                                   > ...

```

Block elements end each line generated with a new line.

### Misc Elements

```
:hr -> ***\n
```

## Extending merkki

`merkki` keyword tags are actually dispatch values for the multimethod `merkki.tags/md-tag`, and thus additional tags can be defined and added by defining new methods for `md-tag`. Keywords not defined as methods will cause the `markdown` function to throw an error. Remember also that when writing your methods, the first argument to an md-tag method will always be the keyword tag itself, and can probably be safely ignored.

Let's say that we wish to add a new tag for doing underline style emphasis. For example:

```
(ns example.merkki
  (:require [merkki.core :refer [markdown]]
            [merkki.tags :refer [md-tag]]
            [merkki.util :refer [wrap]])

(defmethod md-tag :u-em [_ s] (wrap "_" s))

(markdown [[:u-em "Pork"]])
; => "_Pork_"
```

We could even potentially change the behavior of `:em` itself this way, though this is perhaps best done with care. This is however one way one might redefine new dialects of Markdown for one's own projects. 
