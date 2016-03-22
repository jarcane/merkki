;;; Merkki - a Clojure library for Markdown generation
;;;
;;; Copyright (c) 2016 John S. Berry III
;;;
;;; This file is licensed under the Eclipse Public License v1.0. See LICENSE for more details

(ns merkki.core)

;;;
;;; Helper functions
;;;

(defn nl
  "Appends a string with a newline"
  [s]
  (str s "\n"))

(defn break
  "Markdown standard allows hard-wrapping by not creating a new paragraph block automatically 
   on new line. In order to guarantee that a <br> is inserted, a line must end in two spaces 
   followed by new line.
  See: https://daringfireball.net/projects/markdown/syntax#p"
  ([] (nl "  "))
  ([s]
   (str s (nl "  "))))

(defn wrap
  "Helper function. Wraps a given string in the indicated characters."
  [chars s]
  (str chars s chars))

;;;
;;; Header functions
;;;

(defn header
  "Given a number and a string, tags the string as that header level (atx-style) and adds a new line"
  [n s]
  (str (reduce str (take (Integer. n) (repeat "#")))
       " "
       (nl s)))

;; Pre-provided curried versions of header for quicker use
(def h1 (partial header 1))
(def h2 (partial header 2))
(def h3 (partial header 3))
(def h4 (partial header 4))
(def h5 (partial header 5))
(def h6 (partial header 6))

(defn u-header
  "Generates an underlined, multi-line setext style header, using the given underline character"
  [ch s]
  (str (nl s)
       (nl (reduce str (take (count s) (repeat ch))))))

;; Pre-provided curried versions of u-header for h1 and h2
(def uh1 (partial u-header "="))
(def uh2 (partial u-header "-"))

;;;
;;; Span elements
;;;

(def em 
  "Wraps string for emphasis"
  (partial wrap "*"))

(def strong
  "Wraps string for strong emphasis"
  (partial wrap "**"))

(defn link
  "Given a test string and url string, returns a properly wrapped link
   Optionally, a link title can be provided"
  ([text url]
   (link text url nil))
  ([text url title]
   (str "[" text "](" url (if title (str " \"" title "\"")) ")")))

(defn image
  "Given an alt-text, url, and optional title, returns a properly wrapped 
   image link"
  ([alt url]
   (image alt url nil))
  ([alt url title]
   (str "!" (link alt url title))))

(defn auto-link
  "For self-wrapping links, returns the given url wrapped in <>"
  [url]
  (str "<" url ">"))

(def code
  "Wraps string in double backtick, for inline code. Double is used instead of single, to allow safe use of 
   single backtick within the string."
  (partial wrap "``"))

;;;
;;; Block Elements
;;;

(defn ul
  "Creates an unordered list from the given strings. Newlines are not necessary."
  [& rest]
  (reduce #(str %1 "* " (nl %2)) "" rest))

(defn ol
  "Creates a properly numbered ordered list from the given strings. Newlines are not necessary."
  [& rest]
  (->> rest
       (map #(str %1 ". " (nl %2))
            (iterate inc 1))
       (reduce str)))

(defn code-block
  "Wraps the given text in a code block, with optional language parameter according to Github flavor"
  ([text]
   (code-block text nil))
  ([text language]
   (str (if language
          (nl (str "```" language))
          (nl "```"))
        (nl text)
        (nl "```"))))

(defn blockquote
  "Wraps a series of given strings as individual lines of a blockquoted paragraph"
  [& rest]
  (->> rest
       (map #(str "> " (nl %)))
       (reduce str)))

;;;
;;; Misc elements
;;;

(def hr
  "Inserts a horizontal rule"
  (partial nl "***"))

;;;
;;; Element handlers for markdown function
;;;

(defn ensure-nl
  "Ensures that the element ends in a newline"
  [elem]
  (if (= \newline
         (last elem))
    elem
    (nl elem)))

(defn handle-element
  "The main element handler for each block in a markdown sequence"
  [elem]
  (cond
   (string? elem) elem
   (vector? elem) (if (fn? (first elem))
                    (->> (rest elem)
                         (map handle-element)
                         (apply (first elem)))
                    (->> elem
                         (map handle-element)
                         (reduce str)))
   :else (str elem)))

;;;
;;; The markdown function
;;;

(defn markdown
  "Given a vector of markdown elements, processes each element and assembles the result into a single 
  markdown formatted string"
  [v]
  (->> v
       (map handle-element)
       (map ensure-nl)
       (map break)
       (reduce str)))
