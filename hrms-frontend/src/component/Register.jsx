import React from 'react'

function Register() {

    const [empid,setEmpid] = React.useState("");
    const [empname,setEmpname] = React.useState("");
    const [EmpDesignation,setEmpDesignation] = React.useState("");
    const [EmpStack,setEmpStack] = React.useState("");
    const [email,setEmail] = React.useState("");
    const [password,setPassword] = React.useState("");


    function sendData(){
        alert(`Employee ID: ${empid} \nEmployee Name: ${empname} \nEmployee Designation: ${EmpDesignation} \nEmployee Tech Stack: ${EmpStack} \nEmail: ${email} \nPassword: ${password}`);


    }
  return (
    <div>
        <form>
           <div className="form-group">
                <label htmlFor="empid">Employee ID</label>
                <input type="number" className="form-control" id="empid" placeholder="Enter Employee ID" value={empid} onChange={(e)=>{setEmpid(e.target.value)}}/>
            </div>
            <div className="form-group">
                <label htmlFor="empname">Employee Name</label>
                <input type="text" className="form-control" id="empname" placeholder="Enter Employee Name" value={empname} onChange={(e)=>{setEmpname(e.target.value)}}/>
            </div>
            <div className="form-group">
                <label htmlFor="EmpDesignation">Employee Designation</label>
                <input type="text" className="form-control" id="EmpDesignation" placeholder="Enter Employee Designation" value={EmpDesignation} onChange={(e)=>{setEmpDesignation(e.target.value)}}/>
            </div>
            <div className="form-group">
                <label htmlFor="EmpStack">Employee Tech Stack</label>
                <input type="text" className="form-control" id="EmpStack" placeholder="Enter Employee Tech Stack" value={EmpStack} onChange={(e)=>{setEmpStack(e.target.value)}}/>
            </div>
            <div className="form-group">
                <label htmlFor="email">Email</label>
                <input type="email" className="form-control" id="email" placeholder="Enter Email" value={email} onChange={(e)=>{setEmail(e.target.value)}}/>
            </div>
            <div className="form-group">
                <label htmlFor="password">Password</label>
                <input type="password" className="form-control" id="password" placeholder="Enter Password" value={password} onChange={(e)=>{setPassword(e.target.value)}}/>
            </div>
            <button type="submit" className="btn btn-primary" onClick={sendData}>Submit</button>
        </form>
    </div>
  )
}

export default Register
