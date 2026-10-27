import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
function login() {
  return (
    <div>
      <form >
        <div className="form-group">
          <label htmlFor="email">Email</label>
          <input type="email" className="form-control" id="email" placeholder="Enter Email"/>
        </div>
        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input type="password" className="form-control" id="password" placeholder="Enter Password"/>
        </div>
      </form>
    </div>
  )
}

export default login
