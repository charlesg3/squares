(ns squares.core
  (:require [dali.io :as d])
  (:gen-class))

(defn make-svg-doc
  []
  (let [rotations 3.85
        boxes 200]
  (->> (for [i (range boxes)]
       (let [theta (* (/ (/ i 12) boxes) 360 rotations )
             center 512
               x center
               y center
               w (* 6 (+ i 30))
               h (* 6 (+ i 30))
               c (int (- 255 (* 160 (rand))))]
         [:g {:transform (format "rotate(%s %s %s)" theta center center)}
          [:rect {:stroke (format "rgb(%s, %s, %s)" c c c) :stroke-width (- 4 (* 1 (rand))) :fill :none
                  :stroke-opacity (+ (/ i boxes) (/ (rand) 8))
                  } [(- x (/ w 2.0)) (- y (/ h 2.0))] [w h]]]))
       (into [:dali/page {:width 1024 :height 1024} [:rect {:fill :black} [0 0] [1024 1024]]]))))

(defn -main
  [& args]
  (d/render-png (make-svg-doc) "resources/out.png"))
