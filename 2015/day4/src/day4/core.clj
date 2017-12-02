(ns day4.core
  (:require [clojure.string :as string])
  (:gen-class))

(defn md5 [s]
   (string/join (map (partial format "%02x")
        (.digest (doto (java.security.MessageDigest/getInstance "MD5")
                   .reset
                   (.update (.getBytes s)))))))

(defn five-zeros
  [s]
  (.startsWith s "00000"))

(defn six-zeros
  [s]
  (.startsWith s "000000"))

(defn get-thread-fn
  [k i f]
  (fn []
    (f (md5 (str k i)))))

(defn find-first-message
  [k f]
  (loop [i 1]
    (if (f (md5 (str k i)))
      i
      (recur (+ 1 i)))))

(defn part-1
  [k]
  (find-first-message k five-zeros))

(defn part-2
  [k]
  (find-first-message k six-zeros))

(defn -main
  [& args]
  (let [key "yzbqklnj"]
    (if (= (first args) "1")
      (println "part 1: " (part-1 key))
      (println "part 2: " (part-2 key)))))
