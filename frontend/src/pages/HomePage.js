import React from 'react';
import { Link } from 'react-router-dom';

const HomePage = () => {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100">
      <div className="container mx-auto px-4 py-16">
        <div className="text-center">
          <h1 className="text-6xl font-bold text-gray-800 mb-6">
            Welcome to <span className="text-blue-600">ShopSmart</span>
          </h1>
          <p className="text-xl text-gray-600 mb-8 max-w-2xl mx-auto">
            Experience the future of online shopping with our cutting-edge microservices platform. 
            Built with Spring Boot, React, and Docker.
          </p>
          <div className="flex justify-center space-x-4">
            <Link 
              to="/products" 
              className="bg-blue-600 text-white px-8 py-3 rounded-lg text-lg font-semibold hover:bg-blue-700 transition shadow-lg"
            >
              Start Shopping
            </Link>
            <Link 
              to="/register" 
              className="bg-green-600 text-white px-8 py-3 rounded-lg text-lg font-semibold hover:bg-green-700 transition shadow-lg"
            >
              Sign Up Free
            </Link>
          </div>
        </div>

        <div className="mt-20 grid grid-cols-1 md:grid-cols-3 gap-8">
          <div className="bg-white p-6 rounded-lg shadow-md text-center">
            <div className="text-4xl mb-4">🚀</div>
            <h3 className="text-xl font-bold mb-2">Microservices Architecture</h3>
            <p className="text-gray-600">Built with Spring Boot microservices for scalability and reliability</p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow-md text-center">
            <div className="text-4xl mb-4">🔒</div>
            <h3 className="text-xl font-bold mb-2">Secure Payments</h3>
            <p className="text-gray-600">Integrated with Stripe for safe and secure transactions</p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow-md text-center">
            <div className="text-4xl mb-4">⚡</div>
            <h3 className="text-xl font-bold mb-2">Real-time Updates</h3>
            <p className="text-gray-600">Event-driven architecture with RabbitMQ messaging</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default HomePage;