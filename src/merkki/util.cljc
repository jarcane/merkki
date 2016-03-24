;;; Merkki - a Clojure library for Markdown generation
;;;
;;; Copyright (c) 2016 John S. Berry III
;;;
;;; This file is licensed under the Eclipse Public License v1.0. See LICENSE for more details

(ns merkki.util
  (:require [merkki.util :refer :all]))

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
