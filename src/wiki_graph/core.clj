(ns wiki-graph.core
  (:require [ring.adapter.jetty :as jetty]
            [clj-http.client :as client]))

(def wiki "http://en.wikipedia.org/w/api.php")

(defn test []
  (client/get "http://en.wikipedia.org/w/api.php"
              {:query-params {:action "query" :list "search" :srsearch "Craig" :format "json"}
               :debug true}))

;; (client/request)

(defn handler [request]
  {:status 200,
   :headers {"Content-Type" "text/html"}
   :body "<h1>Hello, world!!!!!</h1>"})

(defonce server (jetty/run-jetty handler {:host "localhost"
                                          :port 3000
                                          :join? false}))

(defn -main []
  (test)
                                        ;(.start server)
  )

