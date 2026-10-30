
import React, { useCallback, useEffect, useState } from 'react';
import {  useNavigate } from 'react-router-dom';
import { apiRequest } from '../api';


function getLocalDate() {
  const now = new Date();
  const year = now.getFullYear();
  const month = String(now.getMonth() + 1).padStart(2, '0');
  const day = String(now.getDate()).padStart(2, '0');

  return `${year}-${month}-${day}`;
}

function formatDateTime(value) {
  if (!value) return 'Not checked out';

  // Handles LocalDateTime values returned by Spring Boot.
  const normalized = value.endsWith('Z') ? value : value;
  const date = new Date(normalized);

  if (Number.isNaN(date.getTime())) return value;

  return date.toLocaleString();
}

function Attendance() {
  const navigate = useNavigate();

  const [employee, setEmployee] = useState(null);
  const [records, setRecords] = useState([]);
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState('');
  const [message, setMessage] = useState('');

  const today = getLocalDate();

  useEffect(() => {
    const savedEmployee = localStorage.getItem('employee');

    if (!savedEmployee) {
      navigate('/login', { replace: true });
      return;
    }

    try {
      const parsed = JSON.parse(savedEmployee);

      if (!parsed.employeeId) {
        localStorage.removeItem('employee');
        navigate('/login', { replace: true });
        return;
      }

      setEmployee(parsed);
    } catch {
      localStorage.removeItem('employee');
      navigate('/login', { replace: true });
    }
  }, [navigate]);

  const loadHistory = useCallback(async () => {
    if (!employee?.employeeId) return;

    setLoading(true);
    setError('');

    try {
      const data = await apiRequest(
        `/api/attendance/employee/${employee.employeeId}`
      );

      setRecords(Array.isArray(data) ? data : []);
    } catch (err) {
      setError(err.message || 'Unable to load attendance history.');
    } finally {
      setLoading(false);
    }
  }, [employee]);

  useEffect(() => {
    if (employee) {
      loadHistory();
    }
  }, [employee, loadHistory]);

  if (!employee) {
    return (
      <div className="container py-5">
        Loading employee profile...
      </div>
    );
  }

  const todaysRecord = records.find(
    (record) => record.attendancedate === today
  );

  const checkedIn = Boolean(todaysRecord);
  const checkedOut = Boolean(todaysRecord?.outTime);

  async function checkIn() {
    setSubmitting(true);
    setError('');
    setMessage('');

    try {
      await apiRequest('/api/attendance', {
        method: 'POST',
        body: JSON.stringify({
          employee: {
            emp_id: employee.employeeId,
          },
          attendancedate: today,
          inTime: null,
          outTime: null,
        }),
      });

      setMessage('Check-in successful!');
      await loadHistory();
    } catch (err) {
      setError(err.message || 'Unable to check in.');
    } finally {
      setSubmitting(false);
    }
  }

  async function checkOut() {
    if (!todaysRecord?.attendanceId) {
      setError('No attendance record was found for today.');
      return;
    }

    setSubmitting(true);
    setError('');
    setMessage('');

    try {
      await apiRequest(
        `/api/attendance/${todaysRecord.attendanceId}/checkout`,
        { method: 'PATCH' }
      );

      setMessage('Check-out successful!');
      await loadHistory();
    } catch (err) {
      setError(err.message || 'Unable to check out.');
    } finally {
      setSubmitting(false);
    }
  }

  return (
    <div className="attendance-page">
      

      {error && (
        <div className="alert alert-danger" role="alert">
          {error}
        </div>
      )}

      {message && (
        <div className="alert alert-success" role="status">
          {message}
        </div>
      )}

      <div className="card shadow-sm mb-4">
        <div className="card-body">
          <h5 className="card-title">Today's Attendance</h5>

          <p>
            <strong>Employee ID:</strong> {employee.employeeId}
          </p>

          <p>
            <strong>Date:</strong> {today}
          </p>

          <p>
            <strong>Status:</strong>{' '}
            {!checkedIn
              ? 'Not checked in'
              : checkedOut
                ? 'Checked out'
                : 'Checked in'}
          </p>

          {checkedIn && (
            <>
              <p>
                <strong>Check-in:</strong>{' '}
                {formatDateTime(todaysRecord.inTime)}
              </p>

              {checkedOut && (
                <p>
                  <strong>Check-out:</strong>{' '}
                  {formatDateTime(todaysRecord.outTime)}
                </p>
              )}
            </>
          )}

          <div className="d-flex gap-2 flex-wrap">
            <button
              type="button"
              className="btn btn-success"
              onClick={checkIn}
              disabled={submitting || loading || checkedIn}
            >
              {submitting && !checkedIn
                ? 'Checking in...'
                : 'Check In'}
            </button>

            <button
              type="button"
              className="btn btn-danger"
              onClick={checkOut}
              disabled={
                submitting || loading || !checkedIn || checkedOut
              }
            >
              {submitting && checkedIn && !checkedOut
                ? 'Checking out...'
                : 'Check Out'}
            </button>

            <button
              type="button"
              className="btn btn-outline-primary"
              onClick={loadHistory}
              disabled={loading || submitting}
            >
              Refresh History
            </button>
          </div>
        </div>
      </div>

      <div className="card shadow-sm">
        <div className="card-body">
          <h5 className="card-title mb-3">Attendance History</h5>

          {loading ? (
            <p>Loading attendance history...</p>
          ) : records.length === 0 ? (
            <p className="text-muted">
              No attendance records found.
            </p>
          ) : (
            <div className="table-responsive">
              <table className="table table-striped table-bordered align-middle">
                <thead className="table-light">
                  <tr>
                    <th>Attendance ID</th>
                    <th>Date</th>
                    <th>Check-in</th>
                    <th>Check-out</th>
                    <th>Status</th>
                  </tr>
                </thead>

                <tbody>
                  {[...records]
                    .sort((a, b) =>
                      (b.attendancedate || '').localeCompare(
                        a.attendancedate || ''
                      )
                    )
                    .map((record) => (
                      <tr key={record.attendanceId}>
                        <td>{record.attendanceId}</td>
                        <td>{record.attendancedate}</td>
                        <td>{formatDateTime(record.inTime)}</td>
                        <td>{formatDateTime(record.outTime)}</td>
                        <td>
                          {record.outTime
                            ? 'Completed'
                            : 'Checked in'}
                        </td>
                      </tr>
                    ))}
                </tbody>
              </table>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default Attendance;
