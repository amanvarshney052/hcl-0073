
import './App.css'
import SampleCode from './component/SampleCode.jsx'
import UseFetchApi from './component/UseFetchApi.jsx'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Home from './component/home.jsx'
import Login from './component/login.jsx'
import Registration from './component/Register.jsx'
import Dashboard from './component/Dashboard.jsx'

function App() {

  
 

  return (
    <>
      <h1>Welcome to HRMS Frontend</h1>
      {/* <SampleCode />
      <UseFetchApi /> */}
       <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Registration />} />
         <Route path="/dashboard" element={<Dashboard />} />
        
        
      </Routes>
    </BrowserRouter>
    </>
  )
}

export default App
