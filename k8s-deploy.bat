@echo off
echo ============================================
echo  ShopSmart Kubernetes Deployment
echo ============================================

echo [1/5] Creating namespace...
kubectl apply -f k8s/base/namespace.yml

echo [2/5] Creating configs and secrets...
kubectl apply -f k8s/base/configmap.yml
kubectl apply -f k8s/base/secrets.yml

echo [3/5] Deploying databases...
kubectl apply -f k8s/base/mysql.yml
kubectl apply -f k8s/base/mongodb.yml
kubectl apply -f k8s/base/rabbitmq.yml

echo Waiting for databases to be ready...
timeout /t 60 /nobreak

echo [4/5] Deploying microservices...
kubectl apply -f k8s/base/eureka.yml
timeout /t 30 /nobreak
kubectl apply -f k8s/base/api-gateway.yml
kubectl apply -f k8s/base/user-service.yml
kubectl apply -f k8s/base/product-service.yml
kubectl apply -f k8s/base/order-service.yml
kubectl apply -f k8s/base/payment-service.yml
kubectl apply -f k8s/base/frontend.yml

echo [5/5] Creating ingress...
kubectl apply -f k8s/base/ingress.yml

echo.
echo ============================================
echo  Deployment Complete!
echo ============================================
echo.
echo Check status: kubectl get pods -n shopsmart
echo Check services: kubectl get svc -n shopsmart
echo.
pause