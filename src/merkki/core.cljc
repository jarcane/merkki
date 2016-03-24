;;; Merkki - a Clojure library for Markdown generation
;;;
;;; Copyright (c) 2016 John S. Berry III
;;;
;;; This file is licensed under the Eclipse Public License v1.0. See LICENSE for more details

(ns merkki.core
  (:require [merkki.util :refer [nl break]]
            [merkki.tags :refer [md-tag]]
            merkki.block
            merkki.headers
            merkki.misc
            merkki.span))

;;;
;;; Element handlers for markdown function
;;;

(defn- ensure-nl
  "Ensures that the element ends in a newline"
  [elem]
  (if (= \newline
         (last elem))
    elem
    (nl elem)))

(defn- handle-element
  "The main element handler for each block in a markdown sequence"
  [elem]
  (cond
   (vector? elem) (if (keyword? (first elem))
                    (->> (rest elem)
                         (map handle-element)
                         (apply md-tag (first elem)))
                    (->> elem
                         (map handle-element)
                         (reduce str)))
   :else elem))

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
