import React from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { useCart } from '../context/CartContext';

const Navbar = () => {
  const { user, logout } = useAuth();
  const { cart } = useCart();
  const cartItemCount = cart.reduce((c, i) => c + i.quantity, 0);

  return (
    <nav className="bg-blue-600 text-white shadow-lg">
      <div className="container mx-auto px-4 py-3 flex justify-between items-center">
        <Link to="/" className="text-2xl font-bold">🛒 ShopSmart</Link>
        <div className="flex items-center space-x-6">
          <Link to="/" className="hover:text-blue-200">Home</Link>
          <Link to="/products" className="hover:text-blue-200">Products</Link>
          {user && (
            <Link to="/orders" className="hover:text-blue-200">My Orders</Link>
          )}
          <Link to="/cart" className="relative hover:text-blue-200">
            Cart
            {cartItemCount > 0 && (
              <span className="absolute -top-2 -right-3 bg-red-500 text-xs rounded-full px-2 py-0.5">
                {cartItemCount}
              </span>
            )}
          </Link>
          {user ? (
            <div className="flex items-center space-x-3">
              <span className="text-sm">Hi, {user.name}</span>
              <button onClick={logout} className="bg-red-500 px-4 py-1 rounded hover:bg-red-600">
                Logout
              </button>
            </div>
          ) : (
            <Link to="/login" className="bg-green-500 px-4 py-1 rounded hover:bg-green-600">
              Login
            </Link>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Navbar;