;; gorilla-repl.fileformat = 1

;; **
;;; # 2D physics example from lecture 4
;; **

;; @@
(use 'nstools.ns)

(ns+ bounce-worksheet
     (:like anglican-user.worksheet)
     (:require [org.nfrac.cljbox2d.core :refer [position]])
     (:use [anglican [state 
                      :only [get-predicts 
                             get-log-weight 
                             get-result]]]))

(require '[bounce :refer [create-world
                          show-world-simulation
                          simulate-world
                          display-static-world
                          balls-in-box]] 
         :reload)
;; @@
;; =>
;;; {"type":"list-like","open":"","close":"","separator":"</pre><pre>","items":[{"type":"list-like","open":"","close":"","separator":"</pre><pre>","items":[{"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"},{"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}],"value":"[nil,nil]"},{"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}],"value":"[[nil,nil],nil]"}
;; <=

;; @@
(def bumper-location1 (list))

(show-world-simulation bumper-location1)
;; @@
;; =>
;;; {"type":"list-like","open":"","close":"","separator":"</pre><pre>","items":[{"type":"html","content":"<span class='clj-var'>#&#x27;bounce-worksheet/bumper-location1</span>","value":"#'bounce-worksheet/bumper-location1"},{"type":"html","content":"<span class='clj-unkown'>#object[quil.Applet 0x2e01259b &quot;quil.Applet[panel1,0,0,600x500,layout=java.awt.FlowLayout]&quot;]</span>","value":"#object[quil.Applet 0x2e01259b \"quil.Applet[panel1,0,0,600x500,layout=java.awt.FlowLayout]\"]"}],"value":"[#'bounce-worksheet/bumper-location1,#object[quil.Applet 0x2e01259b \"quil.Applet[panel1,0,0,600x500,layout=java.awt.FlowLayout]\"]]"}
;; <=

;; @@
(def bumper-location2 (list (list -3 6) (list 7 4)))

(show-world-simulation bumper-location2)
;; @@
;; =>
;;; {"type":"list-like","open":"","close":"","separator":"</pre><pre>","items":[{"type":"html","content":"<span class='clj-var'>#&#x27;bounce-worksheet/bumper-location2</span>","value":"#'bounce-worksheet/bumper-location2"},{"type":"html","content":"<span class='clj-unkown'>#object[quil.Applet 0x6b67a6a6 &quot;quil.Applet[panel2,0,0,600x500,layout=java.awt.FlowLayout]&quot;]</span>","value":"#object[quil.Applet 0x6b67a6a6 \"quil.Applet[panel2,0,0,600x500,layout=java.awt.FlowLayout]\"]"}],"value":"[#'bounce-worksheet/bumper-location2,#object[quil.Applet 0x6b67a6a6 \"quil.Applet[panel2,0,0,600x500,layout=java.awt.FlowLayout]\"]]"}
;; <=

;; @@
(def bumper-location-example
  (list (list -3 6) (list 0.8 5) (list 7 4) (list 12 3)))

(show-world-simulation bumper-location-example)
;; @@
;; =>
;;; {"type":"list-like","open":"","close":"","separator":"</pre><pre>","items":[{"type":"html","content":"<span class='clj-var'>#&#x27;bounce-worksheet/bumper-location-example</span>","value":"#'bounce-worksheet/bumper-location-example"},{"type":"html","content":"<span class='clj-unkown'>#object[quil.Applet 0x35cd3363 &quot;quil.Applet[panel6,0,0,600x500,layout=java.awt.FlowLayout]&quot;]</span>","value":"#object[quil.Applet 0x35cd3363 \"quil.Applet[panel6,0,0,600x500,layout=java.awt.FlowLayout]\"]"}],"value":"[#'bounce-worksheet/bumper-location-example,#object[quil.Applet 0x35cd3363 \"quil.Applet[panel6,0,0,600x500,layout=java.awt.FlowLayout]\"]]"}
;; <=

;; @@
(def example-world
  (create-world bumper-location-example))

(def example-world-final-state 
  (simulate-world example-world))

(balls-in-box example-world-final-state)
;; @@
;; =>
;;; {"type":"list-like","open":"","close":"","separator":"</pre><pre>","items":[{"type":"list-like","open":"","close":"","separator":"</pre><pre>","items":[{"type":"html","content":"<span class='clj-var'>#&#x27;bounce-worksheet/example-world</span>","value":"#'bounce-worksheet/example-world"},{"type":"html","content":"<span class='clj-var'>#&#x27;bounce-worksheet/example-world-final-state</span>","value":"#'bounce-worksheet/example-world-final-state"}],"value":"[#'bounce-worksheet/example-world,#'bounce-worksheet/example-world-final-state]"},{"type":"html","content":"<span class='clj-unkown'>0</span>","value":"0"}],"value":"[[#'bounce-worksheet/example-world,#'bounce-worksheet/example-world-final-state],0]"}
;; <=

;; @@
(with-primitive-procedures
  [create-world simulate-world balls-in-box]
  (defquery physics0 []
    (let [n-bumpers 8
          f (fn [] (list 
                     (sample (uniform-continuous -5 14))
                     (sample (uniform-continuous 0 10))))
          bs (repeatedly n-bumpers f)
          w0 (create-world bs)
          w1 (simulate-world w0)
          num-balls (balls-in-box w1)]
      (list num-balls bs))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;bounce-worksheet/physics0</span>","value":"#'bounce-worksheet/physics0"}
;; <=

;; @@
(def lazy-samples0 
  (doquery :importance physics0 []))
(def samples0 
  (map :result (take-nth 10 (take 2000 (drop 1000 lazy-samples0))))) 
(def best-sample0 
  (reduce (fn [acc x] (if (> (first x) (first acc)) x acc)) 
          samples0))
best-sample0
;; @@
;; =>
;;; {"type":"list-like","open":"","close":"","separator":"</pre><pre>","items":[{"type":"list-like","open":"","close":"","separator":"</pre><pre>","items":[{"type":"list-like","open":"","close":"","separator":"</pre><pre>","items":[{"type":"html","content":"<span class='clj-var'>#&#x27;bounce-worksheet/lazy-samples0</span>","value":"#'bounce-worksheet/lazy-samples0"},{"type":"html","content":"<span class='clj-var'>#&#x27;bounce-worksheet/samples0</span>","value":"#'bounce-worksheet/samples0"}],"value":"[#'bounce-worksheet/lazy-samples0,#'bounce-worksheet/samples0]"},{"type":"html","content":"<span class='clj-var'>#&#x27;bounce-worksheet/best-sample0</span>","value":"#'bounce-worksheet/best-sample0"}],"value":"[[#'bounce-worksheet/lazy-samples0,#'bounce-worksheet/samples0],#'bounce-worksheet/best-sample0]"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>1</span>","value":"1"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>-2.872259539861338</span>","value":"-2.872259539861338"},{"type":"html","content":"<span class='clj-double'>7.375506555692743</span>","value":"7.375506555692743"}],"value":"(-2.872259539861338 7.375506555692743)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>-1.8127877780975106</span>","value":"-1.8127877780975106"},{"type":"html","content":"<span class='clj-double'>0.7351898628482134</span>","value":"0.7351898628482134"}],"value":"(-1.8127877780975106 0.7351898628482134)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>-0.619772723127118</span>","value":"-0.619772723127118"},{"type":"html","content":"<span class='clj-double'>7.52750665075056</span>","value":"7.52750665075056"}],"value":"(-0.619772723127118 7.52750665075056)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>8.744790667663144</span>","value":"8.744790667663144"},{"type":"html","content":"<span class='clj-double'>8.272076088039155</span>","value":"8.272076088039155"}],"value":"(8.744790667663144 8.272076088039155)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>8.039148713643517</span>","value":"8.039148713643517"},{"type":"html","content":"<span class='clj-double'>1.3476825428581396</span>","value":"1.3476825428581396"}],"value":"(8.039148713643517 1.3476825428581396)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>5.2323837211909225</span>","value":"5.2323837211909225"},{"type":"html","content":"<span class='clj-double'>1.9746064424247112</span>","value":"1.9746064424247112"}],"value":"(5.2323837211909225 1.9746064424247112)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>2.863653989473061</span>","value":"2.863653989473061"},{"type":"html","content":"<span class='clj-double'>6.3489210858392475</span>","value":"6.3489210858392475"}],"value":"(2.863653989473061 6.3489210858392475)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>12.763078283441669</span>","value":"12.763078283441669"},{"type":"html","content":"<span class='clj-double'>4.64709974968831</span>","value":"4.64709974968831"}],"value":"(12.763078283441669 4.64709974968831)"}],"value":"((-2.872259539861338 7.375506555692743) (-1.8127877780975106 0.7351898628482134) (-0.619772723127118 7.52750665075056) (8.744790667663144 8.272076088039155) (8.039148713643517 1.3476825428581396) (5.2323837211909225 1.9746064424247112) (2.863653989473061 6.3489210858392475) (12.763078283441669 4.64709974968831))"}],"value":"(1 ((-2.872259539861338 7.375506555692743) (-1.8127877780975106 0.7351898628482134) (-0.619772723127118 7.52750665075056) (8.744790667663144 8.272076088039155) (8.039148713643517 1.3476825428581396) (5.2323837211909225 1.9746064424247112) (2.863653989473061 6.3489210858392475) (12.763078283441669 4.64709974968831)))"}],"value":"[[[#'bounce-worksheet/lazy-samples0,#'bounce-worksheet/samples0],#'bounce-worksheet/best-sample0],(1 ((-2.872259539861338 7.375506555692743) (-1.8127877780975106 0.7351898628482134) (-0.619772723127118 7.52750665075056) (8.744790667663144 8.272076088039155) (8.039148713643517 1.3476825428581396) (5.2323837211909225 1.9746064424247112) (2.863653989473061 6.3489210858392475) (12.763078283441669 4.64709974968831)))]"}
;; <=

