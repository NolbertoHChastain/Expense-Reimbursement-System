import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { BrowserRouter, Routes, Route } from 'react-router';
import './index.css'
import App from './App.tsx'
import Login from './components/Login.tsx';
import Signup from './components/Signup.tsx';
import Dashboard from './components/Dashboard.tsx';
import Auth from './components/Auth.tsx';

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />} >
          <Route index element={<Signup /> } />
          <Route path="dashboard" element={<Auth><Dashboard /></Auth>} />
          <Route path="login" element={<Login /> } />
        </Route>
      </Routes>
    </BrowserRouter>
  </StrictMode>,
)
