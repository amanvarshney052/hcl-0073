
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { apiRequest } from '../api';
import AuthLayout from './AuthLayout';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();

  async function loginUser(e) {
    e.preventDefault();
    setError('');
    setLoading(true);

    try {
      const data = await apiRequest('/api/auth/login', {
        method: 'POST',
        body: JSON.stringify({ email, password }),
      });

      // Support common employee ID field names.
      const employeeId =
        data.employeeId ?? data.emp_id ?? data.empId;

      if (employeeId == null) {
        throw new Error(
          'Login succeeded, but the employee ID is missing from the response.'
        );
      }

      const employee = {
        employeeId,
        emp_name: data.emp_name ?? data.empName ?? '',
        email: data.email ?? email,
        designation: data.designation ?? '',
        prog_stack: data.prog_stack ?? data.progStack ?? '',
      };

      localStorage.setItem('employee', JSON.stringify(employee));
      navigate('/dashboard', { replace: true });
    } catch (err) {
      setError(err.message || 'Login failed');
    } finally {
      setLoading(false);
    }
  }

  return (
    <AuthLayout
      title="Welcome back"
      subtitle="Sign in to access your employee workspace."
      footer={
        <>
          New to HCL HRMS? <Link to="/register">Create an account</Link>
        </>
      }
    >
      {error && (
        <div className="alert alert-danger" role="alert">
          {error}
        </div>
      )}

      <form onSubmit={loginUser}>
        <div className="mb-3">
          <label htmlFor="email" className="form-label">
            Email address
          </label>
          <input
            id="email"
            type="email"
            className="form-control"
            placeholder="you@company.com"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            autoComplete="username"
            required
          />
        </div>

        <div className="mb-3">
          <label htmlFor="password" className="form-label">
            Password
          </label>
          <input
            id="password"
            type="password"
            className="form-control"
            placeholder="Enter your password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            autoComplete="current-password"
            required
          />
        </div>

        <button
          type="submit"
          className="btn btn-primary w-100"
          disabled={loading}
        >
          {loading ? 'Signing in...' : 'Sign in to workspace'}
        </button>
      </form>
    </AuthLayout>
  );
}

export default Login;