;; @@
(show-world-simulation (first (rest best-sample0)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>#object[quil.Applet 0x3142c0ac &quot;quil.Applet[panel8,0,0,600x500,layout=java.awt.FlowLayout]&quot;]</span>","value":"#object[quil.Applet 0x3142c0ac \"quil.Applet[panel8,0,0,600x500,layout=java.awt.FlowLayout]\"]"}
;; <=

;; @@
(with-primitive-procedures
  [create-world simulate-world balls-in-box]
  (defquery physics1 []
    (let [n-bumpers 8
          f (fn [] (list 
                     (sample (uniform-continuous -5 14))
                     (sample (uniform-continuous 0 10))))
          bs (repeatedly n-bumpers f)
          w0 (create-world bs)
          w1 (simulate-world w0)
          num-balls (balls-in-box w1)]
      (observe (normal num-balls 1) 20)
      (list num-balls bs))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;bounce-worksheet/physics1</span>","value":"#'bounce-worksheet/physics1"}
;; <=

;; @@
(def lazy-samples1 
  (doquery :lmh physics1 []))
(def samples1 
  (map :result (take-nth 10 (take 2000 (drop 1000 lazy-samples1)))))
(def best-sample1 
  (reduce (fn [acc x] (if (> (first x) (first acc)) x acc))
          samples1))
best-sample1
;; @@
;; =>
;;; {"type":"list-like","open":"","close":"","separator":"</pre><pre>","items":[{"type":"list-like","open":"","close":"","separator":"</pre><pre>","items":[{"type":"list-like","open":"","close":"","separator":"</pre><pre>","items":[{"type":"html","content":"<span class='clj-var'>#&#x27;bounce-worksheet/lazy-samples1</span>","value":"#'bounce-worksheet/lazy-samples1"},{"type":"html","content":"<span class='clj-var'>#&#x27;bounce-worksheet/samples1</span>","value":"#'bounce-worksheet/samples1"}],"value":"[#'bounce-worksheet/lazy-samples1,#'bounce-worksheet/samples1]"},{"type":"html","content":"<span class='clj-var'>#&#x27;bounce-worksheet/best-sample1</span>","value":"#'bounce-worksheet/best-sample1"}],"value":"[[#'bounce-worksheet/lazy-samples1,#'bounce-worksheet/samples1],#'bounce-worksheet/best-sample1]"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>18</span>","value":"18"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>-2.7326274256447585</span>","value":"-2.7326274256447585"},{"type":"html","content":"<span class='clj-double'>1.9734225896970048</span>","value":"1.9734225896970048"}],"value":"(-2.7326274256447585 1.9734225896970048)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>12.686295446692291</span>","value":"12.686295446692291"},{"type":"html","content":"<span class='clj-double'>1.6660592328463064</span>","value":"1.6660592328463064"}],"value":"(12.686295446692291 1.6660592328463064)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>-4.025526688775204</span>","value":"-4.025526688775204"},{"type":"html","content":"<span class='clj-double'>3.960510667506525</span>","value":"3.960510667506525"}],"value":"(-4.025526688775204 3.960510667506525)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>2.5333209414674034</span>","value":"2.5333209414674034"},{"type":"html","content":"<span class='clj-double'>4.360790694204915</span>","value":"4.360790694204915"}],"value":"(2.5333209414674034 4.360790694204915)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>10.740960155293468</span>","value":"10.740960155293468"},{"type":"html","content":"<span class='clj-double'>1.7972292704871595</span>","value":"1.7972292704871595"}],"value":"(10.740960155293468 1.7972292704871595)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>-1.861459312226196</span>","value":"-1.861459312226196"},{"type":"html","content":"<span class='clj-double'>0.18174043017553876</span>","value":"0.18174043017553876"}],"value":"(-1.861459312226196 0.18174043017553876)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>6.402533460007597</span>","value":"6.402533460007597"},{"type":"html","content":"<span class='clj-double'>4.0138466809352895</span>","value":"4.0138466809352895"}],"value":"(6.402533460007597 4.0138466809352895)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>-2.389253439837588</span>","value":"-2.389253439837588"},{"type":"html","content":"<span class='clj-double'>3.9777269747822053</span>","value":"3.9777269747822053"}],"value":"(-2.389253439837588 3.9777269747822053)"}],"value":"((-2.7326274256447585 1.9734225896970048) (12.686295446692291 1.6660592328463064) (-4.025526688775204 3.960510667506525) (2.5333209414674034 4.360790694204915) (10.740960155293468 1.7972292704871595) (-1.861459312226196 0.18174043017553876) (6.402533460007597 4.0138466809352895) (-2.389253439837588 3.9777269747822053))"}],"value":"(18 ((-2.7326274256447585 1.9734225896970048) (12.686295446692291 1.6660592328463064) (-4.025526688775204 3.960510667506525) (2.5333209414674034 4.360790694204915) (10.740960155293468 1.7972292704871595) (-1.861459312226196 0.18174043017553876) (6.402533460007597 4.0138466809352895) (-2.389253439837588 3.9777269747822053)))"}],"value":"[[[#'bounce-worksheet/lazy-samples1,#'bounce-worksheet/samples1],#'bounce-worksheet/best-sample1],(18 ((-2.7326274256447585 1.9734225896970048) (12.686295446692291 1.6660592328463064) (-4.025526688775204 3.960510667506525) (2.5333209414674034 4.360790694204915) (10.740960155293468 1.7972292704871595) (-1.861459312226196 0.18174043017553876) (6.402533460007597 4.0138466809352895) (-2.389253439837588 3.9777269747822053)))]"}
;; <=

