# K8sIngress

This repo contains two apps:

- BitcoinPrice
- Ynet-News

The first app shows the current price of bitcoin and the average price for the last 10 minutes.

The second app displays the latest news from Ynet.

The objective is to deploy the apps in a Kubernetes cluster.

## Kubernetes deployment

To deploy both apps on minikube, run the following commands:

```sh
# start minikube and enable the ingress controller
minikube start
minikube addons enable ingress

# To point your shell to minikube's docker-daemon
eval $(minikube -p minikube docker-env)

# build images
docker build . -t bitcoin-price -f bitcoin.Dockerfile
docker build . -t ynet-news -f ynet.Dockerfile

# apply deployment
kubectl apply -f deployment.yaml

minikube tunnel
```

Now, you can access the apps by pointing your browser to the following URLs:

- http://localhost/bitcoin-price
- http://localhost/ynet-news

To stop the deployment run the following command:

```sh
# to delete the deployment
kubectl delete -f deployment.yaml

# alternatively, delete minikube cluster
minikube delete
```
