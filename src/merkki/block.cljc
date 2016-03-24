;;; Merkki - a Clojure library for Markdown generation
;;;
;;; Copyright (c) 2016 John S. Berry III
;;;
;;; This file is licensed under the Eclipse Public License v1.0. See LICENSE for more details

(ns merkki.block
  (:require [merkki.util :refer [nl]]
            [merkki.tags :refer [md-tag]]))

;;;
;;; Block Elements
;;;

(defn ul
  "Creates an unordered list from the given strings. Newlines are not necessary."
  [& xs]
  (reduce #(str %1 "* " (nl %2)) "" xs))

(defn ol
  "Creates a properly numbered ordered list from the given strings. Newlines are not necessary."
  [& xs]
  (->> xs
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
  [& xs]
  (->> xs
       (map #(str "> " (nl %)))
       (reduce str)))

;;;
;;; Methods for block elements
;;;

(defmethod md-tag :ul [_ & xs] (apply ul xs))
(defmethod md-tag :ol [_ & xs] (apply ol xs))
(defmethod md-tag :code-block [_ & xs] (apply code-block xs))
(defmethod md-tag :blockquote [_ & xs] (apply blockquote xs))
