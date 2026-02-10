import React, { useEffect, useState } from 'react';
import { getProducts } from '../services/api';
import { useCart } from '../context/CartContext';

const ProductsPage = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const { addToCart } = useCart();

  useEffect(() => {
    loadProducts();
  }, []);

  const loadProducts = async () => {
    try {
      const res = await getProducts();
      setProducts(res.data);
      setLoading(false);
    } catch (err) {
      setError('Failed to load products');
      setLoading(false);
    }
  };

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <div className="text-2xl text-gray-600">Loading products...</div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <div className="text-2xl text-red-600">{error}</div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50 py-8">
      <div className="container mx-auto px-4">
        <h2 className="text-4xl font-bold text-gray-800 mb-8">Our Products</h2>
        
        {products.length === 0 ? (
          <div className="text-center py-12">
            <p className="text-xl text-gray-600">No products available yet.</p>
            <p className="text-gray-500 mt-2">Check back soon!</p>
          </div>
        ) : (
          <div className="grid grid-cols-1 md:grid-cols-3 lg:grid-cols-4 gap-6">
            {products.map(product => (
              <div key={product.id} className="bg-white rounded-lg shadow-md overflow-hidden hover:shadow-xl transition">
                <img 
                  src={product.imageUrl || 'https://via.placeholder.com/300'} 
                  alt={product.name}
                  className="w-full h-48 object-cover"
                />
                <div className="p-4">
                  <h3 className="text-lg font-semibold text-gray-800 mb-2">{product.name}</h3>
                  <p className="text-gray-600 text-sm mb-3 line-clamp-2">{product.description}</p>
                  <div className="flex justify-between items-center mb-3">
                    <span className="text-2xl font-bold text-blue-600">${product.price.toFixed(2)}</span>
                    <span className={`text-sm ${product.stock > 0 ? 'text-green-600' : 'text-red-600'}`}>
                      {product.stock > 0 ? `${product.stock} in stock` : 'Out of stock'}
                    </span>
                  </div>
                  <button 
                    onClick={() => addToCart(product)}
                    disabled={product.stock === 0}
                    className={`w-full py-2 rounded-lg font-semibold transition ${
                      product.stock === 0 
                        ? 'bg-gray-300 text-gray-500 cursor-not-allowed' 
                        : 'bg-blue-600 text-white hover:bg-blue-700'
                    }`}
                  >
                    {product.stock === 0 ? 'Out of Stock' : 'Add to Cart'}
                  </button>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default ProductsPage;