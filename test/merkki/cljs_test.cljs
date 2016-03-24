;;; Merkki - a Clojure library for Markdown generation
;;;
;;; Copyright (c) 2016 John S. Berry III
;;;
;;; This file is licensed under the Eclipse Public License v1.0. See LICENSE for more details

(ns merkki.cljs-test
  (:require [doo.runner :refer-macros [doo-tests]]
            [merkki.core-test]
            [merkki.block-test]
            [merkki.headers-test]
            [merkki.misc-test]
            [merkki.span-test]
            [merkki.util-test]))

(doo-tests 'merkki.core-test
           'merkki.block-test
           'merkki.headers-test
           'merkki.misc-test
           'merkki.span-test
           'merkki.util-test)
