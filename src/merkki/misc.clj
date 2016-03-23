;;; Merkki - a Clojure library for Markdown generation
;;;
;;; Copyright (c) 2016 John S. Berry III
;;;
;;; This file is licensed under the Eclipse Public License v1.0. See LICENSE for more details

(ns merkki.misc
  (:require [merkki.util :refer :all]
            [merkki.tags :refer [md-tag]]))

;;;
;;; Misc elements
;;;

(def hr
  "Inserts a horizontal rule"
  (partial nl "***"))

;;;
;;; Methods for misc elements
;;;

(defmethod md-tag :hr [_] (hr))
