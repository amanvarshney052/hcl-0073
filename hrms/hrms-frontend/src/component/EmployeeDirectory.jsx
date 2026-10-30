
import React, { useEffect, useMemo, useState } from 'react';
import { apiRequest } from '../api';

function EmployeeDirectory() {
  const [employees, setEmployees] = useState([]);
  const [search, setSearch] = useState('');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    let active = true;

    async function loadEmployees() {
      try {
        const data = await apiRequest('/v1/user/getEmp');
        if (active) {
          setEmployees(Array.isArray(data) ? data : []);
        }
      } catch (err) {
        if (active) setError(err.message || 'Unable to load employees.');
      } finally {
        if (active) setLoading(false);
      }
    }

    loadEmployees();

    return () => {
      active = false;
    };
  }, []);

  const filteredEmployees = useMemo(() => {
    const query = search.trim().toLowerCase();

    return employees.filter((employee) =>
      [
        employee.emp_name ?? employee.empName,
        employee.email,
        employee.designation,
        employee.prog_stack ?? employee.progStack,
        employee.emp_id ?? employee.empId,
      ].some((value) => String(value ?? '').toLowerCase().includes(query))
    );
  }, [employees, search]);

  return (
    <div>
      <div className="page-intro">
        <div>
          <span className="eyebrow">PEOPLE & ORGANIZATION</span>
          <h2>Employee Directory</h2>
          <p>Browse and find employees across your organization.</p>
        </div>

        <div className="directory-count">
          <strong>{employees.length}</strong>
          <span>Total employees</span>
        </div>
      </div>

      <div className="card">
        <div className="card-body">
          <div className="directory-toolbar">
            <div>
              <h5 className="mb-1">All employees</h5>
              <p className="text-muted mb-0">
                {filteredEmployees.length} matching records
              </p>
            </div>

            <input
              type="search"
              className="form-control employee-search"
              placeholder="Search name, email, role..."
              aria-label="Search employees"
              value={search}
              onChange={(event) => setSearch(event.target.value)}
            />
          </div>

          {error && <div className="alert alert-danger mt-3">{error}</div>}

          {loading ? (
            <p className="text-muted mt-4">Loading employees...</p>
          ) : filteredEmployees.length === 0 ? (
            <div className="empty-state">
              <div className="empty-state-icon">♙</div>
              <h5>No employees found</h5>
              <p>Try another search or register a new employee.</p>
            </div>
          ) : (
            <div className="table-responsive mt-4">
              <table className="table">
                <thead>
                  <tr>
                    <th>Employee</th>
                    <th>Employee ID</th>
                    <th>Designation</th>
                    <th>Tech stack</th>
                  </tr>
                </thead>

                <tbody>
                  {filteredEmployees.map((employee) => {
                    const id = employee.emp_id ?? employee.empId;
                    const name =
                      employee.emp_name ?? employee.empName ?? 'Employee';

                    return (
                      <tr key={id}>
                        <td>
                          <div className="employee-cell">
                            <div className="employee-avatar">
                              {name.charAt(0).toUpperCase()}
                            </div>
                            <div>
                              <strong>{name}</strong>
                              <small>{employee.email}</small>
                            </div>
                          </div>
                        </td>
                        <td>#{id}</td>
                        <td>{employee.designation || '—'}</td>
                        <td>
                          <span className="stack-tag">
                            {employee.prog_stack ?? employee.progStack ?? '—'}
                          </span>
                        </td>
                      </tr>
                    );
                  })}
                </tbody>
              </table>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default EmployeeDirectory;
