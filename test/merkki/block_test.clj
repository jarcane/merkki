;;; Merkki - a Clojure library for Markdown generation
;;;
;;; Copyright (c) 2016 John S. Berry III
;;;
;;; This file is licensed under the Eclipse Public License v1.0. See LICENSE for more details

(ns merkki.span-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [merkki.span :refer :all]))

;;;
;;; Block Elements
;;;

(deftest ul-test
  (testing "Expect a properly ordered and marked up list from the given arguments"
    (is (= (ul "Red" "Green" "Blue")
           "* Red\n* Green\n* Blue\n"))))

(deftest ol-test
  (testing "Expect a properly ordered and numbered list from the given arguments"
    (is (= (ol "Red" "Green" "Blue")
           "1. Red\n2. Green\n3. Blue\n"))))

(deftest code-block-test
  (testing "Expect a properly laid out multiline code block"
    (is (= (code-block "let x = 45;\nx += 1;")
           "```\nlet x = 45;\nx += 1;\n```\n")))
  (testing "Optional language parameter for Github flavor"
    (is (= (code-block "let x = 45;\nx += 1;" "javascript")
           "```javascript\nlet x = 45;\nx += 1;\n```\n"))))

(deftest blockquote-test
  (testing "Expect a series of lines prepended by > and followed by newline"
    (is (= (blockquote "Dave is a" "very, very" "fat man.")
           "> Dave is a\n> very, very\n> fat man.\n"))))
