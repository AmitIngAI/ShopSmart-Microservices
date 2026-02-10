import axios from 'axios';

const api = axios.create({
  baseURL: '/api',
  headers: { 'Content-Type': 'application/json' }
});

// Interceptor to add token
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Product API
export const getProducts = () => api.get('/products');
export const getProduct = (id) => api.get(`/products/${id}`);
export const createProduct = (product) => api.post('/products', product);

// Order API
export const createOrder = (orderData) => api.post('/orders', orderData);
export const getOrdersByUser = (userId) => api.get(`/orders/user/${userId}`);

// Payment API
export const createPaymentIntent = (orderId, amount) => 
  api.post(`/payments/create-payment-intent?orderId=${orderId}&amount=${amount}`);
export const confirmPayment = (orderId) => 
  api.post(`/payments/confirm/${orderId}`);

export default api;