;; @@
(show-world-simulation (first (rest best-sample1)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>#object[quil.Applet 0x11c5d2ef &quot;quil.Applet[panel11,0,0,600x500,layout=java.awt.FlowLayout]&quot;]</span>","value":"#object[quil.Applet 0x11c5d2ef \"quil.Applet[panel11,0,0,600x500,layout=java.awt.FlowLayout]\"]"}
;; <=

;; @@
(with-primitive-procedures
  [create-world simulate-world balls-in-box]
  (defquery physics2 []
    (let [n-bumpers (sample (poisson 6))
          f (fn [] (list 
                     (sample (uniform-continuous -5 14))
                     (sample (uniform-continuous 0 10))))
          bs (repeatedly n-bumpers f)
          w0 (create-world bs)
          w1 (simulate-world w0)
          num-balls (balls-in-box w1)]
      (observe (normal n-bumpers 2) 0)
      (observe (normal num-balls 1) 20)
      (list num-balls bs))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;bounce-worksheet/physics2</span>","value":"#'bounce-worksheet/physics2"}
;; <=

;; @@
(def lazy-samples2 
  (doquery :lmh physics2 []))
(def samples2 
  (map :result (take-nth 10 (take 2000 (drop 1000 lazy-samples2)))))
(defn is-better [x y]
  (let [num-bumpers-less (< (count (second x)) (count (second y)))
        num-balls-more (> (first x) (first y))
        num-balls-equal (= (first x) (first y))
        x-above-threshold (> (first x) 15)
        y-above-threshold (> (first x) 15)]
    (or (and x-above-threshold num-bumpers-less)
        (and num-balls-equal num-bumpers-less)
        num-balls-more)))
(def best-sample2 
  (reduce (fn [acc x] (if (is-better x acc) x acc)) 
          samples2))
best-sample2
;; @@
;; =>
;;; {"type":"list-like","open":"","close":"","separator":"</pre><pre>","items":[{"type":"list-like","open":"","close":"","separator":"</pre><pre>","items":[{"type":"list-like","open":"","close":"","separator":"</pre><pre>","items":[{"type":"list-like","open":"","close":"","separator":"</pre><pre>","items":[{"type":"html","content":"<span class='clj-var'>#&#x27;bounce-worksheet/lazy-samples2</span>","value":"#'bounce-worksheet/lazy-samples2"},{"type":"html","content":"<span class='clj-var'>#&#x27;bounce-worksheet/samples2</span>","value":"#'bounce-worksheet/samples2"}],"value":"[#'bounce-worksheet/lazy-samples2,#'bounce-worksheet/samples2]"},{"type":"html","content":"<span class='clj-var'>#&#x27;bounce-worksheet/is-better</span>","value":"#'bounce-worksheet/is-better"}],"value":"[[#'bounce-worksheet/lazy-samples2,#'bounce-worksheet/samples2],#'bounce-worksheet/is-better]"},{"type":"html","content":"<span class='clj-var'>#&#x27;bounce-worksheet/best-sample2</span>","value":"#'bounce-worksheet/best-sample2"}],"value":"[[[#'bounce-worksheet/lazy-samples2,#'bounce-worksheet/samples2],#'bounce-worksheet/is-better],#'bounce-worksheet/best-sample2]"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-unkown'>18</span>","value":"18"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>-2.6619739919490932</span>","value":"-2.6619739919490932"},{"type":"html","content":"<span class='clj-double'>3.5514411362154497</span>","value":"3.5514411362154497"}],"value":"(-2.6619739919490932 3.5514411362154497)"},{"type":"list-like","open":"<span class='clj-list'>(</span>","close":"<span class='clj-list'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-double'>6.771934435730125</span>","value":"6.771934435730125"},{"type":"html","content":"<span class='clj-double'>0.21577687242230414</span>","value":"0.21577687242230414"}],"value":"(6.771934435730125 0.21577687242230414)"}],"value":"((-2.6619739919490932 3.5514411362154497) (6.771934435730125 0.21577687242230414))"}],"value":"(18 ((-2.6619739919490932 3.5514411362154497) (6.771934435730125 0.21577687242230414)))"}],"value":"[[[[#'bounce-worksheet/lazy-samples2,#'bounce-worksheet/samples2],#'bounce-worksheet/is-better],#'bounce-worksheet/best-sample2],(18 ((-2.6619739919490932 3.5514411362154497) (6.771934435730125 0.21577687242230414)))]"}
;; <=

;; @@
(show-world-simulation (first (rest best-sample2)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>#object[quil.Applet 0xbce9dd4 &quot;quil.Applet[panel14,0,0,600x500,layout=java.awt.FlowLayout]&quot;]</span>","value":"#object[quil.Applet 0xbce9dd4 \"quil.Applet[panel14,0,0,600x500,layout=java.awt.FlowLayout]\"]"}
;; <=

;; @@

;; @@
