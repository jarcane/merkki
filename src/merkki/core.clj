(ns merkki.core)

(defn nl
  "Appends a string with a newline"
  [s]
  (str s "\n"))

(defn break
  "Markdown standard allows hard-wrapping by not creating a new paragraph block automatically on new line.
   In order to guarantee that a <br>/newline is produced, a line must end in two spaces followed by new line.
   See: https://daringfireball.net/projects/markdown/syntax#p"
  [s]
  (str s "  \n"))

(defn header
  "Given a number and a string, tags the string as that header level and adds a new line"
  [n s]
  (str (reduce str (take n (repeat "#")))
       " "
       (break s)))

;; Pre-provided curried versions of header for quicker use
(def h1 (partial header 1))
(def h2 (partial header 2))
(def h3 (partial header 3))
(def h4 (partial header 4))
(def h5 (partial header 5))
(def h6 (partial header 6))

(defn u-header
  "Generates an underlined, multi-line setext style header, using the given underline character"
  [ch s]
  (str (break s)
       (break (reduce str (take (count s) (repeat ch))))))

;; Pre-provided curried versions of u-header for h1 and h2
(def uh1 (partial u-header "="))
(def uh2 (partial u-header "-"))

(defn em 
  "Wraps string for emphasis"
  [s]
  (str "*" s "*"))

(defn strong
  "Wraps string for strong emphasis"
  [s]
  (str "**" s "**"))

(defn link
  "Given a test string and url string, returns a properly wrapped link
   Optionally, a link title can be provided"
  ([text url]
   (link text url nil))
  ([text url title]
   (str "[" text "](" url (if title (str " \"" title "\"")) ")")))

(defn code
  ""
  []
  (str))

(defn image
  ""
  []
  (str))
