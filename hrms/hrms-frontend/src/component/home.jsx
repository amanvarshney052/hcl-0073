
import React from 'react';
import { Link } from 'react-router-dom';

function Home() {
  return (
    <div className="landing-page">
      <nav className="landing-nav">
        <Link to="/" className="landing-logo">
          <span className="brand-mark">H</span>
          HCL HRMS
        </Link>

        <div className="landing-nav-actions">
          <Link to="/login" className="landing-login">
            Sign in
          </Link>
          <Link to="/register" className="btn btn-primary">
            Get started →
          </Link>
        </div>
      </nav>

      <main className="home-hero">
        <span className="hero-pill">
          ✦ &nbsp; YOUR PEOPLE, ONE WORKSPACE
        </span>

        <h1>
          Great teams start with
          <span className="hero-highlight"> better HR.</span>
        </h1>

        <p>
          A simpler way to manage employee information, track
          attendance, and keep your workplace connected.
        </p>

        <div className="hero-actions">
          <Link to="/login" className="btn btn-primary btn-lg">
            Access your workspace →
          </Link>

          <Link to="/register" className="btn btn-light btn-lg">
            Register as employee
          </Link>
        </div>

        <div className="hero-preview">
          <div className="preview-topbar">
            <div className="preview-dots">
              <span /><span /><span />
            </div>
            <span>HCL HRMS · Workspace preview</span>
          </div>

          <div className="preview-content">
            <div className="preview-sidebar">
              <div className="preview-mark">H</div>
              <span className="preview-active">▦</span>
              <span>♙</span>
              <span>◷</span>
            </div>

            <div className="preview-main">
              <span className="preview-label">YOUR WORKSPACE</span>
              <h3>People overview</h3>

              <div className="preview-cards">
                <div>
                  <small>Employees</small>
                  <strong>People</strong>
                  <span>Employee directory</span>
                </div>
                <div>
                  <small>Attendance</small>
                  <strong>Daily</strong>
                  <span>Check-in & check-out</span>
                </div>
                <div>
                  <small>Management</small>
                  <strong>Connected</strong>
                  <span>One central workspace</span>
                </div>
              </div>

              <div className="preview-table">
                <span>EMPLOYEE WORKSPACE</span>
                <div />
                <div />
                <div />
              </div>
            </div>
          </div>
        </div>
      </main>

      <footer className="landing-footer">
        HCL HRMS · Employee Management Portal
      </footer>
    </div>
  );
}

export default Home;
