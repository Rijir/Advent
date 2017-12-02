(ns day6.core
  (:require [clojure.string :as string]
            [clojure.java.io :as io])
  (:gen-class))

(defn build-row
  [w y]
  (loop [x 0
         row []]
    (if (>= x w)
      row
      (recur (+ x 1)
             (conj row {:x x, :y y, :on false, :brightness 0})))))

(defn build-grid
  [w h]
  (loop [y 0
         grid []]
    (if (>= y h)
      grid
      (recur (+ y 1)
             (conj grid (build-row w y))))))

(defn parse-instruction
  [st]
  (let [command (cond
                  (.startsWith st "turn on") :on
                  (.startsWith st "turn off") :off
                  (.startsWith st "toggle") :toggle
                  :else (throw (Exception. "bad instruction")))
        coords (re-seq #"[0-9]+,[0-9]+" st)
        start-coords (re-seq #"[0-9]+" (first coords))
        end-coords (re-seq #"[0-9]+" (second coords))
        start-x (Integer/parseInt (first start-coords))
        start-y (Integer/parseInt (second start-coords))
        end-x (Integer/parseInt (first end-coords))
        end-y (Integer/parseInt (second end-coords))]
    (hash-map :command command,
              :start-x start-x,
              :start-y start-y,
              :end-x end-x,
              :end-y end-y)))

(defn process-light
  [light inst]
  (if (and
       (and (>= (:x light) (:start-x inst))
            (<= (:x light) (:end-x inst)))
       (and (>= (:y light) (:start-y inst))
            (<= (:y light) (:end-y inst))))
    (cond
      (= (:command inst) :on)
      (assoc light
             :on true
             :brightness (inc (:brightness light)))
      (= (:command inst) :off)
      (assoc light
             :on false
             :brightness (max 0 (dec (:brightness light))))
      (= (:command inst) :toggle)
      (assoc light
             :on (not (:on light))
             :brightness (+ 2 (:brightness light)))
      :else
      (throw (Exception.
              (str "invalid command type" (:command inst)))))
    light))

(defn get-next-grid
  [prev inst]
  (map (fn [row]
         (map (fn [light]
                (process-light light inst))
              row))
       prev))

(defn count-on-lights
  [grid]
  (reduce
   +
   (map (partial reduce
                 (fn [row-sum light]
                   (if (:on light)
                     (inc row-sum)
                     row-sum))
                 0)
        grid)))

(defn get-total-brightness
  [grid]
  (reduce
   +
   (map (partial reduce
                 (fn [row-b light]
                   (+ row-b (:brightness light)))
                 0)
        grid)))

;; missed "To a minimum of 0" on turn off
;;(defn get-new-brightness
;;  [brightness inst]
;;  (let [mult (cond
;;               (= (:command inst) :on) 1
;;               (= (:command inst) :off) -1
;;               (= (:command inst) :toggle) 2)
;;        area (* (- (inc (:end-x inst)) (:start-x inst))
;;                (- (inc (:end-y inst)) (:start-y inst)))]
;;    (+ brightness (* mult area))))

(defn -main
  [& args]
  (println "Reading instruction book...")
  (let [instructions-string (.split
                             (slurp (io/resource "input"))
                             "\n")
        instructions (map parse-instruction
                          instructions-string)]
    (println "Flipping switches...")
    (let [final-grid (reduce get-next-grid
                             (build-grid 1000 1000)
                             instructions)]
          (cond
            (= (first args) "part-1")
            (println "Part 1: "
                     (count-on-lights final-grid))
            (= (first args) "part-2")
            (do (println "Part 2: "
                         (get-total-brightness final-grid)))))))
