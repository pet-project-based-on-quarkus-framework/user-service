#!/bin/bash

echo "================================================================================================================== Get namespaces"
kubectl get namespace
echo "================================================================================================================== Creating namespace 'test'..."
kubectl create -f /home/trl/IdeaProjects/Programming-Training-And-Practice/quarkus-user-service/src/main/k8s/test/namespace.yaml
echo "================================================================================================================== Get namespaces"
kubectl get namespace

echo "================================================================================================================== Get contexts"
kubectl config get-contexts
echo "================================================================================================================== Set contexts..."
kubectl config set-context test --namespace=test --user=kind-kind --cluster=kind-kind
echo "================================================================================================================== Use context"
kubectl config use-context test
echo "================================================================================================================== Get contexts"
kubectl config get-contexts

echo "================================================================================================================== Creating postgres configmap..."
kubectl create -f /home/trl/IdeaProjects/Programming-Training-And-Practice/quarkus-user-service/src/main/k8s/test/postgres-configmap.yaml

echo "================================================================================================================== Creating postgres pv volume..."
kubectl create -f /home/trl/IdeaProjects/Programming-Training-And-Practice/quarkus-user-service/src/main/k8s/test/postgres-pv-volum.yaml

echo "================================================================================================================== Creating postgres pv claim..."
kubectl create -f /home/trl/IdeaProjects/Programming-Training-And-Practice/quarkus-user-service/src/main/k8s/test/postgres-pv-claim.yaml

echo "================================================================================================================== Creating postgres deployment..."
kubectl create -f /home/trl/IdeaProjects/Programming-Training-And-Practice/quarkus-user-service/src/main/k8s/test/postgres-deployment.yaml

echo "================================================================================================================== Creating postgres service..."
kubectl create -f /home/trl/IdeaProjects/Programming-Training-And-Practice/quarkus-user-service/src/main/k8s/test/postgres-service.yaml

echo "================================================================================================================== Waiting for termination all tasks..."
sleep 60

echo "================================================================================================================== Get services"
kubectl get services -o wide

echo "================================================================================================================== Get pods"
kubectl get pods -o wide

echo "================================================================================================================== Get nodes"
kubectl get nodes -o wide
