# merkki 
[![Clojars Project](https://img.shields.io/clojars/v/merkki.svg)](https://clojars.org/merkki)

A Clojure(Script) library for rendering Markdown formatted strings

## Usage

`merkki` is a semi-opinionated set of functions for generating Markdown formatted strings. In addition to individual functions for various types of Markdown syntax, it also provides the `markdown` function itself, which takes a vector of elements and returns a Markdown-formatted string.

To use it, add this to your lein dependencies:

```
[merkki "0.2.1"]
```

Merkki's markdown syntax is in the form of nested vectors, which can be tagged with keywords to indicate specific markup. For example, the following code:

```clj
(require '[merkki.core :refer [markdown]])

(markdown
  [[:uh2 "Example"]
   "This is an example text"
   [:hr]
   [:ul
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

New tags can be defined by extending the multimethod `md-tag` found in the `merkki.tags` namespace.

## Development

Testing is configured for both Clojure and ClojureScript (via `doo`).

To run Clojure tests do:

```
lein test
```

For ClojureScript, tests are provided for both node and browser targets. Node tests can be run with `lein doo node node-test` provided node is installed.

For browser testing, doo uses Karma, which you will need to install via npm. See the [doo readme](https://github.com/bensu/doo) for instructions on how to set this up, and install the necessary Karma plugins for the browsers you wish to target. Once installed, you can do `lein doo [browser-name] browser-test`, provided you've installed the Karma plugin for that browser. Note that browser testing with doo/Karma is still in the experimental phase and has a few quirks; in particular it doesn't tend to run the tests automatically on first run, you will need to make and save some minor change to trigger the auto-run.

## License

Copyright © 2016 John S. Berry III

Distributed under the Eclipse Public License v1. See LICENSE for more information.
