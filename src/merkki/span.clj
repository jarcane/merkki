;;; Merkki - a Clojure library for Markdown generation
;;;
;;; Copyright (c) 2016 John S. Berry III
;;;
;;; This file is licensed under the Eclipse Public License v1.0. See LICENSE for more details

(ns merkki.span
  (:require [merkki.util :refer :all]))

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
