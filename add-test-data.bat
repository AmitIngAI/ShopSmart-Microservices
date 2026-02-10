@echo off
echo Adding test data to ShopSmart...
timeout /t 5 /nobreak

echo [1/2] Adding test products...
curl -X POST http://localhost:8080/api/products ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"Laptop\",\"description\":\"High-performance laptop\",\"price\":999.99,\"category\":\"Electronics\",\"imageUrl\":\"https://via.placeholder.com/300\",\"stock\":10}"

curl -X POST http://localhost:8080/api/products ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"Smartphone\",\"description\":\"Latest smartphone\",\"price\":699.99,\"category\":\"Electronics\",\"imageUrl\":\"https://via.placeholder.com/300\",\"stock\":25}"

curl -X POST http://localhost:8080/api/products ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"Headphones\",\"description\":\"Noise-cancelling headphones\",\"price\":199.99,\"category\":\"Audio\",\"imageUrl\":\"https://via.placeholder.com/300\",\"stock\":50}"

echo.
echo [2/2] Test data added successfully!
echo Open http://localhost:3000/products to see products
pause