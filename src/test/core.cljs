(ns test.core
  (:require
   #_[om.core :as om :include-macros true]
   [sablono.core :as sab :include-macros true])
  (:require-macros
   [devcards.core :as dc :refer [defcard deftest]]))

(enable-console-print!)

(defn button-inc [state]
  [:button {:on-click (fn [] (swap! state update :counter inc))}"Increment"])

(defn button-dec [state]
  [:button {:on-click (fn [] (swap! state update :counter dec))} "Decrement"])

(defn printer [state]
  [:div {:class "counter" 
         } (get @state :counter)])

(defn page [state]
  [:div
   (printer state)
   [:span
    (button-inc state)
    (button-dec state)
    ]
   ]
  )

(defcard
  first-card
  (fn [state]
    (sab/html (page state)))
  {}
  {:history true
   :inspect-data true})

(defn main []
  ;; conditionally start the app based on whether the #main-app-area
  ;; node is on the page
  (if-let [node (.getElementById js/document "main-app-area")]
    (js/React.render (sab/html [:div "This is working"]) node)))

(main)

;; remember to run lein figwheel and then browse to
;; http://localhost:3449/cards.html

