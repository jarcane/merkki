;;; Merkki - a Clojure library for Markdown generation
;;;
;;; Copyright (c) 2016 John S. Berry III
;;;
;;; This file is licensed under the Eclipse Public License v1.0. See LICENSE for more details

(ns merkki.tags)

;;;
;;; Multimethod for format tag keywords
;;; 

(defmulti md-tag
  "This multimethod dispatches on keyword, which is expected to match an existing method for handling a markdown vector.
  The default method will throw an exception on invalid keys. Remember that the first argument to a method will always 
  be the tag keyword itself."
  (fn [x & _] x))

(defmethod md-tag :default invalid-key [k & rest]
  (throw (#?(:clj Exception.
             :cljs js/Error.) (str "Unrecognized key: " (pr-str k)))))
