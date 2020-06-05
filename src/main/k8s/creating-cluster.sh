#!/bin/bash

echo "================================================================================================================== Restarting service docker..."
sudo systemctl restart docker
sleep 10

echo "================================================================================================================== Creating Cluster..."
kind create cluster  --config /home/trl/IdeaProjects/Programming-Training-And-Practice/kubernetes-main-information/kind-config.yaml

echo "================================================================================================================== Get Clusters"
kind get clusters
