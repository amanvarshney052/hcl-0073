import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

function Registration() {
  const [Name, setName] = useState('');
  const [Designation, setDesignation] = useState('');
  const [Stack, setStack] = useState('');
  const [Email, setEmail] = useState('');
  const [Password, setPassword] = useState('');

  async function sendData(e) {
    e.preventDefault();

    try {
      const server = await fetch(
        'http://localhost:8080/v1/user/register',
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            emp_name: Name,
            designation: Designation,
            prog_stack: Stack,
            email: Email,
            password: Password
          })
        }
      );

      const data = await server.json();

      if (server.ok) {
        alert('Employee Registered Successfully');
        setName('');
        setDesignation('');
        setStack('');
        setEmail('');
        setPassword('');
      } else {
        alert('Error: ' + (data.message || 'Registration failed'));
      }
    } catch (error) {
      console.log(error);
      alert('Unable to connect to Spring Boot');
    }
  }

  return (
    <div className="container mt-4">
      <h2 className="text-center mb-4">Employee Registration</h2>

      <form onSubmit={sendData}>
        <div className="form-group mb-3">
          <label>Employee Name</label>
          <input
            type="text"
            className="form-control"
            placeholder="Enter employee name"
            value={Name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>

        <div className="form-group mb-3">
          <label>Employee Designation</label>
          <input
            type="text"
            className="form-control"
            placeholder="Enter designation"
            value={Designation}
            onChange={(e) => setDesignation(e.target.value)}
            required
          />
        </div>

        <div className="form-group mb-3">
          <label>Employee Tech Stack</label>
          <input
            type="text"
            className="form-control"
            placeholder="Enter tech stack"
            value={Stack}
            onChange={(e) => setStack(e.target.value)}
            required
          />
        </div>

        <div className="form-group mb-3">
          <label>Email</label>
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
            minLength={8}
            required
          />
        </div>

        <button type="submit" className="btn btn-primary w-100">
          Submit
        </button>
      </form>
    </div>
  );
}

export default Registration;