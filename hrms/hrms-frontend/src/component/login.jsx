import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

function Login() {
  const [Email, setEmail] = useState('');
  const [Password, setPassword] = useState('');

  async function loginUser(e) {
    e.preventDefault();

    try {
      const server = await fetch(
        'http://localhost:8080/v1/user/login',
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            email: Email,
            password: Password
          })
        }
      );

      const data = await server.json();

      if (server.ok) {
        alert('Login Successful');

        // Navigate to the dashboard
        window.location.href = '/dashboard';
      } else {
        alert(data.message || 'Invalid email or password');
      }
    } catch (error) {
      console.log(error);
      alert('Unable to connect to Spring Boot');
    }
  }

  return (
    <div className="container mt-5">
      <h2 className="text-center mb-4">Employee Login</h2>

      <form onSubmit={loginUser}>
        <div className="form-group mb-3">
          <label>Email address</label>
          <input
            type="email"
            className="form-control"
            placeholder="Enter email"
            value={Email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>

        <div className="form-group mb-3">
          <label>Password</label>
          <input
            type="password"
            className="form-control"
            placeholder="Enter password"
            value={Password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>

        <button type="submit" className="btn btn-primary w-100">
          Login
        </button>
      </form>
    </div>
  );
}

export default Login;