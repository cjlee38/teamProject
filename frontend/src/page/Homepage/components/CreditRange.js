import React, {useState} from 'react';


function CreditRange() {
    const [number, setNumber]=useState(0);  /*첫번째원소는 현재상태, 두번쨰 원소는 전달받은 값을 최신상태로 설정해주는 세터함수*/ 

    const onIncrease=()=>{
        setNumber(prevNumber=>prevNumber+1);
    }
    const onDecrease=()=>{
        setNumber(prevNumber=>prevNumber-1);
    }
  return (
    <div>
      <h1>{number}</h1>
      <button onClick={onIncrease}>+1</button>
      <button onClick={onDecrease}>-1</button>
    </div>
  );
}

export default CreditRange;