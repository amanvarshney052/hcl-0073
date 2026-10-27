import React from 'react'
import { useState,useEffect } from 'react'
function UseFetchApi() {
    const[serverData,setServerData] = useState([]);

    useEffect(()=>{
        



        async function fetchData() {
            try{
                const serverData = await fetch("http://localhost:8080/getEmp");
                const jsonData = await serverData.json();
                console.log("Data from API: ", jsonData);
                setServerData(jsonData);
            }catch(e){
                console.error("Error fetching data: ", e);
            }
        }
        fetchData();
    },[])
  return (
    <div>
        <h2>Data from API</h2>
        <h2>{JSON.stringify(serverData)}</h2>
    </div>
  )
}

export default UseFetchApi
