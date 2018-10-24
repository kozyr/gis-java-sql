set -e

echo "deleting old deployment demo-gis"
kubectl delete deployments/demo-gis || true
kubectl apply -f deployment.yaml