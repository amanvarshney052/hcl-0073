import './App.css';

import { BrowserRouter, Routes, Route } from 'react-router-dom';

import Home from './component/home.jsx';
import Login from './component/login.jsx';
import Registration from './component/Register.jsx';
import Dashboard from './component/Dashboard.jsx';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Registration />} />
        <Route path="/dashboard" element={<Dashboard />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;