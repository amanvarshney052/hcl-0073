
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { apiRequest } from '../api';
import AuthLayout from './AuthLayout';

function Registration() {
  const [form, setForm] = useState({
    name: '',
    designation: '',
    stack: '',
    email: '',
    password: '',
  });

  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();

  function handleChange(e) {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  }

  async function sendData(e) {
    e.preventDefault();
    setError('');
    setLoading(true);

    try {
      await apiRequest('/v1/user/register', {
        method: 'POST',
        body: JSON.stringify({
          emp_name: form.name,
          designation: form.designation,
          prog_stack: form.stack,
          email: form.email,
          password: form.password,
        }),
      });

      alert('Employee registered successfully. Please log in.');

      navigate('/login', { replace: true });
    } catch (err) {
      setError(err.message || 'Registration failed');
    } finally {
      setLoading(false);
    }
  }

  return (
  <AuthLayout
    title="Create your account"
    subtitle="Enter your details to join the HCL HRMS workspace."
    footer={
      <>
        Already registered? <Link to="/login">Sign in</Link>
      </>
    }
  >
    {error && (
      <div className="alert alert-danger" role="alert">
        {error}
      </div>
    )}

    <form onSubmit={sendData}>
      <div className="mb-3">
        <label htmlFor="name" className="form-label">Employee Name</label>
        <input
          id="name"
          name="name"
          type="text"
          className="form-control"
          placeholder="Enter employee name"
          value={form.name}
          onChange={handleChange}
          autoComplete="name"
          required
        />
      </div>

      <div className="mb-3">
        <label htmlFor="designation" className="form-label">Designation</label>
        <input
          id="designation"
          name="designation"
          className="form-control"
          placeholder="e.g. Software Engineer"
          value={form.designation}
          onChange={handleChange}
          required
        />
      </div>

      <div className="mb-3">
        <label htmlFor="stack" className="form-label">Tech Stack</label>
        <input
          id="stack"
          name="stack"
          className="form-control"
          placeholder="e.g. Java, Spring Boot, React"
          value={form.stack}
          onChange={handleChange}
          required
        />
      </div>

      <div className="mb-3">
        <label htmlFor="email" className="form-label">Email address</label>
        <input
          id="email"
          name="email"
          type="email"
          className="form-control"
          placeholder="you@company.com"
          value={form.email}
          onChange={handleChange}
          autoComplete="email"
          required
        />
      </div>

      <div className="mb-3">
        <label htmlFor="password" className="form-label">Password</label>
        <input
          id="password"
          name="password"
          type="password"
          className="form-control"
          placeholder="At least 8 characters"
          value={form.password}
          onChange={handleChange}
          minLength={8}
          autoComplete="new-password"
          required
        />
      </div>

      <button
        type="submit"
        className="btn btn-primary w-100"
        disabled={loading}
      >
        {loading ? 'Creating account...' : 'Create account'}
      </button>
    </form>
  </AuthLayout>
);
}

export default Registration;
