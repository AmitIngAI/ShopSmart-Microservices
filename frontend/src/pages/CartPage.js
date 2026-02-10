import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useCart } from '../context/CartContext';
import { useAuth } from '../context/AuthContext';
import { createOrder } from '../services/api';

const CartPage = () => {
  const { cart, updateQuantity, removeFromCart, clearCart, total } = useCart();
  const { user } = useAuth();
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);

  const handleCheckout = async () => {
    if (!user) {
      alert('Please login to checkout');
      navigate('/login');
      return;
    }

    const orderData = {
      userId: user.id,
      items: cart.map(item => ({
        productId: item.productId,
        productName: item.productName,
        quantity: item.quantity,
        price: item.price
      })),
      shippingAddress: '123 Main St, City, Country' // In real app, collect from form
    };

    try {
      setLoading(true);
      const res = await createOrder(orderData);
      alert(`Order placed successfully! Order ID: ${res.data.id}`);
      clearCart();
      navigate('/orders');
    } catch (err) {
      alert('Failed to place order: ' + (err.response?.data || err.message));
    } finally {
      setLoading(false);
    }
  };

  if (cart.length === 0) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center">
          <div className="text-6xl mb-4">🛒</div>
          <h2 className="text-3xl font-bold text-gray-800 mb-4">Your cart is empty</h2>
          <p className="text-gray-600 mb-6">Add some products to get started!</p>
          <button 
            onClick={() => navigate('/products')}
            className="bg-blue-600 text-white px-6 py-3 rounded-lg hover:bg-blue-700"
          >
            Continue Shopping
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50 py-8">
      <div className="container mx-auto px-4">
        <h2 className="text-4xl font-bold text-gray-800 mb-8">Shopping Cart</h2>
        
        <div className="bg-white rounded-lg shadow-md overflow-hidden">
          <table className="min-w-full">
            <thead className="bg-gray-100">
              <tr>
                <th className="px-6 py-3 text-left text-sm font-semibold text-gray-700">Product</th>
                <th className="px-6 py-3 text-left text-sm font-semibold text-gray-700">Price</th>
                <th className="px-6 py-3 text-left text-sm font-semibold text-gray-700">Quantity</th>
                <th className="px-6 py-3 text-left text-sm font-semibold text-gray-700">Total</th>
                <th className="px-6 py-3 text-left text-sm font-semibold text-gray-700">Actions</th>
              </tr>
            </thead>
            <tbody>
              {cart.map(item => (
                <tr key={item.productId} className="border-b hover:bg-gray-50">
                  <td className="px-6 py-4">
                    <div className="flex items-center">
                      <img 
                        src={item.imageUrl || 'https://via.placeholder.com/50'} 
                        alt={item.productName}
                        className="w-12 h-12 rounded object-cover mr-4"
                      />
                      <span className="font-medium">{item.productName}</span>
                    </div>
                  </td>
                  <td className="px-6 py-4 text-gray-700">${item.price.toFixed(2)}</td>
                  <td className="px-6 py-4">
                    <input 
                      type="number" 
                      min="1" 
                      value={item.quantity}
                      onChange={(e) => updateQuantity(item.productId, parseInt(e.target.value))}
                      className="w-20 px-2 py-1 border rounded text-center"
                    />
                  </td>
                  <td className="px-6 py-4 font-semibold text-gray-800">
                    ${(item.price * item.quantity).toFixed(2)}
                  </td>
                  <td className="px-6 py-4">
                    <button 
                      onClick={() => removeFromCart(item.productId)}
                      className="text-red-600 hover:text-red-800 font-medium"
                    >
                      Remove
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        <div className="mt-6 flex justify-end">
          <div className="bg-white rounded-lg shadow-md p-6 w-80">
            <div className="flex justify-between mb-4">
              <span className="text-lg font-semibold">Subtotal:</span>
              <span className="text-lg">${total.toFixed(2)}</span>
            </div>
            <div className="flex justify-between mb-4">
              <span className="text-lg font-semibold">Shipping:</span>
              <span className="text-lg">Free</span>
            </div>
            <div className="border-t pt-4 mb-6">
              <div className="flex justify-between">
                <span className="text-2xl font-bold">Total:</span>
                <span className="text-2xl font-bold text-blue-600">${total.toFixed(2)}</span>
              </div>
            </div>
            <button 
              onClick={handleCheckout}
              disabled={loading}
              className={`w-full py-3 rounded-lg font-semibold text-white transition ${
                loading ? 'bg-gray-400' : 'bg-green-600 hover:bg-green-700'
              }`}
            >
              {loading ? 'Processing...' : 'Proceed to Checkout'}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CartPage;