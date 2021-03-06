;;; Merkki - a Clojure library for Markdown generation
;;;
;;; Copyright (c) 2016 John S. Berry III
;;;
;;; This file is licensed under the Eclipse Public License v1.0. See LICENSE for more details

(ns merkki.util-test
  (:require #?(:clj [clojure.test :refer :all]
               :cljs [cljs.test :refer-macros [deftest testing is]])
            [merkki.util :refer [nl break wrap]]))

;;;
;;; Helper functions
;;;

(deftest nl-test
  (testing "Expect string with new line added"
    (is (= (nl "Dave") "Dave\n"))))

(deftest break-test
  (testing "Expect string with two spaces and a newline"
    (is (= (break "Dave") "Dave  \n"))))

(deftest wrap-test
  (testing "Expect string wrapped in the given characters"
    (is (= (wrap "*" "Dave")
           "*Dave*"))))
