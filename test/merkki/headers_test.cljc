;;; Merkki - a Clojure library for Markdown generation
;;;
;;; Copyright (c) 2016 John S. Berry III
;;;
;;; This file is licensed under the Eclipse Public License v1.0. See LICENSE for more details

(ns merkki.headers-test
  (:require #?(:clj [clojure.test :refer :all]
               :cljs [cljs.test :refer-macros [deftest testing is]])
            [merkki.headers :refer [header h1 h2 h3 h4 h5 h6 u-header uh1 uh2]]))

;;;
;;; Header functions
;;;

(deftest header-test
  (testing "Expect header-fied string of matching level to number"
    (is (= (header 2 "Dave") "## Dave\n")))
  (testing "Curried versions return proper level"
    (is (= (h1 "Dave") "# Dave\n"))
    (is (= (h2 "Dave") "## Dave\n"))
    (is (= (h3 "Dave") "### Dave\n"))
    (is (= (h4 "Dave") "#### Dave\n"))
    (is (= (h5 "Dave") "##### Dave\n"))
    (is (= (h6 "Dave") "###### Dave\n"))))

(deftest u-header-test
  (testing "Expect two line string, with second line matching length of first with underline character"
    (is (= (u-header "=" "Dave")
           (str "Dave\n"
                "====\n"))))
  (testing "Test curried forms of u-header"
    (is (= (uh1 "Dave is fat")
           (str "Dave is fat\n"
                "===========\n")))
    (is (= (uh2 "Dave is fat")
           (str "Dave is fat\n"
                "-----------\n")))))
