#! /bin/bash

# IMPORTANT: give user permission to run docker commands without sudo

# start minikube and enable the ingress controller
minikube start
minikube addons enable ingress

# To point your shell to minikube's docker-daemon, run:
eval $(minikube -p minikube docker-env)

# build images
docker build . -t bitcoin-price -f bitcoin.Dockerfile
docker build . -t ynet-news -f ynet.Dockerfile

# apply deployment
minikube kubectl -- apply -f deployment.yaml