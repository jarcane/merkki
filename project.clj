(defproject merkki "0.2.0"
  :description "A Clojure library for generating Markdown formatted text."
  :url "http://github.com/jarcane/merkki"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.7.0"]]

  :plugins [[cider/cider-nrepl "0.8.1"]
            [lein-doo "0.1.6"]]

  :cljsbuild
  {:builds [{:id "test"
             :source-paths ["src" "test"]
             :compiler {:output-to "target/testable.js"
                        :output-dir "target"
                        :target :nodejs
                        :main merkki.cljs-test
                        :optimizations :none}}]})
