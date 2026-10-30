
import React, { useState } from 'react';
import { NavLink, useLocation, useNavigate } from 'react-router-dom';

const navigation = [
  { label: 'Overview', path: '/dashboard', icon: '◫' },
  { label: 'Employees', path: '/employees', icon: '♙' },
  { label: 'Attendance', path: '/attendance', icon: '◷' },
];

function HRMSLayout({ children }) {
  const [menuOpen, setMenuOpen] = useState(false);
  const navigate = useNavigate();
  const location = useLocation();

  const employee = (() => {
    try {
      return JSON.parse(localStorage.getItem('employee') || 'null');
    } catch {
      return null;
    }
  })();

  const titles = {
    '/dashboard': 'Dashboard',
    '/employees': 'Employee Directory',
    '/attendance': 'Attendance Management',
  };

  function logout() {
    localStorage.removeItem('employee');
    navigate('/login', { replace: true });
  }

  return (
    <div className="hrms-shell">
      <button
        className="mobile-menu-toggle"
        onClick={() => setMenuOpen(!menuOpen)}
        aria-label="Toggle navigation"
      >
        ☰
      </button>

      {menuOpen && (
        <button
          className="sidebar-backdrop"
          aria-label="Close navigation"
          onClick={() => setMenuOpen(false)}
        />
      )}

      <aside className={`hrms-sidebar ${menuOpen ? 'sidebar-open' : ''}`}>
        <NavLink to="/" className="brand" onClick={() => setMenuOpen(false)}>
          <span className="brand-mark">H</span>
          <span>
            <strong>HCL HRMS</strong>
            <small>PEOPLE MANAGEMENT</small>
          </span>
        </NavLink>

        <div className="sidebar-label">WORKSPACE</div>

        <nav className="sidebar-nav">
          {navigation.map((item) => (
            <NavLink
              key={item.path}
              to={item.path}
              onClick={() => setMenuOpen(false)}
              className={({ isActive }) =>
                `sidebar-link ${isActive ? 'active' : ''}`
              }
            >
              <span className="nav-icon">{item.icon}</span>
              {item.label}
            </NavLink>
          ))}
        </nav>

        <div className="sidebar-bottom">
          <div className="sidebar-profile">
            <div className="avatar">
              {(employee?.emp_name || 'E').charAt(0).toUpperCase()}
            </div>
            <div className="profile-copy">
              <strong>{employee?.emp_name || 'Employee'}</strong>
              <small>{employee?.designation || 'Team Member'}</small>
            </div>
          </div>

          <button className="sidebar-logout" onClick={logout}>
            ↪ &nbsp; Sign out
          </button>
        </div>
      </aside>

      <main className="hrms-main">
        <header className="hrms-topbar">
          <div>
            <span className="breadcrumb-label">WORKSPACE / HRMS</span>
            <h1>{titles[location.pathname] || 'HRMS Portal'}</h1>
          </div>

          <div className="topbar-user">
            <div className="avatar avatar-small">
              {(employee?.emp_name || 'E').charAt(0).toUpperCase()}
            </div>
            <div className="profile-copy">
              <strong>{employee?.emp_name || 'Employee'}</strong>
              <small>Signed in</small>
            </div>
          </div>
        </header>

        <section className="hrms-content">{children}</section>
      </main>
    </div>
  );
}

export default HRMSLayout;
