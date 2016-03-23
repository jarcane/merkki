;;; Merkki - a Clojure library for Markdown generation
;;;
;;; Copyright (c) 2016 John S. Berry III
;;;
;;; This file is licensed under the Eclipse Public License v1.0. See LICENSE for more details

(ns merkki.headers
  (:require [merkki.util :refer :all]
            [merkki.tags :refer [md-tag]]))

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
;;; Methods for header elements
;;;

(defmethod md-tag :h1 [_ & xs] (apply h1 xs))
(defmethod md-tag :h2 [_ & xs] (apply h2 xs))
(defmethod md-tag :h3 [_ & xs] (apply h3 xs))
(defmethod md-tag :h4 [_ & xs] (apply h4 xs))
(defmethod md-tag :h5 [_ & xs] (apply h5 xs))
(defmethod md-tag :h6 [_ & xs] (apply h6 xs))
(defmethod md-tag :uh1 [_ & xs] (apply uh1 xs))
(defmethod md-tag :uh2 [_ & xs] (apply uh2 xs))
