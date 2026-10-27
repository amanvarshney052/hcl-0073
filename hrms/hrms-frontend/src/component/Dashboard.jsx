import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.min.css'

function Dashboard() {
  const navigate = useNavigate()
  const [currentUser, setCurrentUser] = useState(null)
  const [employees, setEmployees] = useState([])
  const [loading, setLoading] = useState(false)
  const [fetchError, setFetchError] = useState('')

  useEffect(() => {
    const stored = localStorage.getItem('user')
    if (stored) {
      try {
        setCurrentUser(JSON.parse(stored))
      } catch (e) {
        console.error('Failed to parse user from localStorage', e)
      }
    }
    fetchEmployees()
  }, [])

  async function fetchEmployees() {
    setLoading(true)
    setFetchError('')
    try {
      const res = await fetch('http://localhost:8080/getEmp')
      if (res.ok) {
        const data = await res.json()
        setEmployees(data)
      } else {
        setFetchError('Failed to fetch employee list.')
      }
    } catch (err) {
      setFetchError('Backend server is not responding for /getEmp.')
    } finally {
      setLoading(false)
    }
  }

  function handleLogout() {
    localStorage.removeItem('user')
    navigate('/login')
  }

  return (
    <div className="container mt-4">
      {/* Header */}
      <div className="d-flex justify-content-between align-items-center pb-3 mb-4 border-bottom">
        <div>
          <h2 className="mb-0">HRMS Portal Dashboard</h2>
          <small className="text-muted">Welcome, {currentUser?.emp_name || currentUser?.empName || 'Employee'}!</small>
        </div>
        <button className="btn btn-outline-danger" onClick={handleLogout}>
          Logout
        </button>
      </div>

      {/* User Info Card */}
      {currentUser && (
        <div className="card shadow-sm mb-4">
          <div className="card-header bg-primary text-white">
            <h5 className="mb-0">Your Profile</h5>
          </div>
          <div className="card-body">
            <div className="row">
              <div className="col-md-3 mb-2">
                <strong>Employee ID:</strong> {currentUser.emp_id || currentUser.empId || 'N/A'}
              </div>
              <div className="col-md-3 mb-2">
                <strong>Name:</strong> {currentUser.emp_name || currentUser.empName || 'N/A'}
              </div>
              <div className="col-md-3 mb-2">
                <strong>Email:</strong> {currentUser.email || 'N/A'}
              </div>
              <div className="col-md-3 mb-2">
                <strong>Designation:</strong> {currentUser.designation || 'N/A'}
              </div>
              <div className="col-md-6 mb-2">
                <strong>Tech Stack:</strong> {currentUser.progStack || 'N/A'}
              </div>
            </div>
          </div>
        </div>
      )}

      {/* Employees Table */}
      <div className="card shadow-sm">
        <div className="card-header d-flex justify-content-between align-items-center">
          <h5 className="mb-0">All Registered Employees</h5>
          <button className="btn btn-sm btn-outline-secondary" onClick={fetchEmployees} disabled={loading}>
            {loading ? 'Refreshing...' : 'Refresh List'}
          </button>
        </div>
        <div className="card-body">
          {fetchError && <div className="alert alert-warning">{fetchError}</div>}
          {employees.length === 0 && !loading && (
            <p className="text-muted mb-0">No employees found or server not connected.</p>
          )}
          {employees.length > 0 && (
            <div className="table-responsive">
              <table className="table table-hover table-striped align-middle">
                <thead className="table-dark">
                  <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Designation</th>
                    <th>Tech Stack</th>
                  </tr>
                </thead>
                <tbody>
                  {employees.map((emp) => (
                    <tr key={emp.emp_id || emp.empId}>
                      <td>{emp.emp_id || emp.empId}</td>
                      <td>{emp.emp_name || emp.empName}</td>
                      <td>{emp.email}</td>
                      <td>{emp.designation}</td>
                      <td>{emp.progStack}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </div>
      </div>
    </div>
  )
}

export default Dashboard
