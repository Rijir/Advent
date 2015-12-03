(ns day3.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [clojure.string :as st]))

(defn get-next-house
  [dir cur]
  (cond
    (= dir \^) (list (first cur) (+ (second cur) 1))
    (= dir \v) (list (first cur) (- (second cur) 1))
    (= dir \>) (list (+ (first cur) 1) (second cur))
    (= dir \<) (list (- (first cur) 1) (second cur))))

(defn get-houses
  ([dirs]
   (let [start '(0 0)]
     (get-houses dirs start (list start))))
  ([dirs cur houses]
   (if (nil? dirs)
     houses
     (let [next-house (get-next-house (first dirs) cur)]
       (recur (next dirs) next-house (cons next-house houses))))))

(defn get-part2-houses
  ([dirs]
   (let [start '(0 0)]
     (get-part2-houses dirs start start (list start))))
  ([dirs cur-santa cur-robo houses]
   (cond
     (nil? dirs) houses
     (nil? (next dirs)) (cons (get-next-house (first dirs) cur-santa)
                              houses)
     :else (let [next-santa (get-next-house (first dirs) cur-santa)
                 next-robo (get-next-house (second dirs) cur-robo)]
             (recur (next (next dirs))
                    next-santa
                    next-robo
                    (cons next-robo (cons next-santa houses)))))))

(defn get-unique
  ([xs eq]
   (get-unique xs eq '()))
  ([xs eq unique]
   (if (nil? xs)
     unique
     (if (some #(eq (first xs) %) unique)
       (recur (next xs) eq unique)
       (recur (next xs) eq (cons (first xs) unique))))))

(defn point-eq
  [p1 p2]
  (and (= (first p1) (first p2))
       (= (second p1) (second p2))))

(defn part1
  [dir-string]
  (count (get-unique (get-houses (seq dir-string))
                     point-eq)))

(defn part2
  [dir-string]
  (count (get-unique (get-part2-houses (seq dir-string))
                     point-eq)))

(defn -main
  [& args]
  (let [dirs-string (st/trim (slurp (io/resource "directions")))]
    (println "Santa visits " (part1 dirs-string) "houses")
    (println "Santa and Robo-Santa visit " (part2 dirs-string) " houses")))
