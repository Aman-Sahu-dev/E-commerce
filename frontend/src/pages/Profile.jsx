import React, { useState } from 'react';
import { 
  User, 
  Mail, 
  Phone, 
  Shield, 
  Bell, 
  Camera,
  Moon,
  Sun,
  ChevronRight
} from 'lucide-react';
import { motion } from 'framer-motion';

const Profile = () => {
  const [isDarkMode, setIsDarkMode] = useState(false);

  const sections = [
    { title: 'Personal Information', description: 'Update your name, email and phone number.', icon: User },
    { title: 'Security', description: 'Change your password and manage 2FA settings.', icon: Shield },
    { title: 'Notifications', description: 'Configure how you receive alerts.', icon: Bell },
  ];

  return (
    <div className="max-w-4xl mx-auto space-y-8 animate-fade-in">
      <div>
        <h1 className="text-2xl font-bold text-slate-900">Profile Settings</h1>
        <p className="text-slate-500">Manage your account preferences and settings.</p>
      </div>

      <div className="card">
        <div className="p-8 border-b border-slate-100 flex flex-col md:flex-row items-center gap-8">
          <div className="relative">
            <div className="w-32 h-32 rounded-3xl bg-gradient-to-tr from-primary-600 to-blue-400 flex items-center justify-center text-white text-4xl font-bold shadow-xl shadow-primary-500/20">
              JD
            </div>
            <button className="absolute -bottom-2 -right-2 p-2 bg-white border border-slate-200 rounded-xl text-slate-600 hover:text-primary-600 shadow-lg transition-all">
              <Camera size={18} />
            </button>
          </div>
          <div className="text-center md:text-left flex-1">
            <h2 className="text-2xl font-bold text-slate-900">John Doe</h2>
            <p className="text-slate-500">Super Administrator</p>
            <div className="flex flex-wrap justify-center md:justify-start gap-4 mt-4">
              <div className="flex items-center gap-2 text-sm text-slate-600 bg-slate-50 px-3 py-1.5 rounded-lg border border-slate-200">
                <Mail size={16} />
                john.doe@company.com
              </div>
              <div className="flex items-center gap-2 text-sm text-slate-600 bg-slate-50 px-3 py-1.5 rounded-lg border border-slate-200">
                <Phone size={16} />
                +1 (555) 000-0000
              </div>
            </div>
          </div>
          <button className="btn btn-primary px-8">Save Changes</button>
        </div>

        <div className="p-4 space-y-2">
          {sections.map((section) => (
            <button
              key={section.title}
              className="w-full flex items-center gap-4 p-4 rounded-xl hover:bg-slate-50 transition-all text-left group"
            >
              <div className="w-10 h-10 bg-slate-100 text-slate-600 rounded-lg flex items-center justify-center group-hover:bg-primary-50 group-hover:text-primary-600 transition-colors">
                <section.icon size={20} />
              </div>
              <div className="flex-1">
                <h3 className="text-sm font-bold text-slate-900">{section.title}</h3>
                <p className="text-xs text-slate-500">{section.description}</p>
              </div>
              <ChevronRight size={18} className="text-slate-300 group-hover:text-slate-600 transition-colors" />
            </button>
          ))}
          
          <div className="flex items-center gap-4 p-4 rounded-xl">
            <div className="w-10 h-10 bg-slate-100 text-slate-600 rounded-lg flex items-center justify-center">
              {isDarkMode ? <Moon size={20} /> : <Sun size={20} />}
            </div>
            <div className="flex-1">
              <h3 className="text-sm font-bold text-slate-900">Appearance</h3>
              <p className="text-xs text-slate-500">Switch between light and dark mode.</p>
            </div>
            <button 
              onClick={() => setIsDarkMode(!isDarkMode)}
              className={`
                w-12 h-6 rounded-full transition-all duration-300 p-1
                ${isDarkMode ? 'bg-primary-600 justify-end' : 'bg-slate-200 justify-start'}
                flex items-center
              `}
            >
              <div className="w-4 h-4 bg-white rounded-full shadow-sm"></div>
            </button>
          </div>
        </div>
      </div>

      <div className="card border-red-100 bg-red-50/30 p-8 flex flex-col sm:flex-row items-center justify-between gap-6">
        <div>
          <h3 className="text-lg font-bold text-red-900">Danger Zone</h3>
          <p className="text-sm text-red-700/70">Once you delete your account, there is no going back. Please be certain.</p>
        </div>
        <button className="btn bg-white text-red-600 border border-red-200 hover:bg-red-50">Delete Account</button>
      </div>
    </div>
  );
};

export default Profile;
