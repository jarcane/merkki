(ns merkki.core)

(defn header
  "Given a number and a string, tags the string as that header level and adds a new line"
  [n s]
  (str (reduce str (take n (repeat "#")))
       " "
       s
       "  \n"))

(def h1 (partial header 1))
(def h2 (partial header 2))
(def h3 (partial header 3))
(def h4 (partial header 4))
(def h5 (partial header 5))
(def h6 (partial header 6))

(defn em 
  "Wraps string for emphasis"
  [s]
  (str "*" s "*"))

(defn strong
  "Wraps string for strong emphasis"
  [s]
  (str "**" s "**"))
