import React, { useEffect, useState } from 'react';
import { useAuth } from '../context/AuthContext';
import { getOrdersByUser } from '../services/api';

const OrdersPage = () => {
  const { user } = useAuth();
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (user) {
      loadOrders();
    }
  }, [user]);

  const loadOrders = async () => {
    try {
      const res = await getOrdersByUser(user.id);
      setOrders(res.data);
      setLoading(false);
    } catch (err) {
      console.error('Failed to load orders', err);
      setLoading(false);
    }
  };

  if (!user) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <div className="text-2xl text-gray-600">Please login to view orders</div>
      </div>
    );
  }

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <div className="text-2xl text-gray-600">Loading orders...</div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50 py-8">
      <div className="container mx-auto px-4">
        <h2 className="text-4xl font-bold text-gray-800 mb-8">My Orders</h2>
        
        {orders.length === 0 ? (
          <div className="text-center py-12">
            <div className="text-6xl mb-4">📦</div>
            <p className="text-xl text-gray-600">No orders yet</p>
          </div>
        ) : (
          <div className="space-y-6">
            {orders.map(order => (
              <div key={order.id} className="bg-white rounded-lg shadow-md p-6">
                <div className="flex justify-between items-start mb-4">
                  <div>
                    <h3 className="text-xl font-bold text-gray-800">Order #{order.id}</h3>
                    <p className="text-gray-600">
                      {new Date(order.orderDate).toLocaleDateString('en-US', {
                        year: 'numeric',
                        month: 'long',
                        day: 'numeric',
                        hour: '2-digit',
                        minute: '2-digit'
                      })}
                    </p>
                  </div>
                  <div className="text-right">
                    <span className={`px-4 py-2 rounded-full text-sm font-semibold ${
                      order.status === 'PAID' ? 'bg-green-100 text-green-800' :
                      order.status === 'PENDING' ? 'bg-yellow-100 text-yellow-800' :
                      order.status === 'DELIVERED' ? 'bg-blue-100 text-blue-800' :
                      'bg-gray-100 text-gray-800'
                    }`}>
                      {order.status}
                    </span>
                  </div>
                </div>
                
                <div className="border-t pt-4">
                  <h4 className="font-semibold mb-3">Items:</h4>
                  <ul className="space-y-2">
                    {order.items.map((item, idx) => (
                      <li key={idx} className="flex justify-between text-gray-700">
                        <span>{item.productName} (x{item.quantity})</span>
                        <span className="font-medium">${(item.price * item.quantity).toFixed(2)}</span>
                      </li>
                    ))}
                  </ul>
                </div>
                
                <div className="border-t mt-4 pt-4 flex justify-between items-center">
                  <span className="text-gray-600">Shipping Address:</span>
                  <span className="text-gray-800">{order.shippingAddress}</span>
                </div>
                
                <div className="border-t mt-4 pt-4 flex justify-between items-center">
                  <span className="text-xl font-bold">Total:</span>
                  <span className="text-2xl font-bold text-blue-600">${order.totalAmount.toFixed(2)}</span>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default OrdersPage;