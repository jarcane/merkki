;;; Merkki - a Clojure library for Markdown generation
;;;
;;; Copyright (c) 2016 John S. Berry III
;;;
;;; This file is licensed under the Eclipse Public License v1.0. See LICENSE for more details

(ns merkki.span-test
  (:require #?(:clj [clojure.test :refer :all]
               :cljs [cljs.test :refer-macros [deftest testing is]])
            [merkki.span :refer :all]
            [merkki.util :refer :all]))

;;;
;;; Span elements
;;;

(deftest em-test
  (testing "Expect emphasised string"
    (is (= (em "Dave") "*Dave*")))
  (testing "em is a function of wrap"
    (is (= (em "Dave")
           (wrap "*" "Dave")))))

(deftest strong-test
  (testing "Expect bolded (strong em) string"
    (is (= (strong "Dave") "**Dave**")))
  (testing "strong is a function of wrap"
    (is (= (strong "Dave")
           (wrap "**" "Dave")))))

(deftest link-test
  (testing "Expect a properly marked up link"
    (is (= (link "Dave" "http://www.dave.com")
           "[Dave](http://www.dave.com)")))
  (testing "Testing optional title parameter"
    (is (= (link "Dave" "http://www.dave.com" "Dave")
           "[Dave](http://www.dave.com \"Dave\")"))))

(deftest image-test
  (testing "Expect a properly marked up image link"
    (is (= (image "Dave" "http://www.dave.com/dave.jpg")
           "![Dave](http://www.dave.com/dave.jpg)")))
  (testing "Testing optional title parameter"
    (is (= (image "Dave" "http://www.dave.com/dave.jpg" "Dave")
           "![Dave](http://www.dave.com/dave.jpg \"Dave\")")))
  (testing "Image is a function of link"
    (is (= (image "Dave" "http://www.dave.com/dave.jpg")
           (str "!" (link "Dave" "http://www.dave.com/dave.jpg"))))))

(deftest auto-link-test
  (testing "Expect a properly wrapped link"
    (is (= (auto-link "http://dave.com")
           "<http://dave.com>"))))

(deftest code-test
  (testing "Expect a backtick wrapped version of string"
    (is (= (code "var foo = 5")
           "``var foo = 5``")))
  (testing "Single backtick should be usable within the string"
    (is (= (code "`(foo ,bar)")
           "```(foo ,bar)``")))
  (testing "code is a function of wrap"
    (is (= (code "(+ 1 2 3)")
           (wrap "``" "(+ 1 2 3)")))))
