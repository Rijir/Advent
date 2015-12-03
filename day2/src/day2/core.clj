(ns day2.core
  (:gen-class)
  (:require [clojure.core.reducers :as r]
            [clojure.java.io :as io]
            [clojure.string :as st]))

(defn paper-for-box
  [l w h]
  (let [s1 (* l w)
        s2 (* l h)
        s3 (* w h)]

    (+ (* 2 s1) (* 2 s2) (* 2 s3) (min s1 s2 s3))))

(defn parse-dimentions
  [st-dims]
  (map #(Integer/parseInt %) st-dims))

(defn get-box-dimentions
  [s]
  (map parse-dimentions
       (map #(st/split % #"x")
            (st/split s #"\n"))))

(defn get-smallest-perimeter
  [l w h]
  (min (+ (* 2 l) (* 2 w))
       (+ (* 2 l) (* 2 h))
       (+ (* 2 w) (* 2 h))))

(defn get-vol
  [l w h]
  (* l w h))

(defn get-ribon-length
  [l w h]
  (+ (get-smallest-perimeter l w h) (get-vol l w h)))

(defn part1
  [s]
  (r/fold + (map #(apply paper-for-box %) (get-box-dimentions s))))

(defn part2
  [s]
  (r/fold + (map #(apply get-ribon-length %) (get-box-dimentions s))))

(defn -main
  "given the name of the list of dimentions, calculates the amount of wrapping paper required"
  [& args]
  (let [box-list (slurp (io/resource "list"))]
    (println "Total square feet of paper: " (part1 box-list))
    (println "Total feet of ribon: " (part2 box-list))))
