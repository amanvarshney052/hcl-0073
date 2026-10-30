
import './App.css';

import {
  BrowserRouter,
  Routes,
  Route,
  Navigate,
  Outlet,
} from 'react-router-dom';

import Home from './component/home.jsx';
import Login from './component/login.jsx';
import Registration from './component/Register.jsx';
import Dashboard from './component/Dashboard.jsx';
import Attendance from './component/Attendance.jsx';
import EmployeeDirectory from './component/EmployeeDirectory.jsx';
import HRMSLayout from './component/HRMSLayout.jsx';

function RequireEmployee() {
  const employee = (() => {
    try {
      return JSON.parse(localStorage.getItem('employee') || 'null');
    } catch {
      return null;
    }
  })();

  if (!employee?.employeeId) {
    return <Navigate to="/login" replace />;
  }

  return (
    <HRMSLayout>
      <Outlet />
    </HRMSLayout>
  );
}

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Registration />} />

        <Route element={<RequireEmployee />}>
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/employees" element={<EmployeeDirectory />} />
          <Route path="/attendance" element={<Attendance />} />
        </Route>

        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
