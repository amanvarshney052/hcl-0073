import React, { useEffect,useState } from 'react'

function SampleCode() {
  const [counter, setCounter] = useState(100);
  const [pointer, setPointer] = useState(100);

  useEffect(() => {
    
    console.log("Counter value: ", counter);
    console.log("Pointer value: ", pointer);
  },[pointer,counter]);

  function setCounterValue() {
    setCounter(counter + 20);
    
  }
  function setPointerValue() {
    setPointer(pointer - 2);
  }

  return (
    <div>
      <h2>Counter Value= {counter}</h2>
      <h2>Pointer Value= {pointer}</h2>
      <button onClick={setCounterValue}>Counter</button>
      <button onClick={setPointerValue}>Pointer</button>
    </div>
  )
}

export default SampleCode