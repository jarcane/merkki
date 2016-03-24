;;; Merkki - a Clojure library for Markdown generation
;;;
;;; Copyright (c) 2016 John S. Berry III
;;;
;;; This file is licensed under the Eclipse Public License v1.0. See LICENSE for more details

(ns merkki.misc-test
  (:require #?(:clj [clojure.test :refer :all]
               :cljs [cljs.test :refer-macros [deftest testing is]])
            [merkki.misc :refer :all]))

;;;
;;; Misc elements
;;;

(deftest hr-test
  (testing "Returns proper horizontal rule line"
    (is (= (hr)
           "***\n"))))
