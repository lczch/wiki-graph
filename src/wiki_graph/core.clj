(ns wiki-graph.core
  (:require [ring.adapter.jetty :as jetty]
            [clj-http.client :as client]
            [selmer.parser :as sel]
            [clojure.string :as str]
            [ring.middleware.file :as static]))

(def wiki "http://en.wikipedia.org/w/api.php")

(defn my-test []
  (println 
   (client/get "http://en.wikipedia.org/w/api.php"
               {:query-params {:action "query"
                               :list "search"
                               :srsearch "Dwarf_Fortress"
                               :format "json"}})))

;; (client/request)

(defn handler [request]
  {:status 200,
   :headers {"Content-Type" "text/html"}
   :body "<h1>Hello, world!!!!!</h1>"})



;; (defn -main []
;;   (.start server))

;; (defn -main []
;;   (test)
;;   )

;; render html
(sel/cache-off!)
(sel/set-resource-path! (clojure.java.io/resource "templates"))

(defn cons-server []
  (defonce server (jetty/run-jetty (app-handler handler))))

(defn app-handler [handler]
  (static/wrap-file handler (clojure.java.io/resource "templates")))

(def HTML (sel/render-file "index.htm" {}))

(defn fetch-html [html]
  (let [soup (Jsoup/parse html)
        headers (.head soup)
        body (.body soup)]
    {:status 200 :headers headers :body body}))

(defn -main []
  (cons-server)
  (.start server)
  )


