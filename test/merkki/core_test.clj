(ns merkki.core-test
  (:require [clojure.test :refer :all]
            [merkki.core :refer :all]))

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
           (str "Dave  \n"
                "====  \n"))))
  (testing "Test curried forms of u-header"
    (is (= (uh1 "Dave is fat")
           (str "Dave is fat  \n"
                "===========  \n")))
    (is (= (uh2 "Dave is fat")
           (str "Dave is fat  \n"
                "-----------  \n")))))

(deftest em-test
  (testing "Expect emphasised string"
    (is (= (em "Dave") "*Dave*"))))

(deftest strong-test
  (testing "Expect bolded (strong em) string"
    (is (= (strong "Dave") "**Dave**"))))
