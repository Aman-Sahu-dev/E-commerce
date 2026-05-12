import React from 'react';
import { motion } from 'framer-motion';
import { 
  TrendingUp, 
  Users, 
  ShoppingBag, 
  DollarSign, 
  ArrowUpRight, 
  ArrowDownRight,
  Clock,
  MoreVertical
} from 'lucide-react';

const Dashboard = () => {
  const stats = [
    { title: 'Total Revenue', value: '$45,231.89', change: '+20.1%', positive: true, icon: DollarSign },
    { title: 'Sales', value: '+2350', change: '+180.1%', positive: true, icon: ShoppingBag },
    { title: 'Active Users', value: '+12,234', change: '+19%', positive: true, icon: Users },
    { title: 'Growth', value: '+12.5%', change: '-4.1%', positive: false, icon: TrendingUp },
  ];

  const recentOrders = [
    { id: '#ORD-7829', customer: 'Alice Johnson', status: 'Delivered', amount: '$120.00', date: '2 mins ago' },
    { id: '#ORD-7830', customer: 'Bob Smith', status: 'Pending', amount: '$85.50', date: '15 mins ago' },
    { id: '#ORD-7831', customer: 'Charlie Brown', status: 'Cancelled', amount: '$42.00', date: '1 hour ago' },
    { id: '#ORD-7832', customer: 'Diana Prince', status: 'Processing', amount: '$210.00', date: '3 hours ago' },
  ];

  return (
    <div className="space-y-8 animate-fade-in">
      <div className="flex flex-col md:flex-row md:items-center justify-between gap-4">
        <div>
          <h1 className="text-2xl font-bold text-slate-900">Dashboard</h1>
          <p className="text-slate-500">Welcome back, here's what's happening today.</p>
        </div>
        <div className="flex items-center gap-3">
          <button className="btn btn-secondary bg-white">Download Report</button>
          <button className="btn btn-primary">Create New Order</button>
        </div>
      </div>

      {/* Stats Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        {stats.map((stat, i) => (
          <motion.div
            key={stat.title}
            initial={{ opacity: 0, y: 20 }}
            animate={{ opacity: 1, y: 0 }}
            transition={{ delay: i * 0.1 }}
            className="card p-6"
          >
            <div className="flex items-center justify-between mb-4">
              <div className="w-12 h-12 bg-primary-50 text-primary-600 rounded-xl flex items-center justify-center">
                <stat.icon size={24} />
              </div>
              <div className={`flex items-center gap-1 text-sm font-bold ${stat.positive ? 'text-green-600' : 'text-red-600'}`}>
                {stat.change}
                {stat.positive ? <ArrowUpRight size={16} /> : <ArrowDownRight size={16} />}
              </div>
            </div>
            <h3 className="text-slate-500 text-sm font-medium">{stat.title}</h3>
            <p className="text-2xl font-bold text-slate-900 mt-1">{stat.value}</p>
          </motion.div>
        ))}
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
        {/* Recent Orders */}
        <div className="lg:col-span-2 space-y-4">
          <div className="flex items-center justify-between">
            <h2 className="text-lg font-bold text-slate-900">Recent Orders</h2>
            <button className="text-sm font-semibold text-primary-600 hover:text-primary-700">View All</button>
          </div>
          <div className="card overflow-hidden">
            <div className="overflow-x-auto">
              <table className="w-full text-left border-collapse">
                <thead>
                  <tr className="bg-slate-50 border-b border-slate-100">
                    <th className="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider">Order ID</th>
                    <th className="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider">Customer</th>
                    <th className="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider">Status</th>
                    <th className="px-6 py-4 text-xs font-bold text-slate-500 uppercase tracking-wider text-right">Amount</th>
                  </tr>
                </thead>
                <tbody className="divide-y divide-slate-100">
                  {recentOrders.map((order) => (
                    <tr key={order.id} className="hover:bg-slate-50 transition-colors">
                      <td className="px-6 py-4 font-medium text-slate-900">{order.id}</td>
                      <td className="px-6 py-4 text-slate-600">{order.customer}</td>
                      <td className="px-6 py-4">
                        <span className={`
                          px-3 py-1 rounded-full text-xs font-bold
                          ${order.status === 'Delivered' ? 'bg-green-100 text-green-700' : ''}
                          ${order.status === 'Pending' ? 'bg-yellow-100 text-yellow-700' : ''}
                          ${order.status === 'Processing' ? 'bg-blue-100 text-blue-700' : ''}
                          ${order.status === 'Cancelled' ? 'bg-red-100 text-red-700' : ''}
                        `}>
                          {order.status}
                        </span>
                      </td>
                      <td className="px-6 py-4 text-right font-bold text-slate-900">{order.amount}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>

        {/* Activity Feed */}
        <div className="space-y-4">
          <h2 className="text-lg font-bold text-slate-900">Recent Activity</h2>
          <div className="card p-6">
            <div className="space-y-6">
              {[1, 2, 3, 4].map((item) => (
                <div key={item} className="flex gap-4">
                  <div className="relative">
                    <div className="w-10 h-10 bg-slate-100 rounded-full flex items-center justify-center text-slate-600">
                      <Clock size={18} />
                    </div>
                    {item !== 4 && <div className="absolute top-10 bottom-[-24px] left-5 w-px bg-slate-100"></div>}
                  </div>
                  <div>
                    <p className="text-sm font-bold text-slate-900">New Product Added</p>
                    <p className="text-xs text-slate-500 mt-0.5">Added "Wireless Headphones" to Electronics</p>
                    <p className="text-[10px] font-bold text-primary-600 mt-2 uppercase tracking-tight">12:30 PM</p>
                  </div>
                </div>
              ))}
            </div>
            <button className="w-full mt-8 py-2 text-sm font-bold text-slate-400 hover:text-slate-600 transition-colors">
              View full timeline
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
