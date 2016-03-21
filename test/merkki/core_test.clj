;;; Merkki - a Clojure library for Markdown generation
;;;
;;; Copyright (c) 2016 John S. Berry III
;;;
;;; This file is licensed under the Affero GPL v3. See LICENSE for more details

(ns merkki.core-test
  (:require [clojure.test :refer :all]
            [merkki.core :refer :all]))

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

;;;
;;; Header functions
;;;

(deftest header-test
  (testing "Expect header-fied string of matching level to number"
    (is (= (header 2 "Dave") "## Dave  \n")))
  (testing "Curried versions return proper level"
    (is (= (h1 "Dave") "# Dave  \n"))
    (is (= (h2 "Dave") "## Dave  \n"))
    (is (= (h3 "Dave") "### Dave  \n"))
    (is (= (h4 "Dave") "#### Dave  \n"))
    (is (= (h5 "Dave") "##### Dave  \n"))
    (is (= (h6 "Dave") "###### Dave  \n"))))

(deftest u-header-test
  (testing "Expect two line string, with second line matching length of first with underline character"
    (is (= (u-header "=" "Dave")
           (str "Dave\n"
                "====  \n"))))
  (testing "Test curried forms of u-header"
    (is (= (uh1 "Dave is fat")
           (str "Dave is fat\n"
                "===========  \n")))
    (is (= (uh2 "Dave is fat")
           (str "Dave is fat\n"
                "-----------  \n")))))

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

;;;
;;; Block Elements
;;;

(deftest ul-test
  (testing "Expect a properly ordered and marked up list from the given arguments"
    (is (= (ul "Red" "Green" "Blue")
           "* Red\n* Green\n* Blue\n  \n"))))

(deftest ol-test
  (testing "Expect a properly ordered and numbered list from the given arguments"
    (is (= (ol "Red" "Green" "Blue")
           "1. Red\n2. Green\n3. Blue\n  \n"))))

;;;
;;; Misc elements
;;;

(deftest hr-test
  (testing "Returns proper horizontal rule line"
    (is (= (hr)
           "***  \n"))))
