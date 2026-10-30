
import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { apiRequest } from '../api';


function Dashboard() {
  const navigate = useNavigate();

  const [employee, setEmployee] = useState(null);
  const [employees, setEmployees] = useState([]);
  const [loadingEmployees, setLoadingEmployees] = useState(false);
  const [error, setError] = useState('');

  useEffect(() => {
    const savedEmployee = localStorage.getItem('employee');

    if (!savedEmployee) {
      navigate('/login', { replace: true });
      return;
    }

    try {
      setEmployee(JSON.parse(savedEmployee));
    } catch {
      localStorage.removeItem('employee');
      navigate('/login', { replace: true });
    }
  }, [navigate]);

  async function loadEmployees() {
    setLoadingEmployees(true);
    setError('');

    try {
      const data = await apiRequest('/v1/user/getEmp');
      setEmployees(data);
    } catch (err) {
      setError(err.message || 'Unable to load employees');
    } finally {
      setLoadingEmployees(false);
    }
  }

  function logout() {
    localStorage.removeItem('employee');
    navigate('/login', { replace: true });
  }

  if (!employee) {
    return <div className="container mt-5">Loading dashboard...</div>;
  }

  return (
    <div className="dashboard-page">
      

      <div className="card shadow-sm mb-4">
        <div className="card-body">
          <h4>Welcome, {employee.emp_name || 'Employee'}!</h4>
          <hr />

          <p>
            <strong>Employee ID:</strong> {employee.employeeId}
          </p>
          <p>
            <strong>Name:</strong> {employee.emp_name || 'Not provided'}
          </p>
          <p>
            <strong>Email:</strong> {employee.email}
          </p>
          <p>
            <strong>Designation:</strong>
            {' '}{employee.designation || 'Not provided'}
          </p>
          <p>
            <strong>Tech Stack:</strong>
            {' '}{employee.prog_stack || 'Not provided'}
          </p>
        </div>
      </div>

      <div className="d-flex flex-wrap gap-2 mb-3">
        <button
          className="btn btn-primary"
          onClick={loadEmployees}
          disabled={loadingEmployees}
        >
          {loadingEmployees ? 'Loading...' : 'View All Employees'}
        </button>

        <Link to="/register" className="btn btn-outline-primary">
          Register Employee
        </Link>
        <Link to="/attendance" className="btn btn-success">
          Manage Attendance
        </Link>
      </div>

      {error && <div className="alert alert-danger">{error}</div>}

      {employees.length > 0 && (
        <div className="table-responsive">
          <table className="table table-bordered table-striped">
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Designation</th>
                <th>Tech Stack</th>
              </tr>
            </thead>

            <tbody>
              {employees.map((item) => (
                <tr key={item.emp_id ?? item.empId}>
                  <td>{item.emp_id ?? item.empId}</td>
                  <td>{item.emp_name ?? item.empName}</td>
                  <td>{item.email}</td>
                  <td>{item.designation}</td>
                  <td>{item.prog_stack ?? item.progStack}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default Dashboard;
