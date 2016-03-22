# merkki

A Clojure library for rendering Markdown formatted strings

## Usage

`merkki` is a semi-opinionated set of functions for generating Markdown formatted strings. In addition to individual functions for various types of Markdown syntax, it also provides the `markdown` function itself, which takes a vector of elements and returns a Markdown-formatted string.

To use it, add this to your lein dependencies:

```
[merkki "0.1.0-SNAPSHOT"]
```


Merkki's markdown syntax is in the form of nested vectors, and these vectors can be prepended with other merkki functions that return markdown. For example, the following code:

```clj
(markdown
  [[uh2 "Example"]
   "This is an example text"
   [hr]
   [ul
    "Here's"
    "A"
    "List"]])
```
    
Will produce the following Markdown output:

```md
Example
-------

This is an example text

***

* Here's
* A
* List
```

## License

Copyright © 2016 John S. Berry III

Distributed under the GNU Affero General Public License v3. See LICENSE for more information.
