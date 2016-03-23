;;; Merkki - a Clojure library for Markdown generation
;;;
;;; Copyright (c) 2016 John S. Berry III
;;;
;;; This file is licensed under the Eclipse Public License v1.0. See LICENSE for more details

(ns merkki.core-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :as io]
            [merkki.core :refer :all]
            [merkki.headers :refer :all]
            [merkki.misc :refer :all]
            [merkki.span :refer :all]
            [merkki.block :refer :all]
            [merkki.util :refer :all]))

;;;
;;; Markdown test
;;;

(deftest markdown-test
  (testing "Given a vector of block elements, return a string of markdown formatted text"
    (is (= (let [test-text (markdown
                            [[:uh1 "Markdown Test"]
                             "This is a test of the merkki markdown rendering system."
                             [:h2 "Span Elements"]
                             ["A subvector of strings can be passed, allowing usage of span elements like "
                              [:em "emphasized"] ", " [:strong "strong"] ", " [:link "links" "http://www.google.com"] ", and "
                              [:code "code"] ". We can also do auto-links like so: " [:auto-link "http://www.github.com/jarcane/merkki"] "."]
                             [:image
                              "Here's a pug to show off images."
                              "https://pbs.twimg.com/profile_images/378800000515894032/0758bced4b2f071fbf9916e15d28e7a8.jpeg"]
                             [:h2 "Block Elements"]
                             [:ol
                              "Here"
                              "Is"
                              "An"
                              "Ordered"
                              "List"]
                             [:ul
                              "Here's"
                              "An"
                              "Unordered"
                              "List"]
                             [:code-block
                              "(defn some-code [] \"Hello World!\")"]
                             [:blockquote
                              "Here is a big long block quote that is too long"
                              "so we've made it onto multiple lines because that"
                              "is just how we roll."]])]
             (spit "/tmp/merkki-temp.md" test-text)
             test-text)
           (slurp (io/resource "merkki-test.md"))))))
