;;; Merkki - a Clojure library for Markdown generation
;;;
;;; Copyright (c) 2016 John S. Berry III
;;;
;;; This file is licensed under the Eclipse Public License v1.0. See LICENSE for more details

(ns merkki.core
  (:require [merkki.util :refer :all]
            [merkki.headers :refer :all]
            [merkki.span :refer :all]
            [merkki.block :refer :all]
            [merkki.misc :refer :all]))

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
