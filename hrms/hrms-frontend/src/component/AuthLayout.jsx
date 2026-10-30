
import React from 'react';
import { Link } from 'react-router-dom';

function AuthLayout({ title, subtitle, children, footer }) {
  return (
    <div className="auth-page">
      <section className="auth-visual">
        <Link to="/" className="auth-brand">
          <span className="brand-mark">H</span>
          <span>HCL HRMS</span>
        </Link>

        <div>
          <span className="auth-eyebrow">PEOPLE FIRST. WORK SMARTER.</span>
          <h1>Everything your team needs, in one place.</h1>
          <p>
            Manage employee information, track attendance, and
            simplify your everyday HR operations.
          </p>

          <div className="auth-feature-list">
            <span>✓ Employee management</span>
            <span>✓ Attendance tracking</span>
            <span>✓ One connected workspace</span>
          </div>
        </div>

        <small className="auth-footer-note">
          HCL HRMS · Employee Management Portal
        </small>
      </section>

      <section className="auth-form-area">
        <div className="auth-form-card">
          <Link to="/" className="auth-mobile-brand">
            <span className="brand-mark">H</span> HCL HRMS
          </Link>

          <span className="eyebrow">WELCOME TO HCL HRMS</span>
          <h2>{title}</h2>
          <p className="auth-caption">{subtitle}</p>

          {children}

          <div className="auth-switch">{footer}</div>
        </div>
      </section>
    </div>
  );
}

export default AuthLayout;
