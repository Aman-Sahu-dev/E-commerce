import React, { useState, useEffect } from 'react';
import api from '../services/api';
import { 
  Plus, 
  Search, 
  Filter, 
  MoreVertical, 
  Edit, 
  Trash2, 
  ExternalLink,
  Package
} from 'lucide-react';
import { motion, AnimatePresence } from 'framer-motion';

const Products = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const response = await api.get('/web/getproducts');
      setProducts(response.data);
    } catch (err) {
      console.error('Error fetching products:', err);
    } finally {
      setLoading(false);
    }
  };

  const filteredProducts = products.filter(p => 
    p.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    p.description.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="space-y-6">
      <div className="flex flex-col sm:flex-row sm:items-center justify-between gap-4">
        <div>
          <h1 className="text-2xl font-bold text-slate-900">Inventory</h1>
          <p className="text-slate-500">Manage your product catalog and stock.</p>
        </div>
        <button className="btn btn-primary gap-2">
          <Plus size={20} />
          Add Product
        </button>
      </div>

      <div className="flex flex-col md:flex-row gap-4 items-center justify-between">
        <div className="relative w-full md:w-96">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 text-slate-400" size={18} />
          <input
            type="text"
            placeholder="Search products..."
            className="input pl-10"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </div>
        <div className="flex items-center gap-2 w-full md:w-auto">
          <button className="btn btn-secondary bg-white flex-1 md:flex-none gap-2">
            <Filter size={18} />
            Filters
          </button>
          <div className="flex items-center border border-slate-200 rounded-lg overflow-hidden bg-white">
            <button className="p-2 bg-slate-50 text-slate-900 border-r border-slate-200"><Package size={18} /></button>
            <button className="p-2 text-slate-400 hover:bg-slate-50"><MoreVertical size={18} /></button>
          </div>
        </div>
      </div>

      {loading ? (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
          {[1, 2, 3, 4, 5, 6, 7, 8].map(i => (
            <div key={i} className="card h-80 animate-pulse bg-slate-100"></div>
          ))}
        </div>
      ) : filteredProducts.length > 0 ? (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
          <AnimatePresence>
            {filteredProducts.map((product, i) => (
              <motion.div
                key={product.productId}
                initial={{ opacity: 0, scale: 0.95 }}
                animate={{ opacity: 1, scale: 1 }}
                transition={{ delay: i * 0.05 }}
                className="card group hover:shadow-xl transition-all duration-300"
              >
                <div className="h-48 bg-slate-100 relative overflow-hidden">
                  <div className="absolute inset-0 flex items-center justify-center text-slate-300">
                    <Package size={64} />
                  </div>
                  <div className="absolute top-3 left-3 flex gap-2">
                    {product.categories?.map(cat => (
                      <span key={cat.categoryId} className="px-2 py-1 bg-white/90 backdrop-blur text-[10px] font-bold uppercase tracking-wider rounded-md shadow-sm">
                        {cat.name}
                      </span>
                    ))}
                  </div>
                  <div className="absolute inset-0 bg-slate-900/40 opacity-0 group-hover:opacity-100 transition-opacity flex items-center justify-center gap-3">
                    <button className="p-2 bg-white rounded-full text-slate-900 hover:scale-110 transition-transform shadow-lg"><Edit size={18} /></button>
                    <button className="p-2 bg-red-500 rounded-full text-white hover:scale-110 transition-transform shadow-lg"><Trash2 size={18} /></button>
                  </div>
                </div>
                <div className="p-5">
                  <div className="flex items-start justify-between mb-2">
                    <h3 className="font-bold text-slate-900 group-hover:text-primary-600 transition-colors line-clamp-1">{product.name}</h3>
                    <span className="font-bold text-slate-900">${product.price}</span>
                  </div>
                  <p className="text-sm text-slate-500 line-clamp-2 mb-4 h-10">{product.description}</p>
                  <div className="flex items-center justify-between pt-4 border-t border-slate-100">
                    <span className={`text-xs font-bold ${product.quantity > 10 ? 'text-green-600' : 'text-orange-600'}`}>
                      {product.quantity} in stock
                    </span>
                    <button className="text-slate-400 hover:text-primary-600 transition-colors">
                      <ExternalLink size={18} />
                    </button>
                  </div>
                </div>
              </motion.div>
            ))}
          </AnimatePresence>
        </div>
      ) : (
        <div className="card p-12 text-center flex flex-col items-center justify-center text-slate-400">
          <div className="w-20 h-20 bg-slate-50 rounded-full flex items-center justify-center mb-4">
            <Package size={40} />
          </div>
          <h2 className="text-xl font-bold text-slate-900 mb-1">No products found</h2>
          <p>Try adjusting your search or add a new product.</p>
          <button className="mt-6 btn btn-primary">Add Your First Product</button>
        </div>
      )}
    </div>
  );
};

export default Products;
