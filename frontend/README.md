# E-Commerce Admin Frontend

A modern, responsive React-based admin dashboard for the e-commerce backend.

## Tech Stack
- **React 18** (Vite)
- **Tailwind CSS** (Styling)
- **Framer Motion** (Animations)
- **Lucide React** (Icons)
- **Axios** (API requests)
- **React Router** (Navigation)

## Getting Started

1. **Install dependencies:**
   ```bash
   npm install
   ```

2. **Run the development server:**
   ```bash
   npm run dev
   ```
   The app will be available at `http://localhost:3000`.

3. **Proxy Configuration:**
   The frontend is pre-configured to proxy API requests to `http://localhost:8080`. You can change this in `vite.config.js`.

## Features
- **Modern UI:** Clean, premium design inspired by modern SaaS platforms.
- **Responsive:** Fully optimized for mobile, tablet, and desktop.
- **JWT Auth:** Pre-integrated with localStorage and Axios interceptors.
- **Protected Routes:** Dashboard access is restricted to authenticated users.
- **Skeleton Loaders:** Smooth loading experience for data-heavy sections.
- **Animations:** Subtle transitions and hover effects using Framer Motion.
