(ns wiki-graph.core
  (:require [ring.adapter.jetty :as jetty]))

(defn handler [request]
  {:status 200,
   :headers {"Content-Type" "text/html"}
   :body "<h1>Hello, world!!!!!</h1>"})

(defonce server (jetty/run-jetty handler {:host "localhost"
                                          :port 3000
                                          :join? false}))

(defn -main []
  (.start server))
