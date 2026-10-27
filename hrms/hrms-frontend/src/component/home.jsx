import { Link } from 'react-router-dom'

function Home() {
  return (
    <div className="container text-center mt-5">
      <h1 className="mb-3">Welcome to HRMS Frontend</h1>
      <p className="text-muted mb-4">ABES HRMS Portal</p>

      <Link to="/login" className="btn btn-primary me-2">Login</Link>
      <Link to="/register" className="btn btn-outline-primary">Register</Link>
    </div>
  )
}

export default Home