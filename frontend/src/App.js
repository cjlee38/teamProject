import React, {useState, useRef} from 'react';

function App() {
  const [inputs, setInputs] = useState({
    id: "",
    password: ""
  });

  const {id, password} = inputs;

  const idRef = useRef();

  const onChange = e => {
    const {value, name} = e;
    console.log(value, name);

    setInputs({
      ...inputs,
      [name]: value
    });
  }
  
  const reset = () => {
    setInputs({
      id : "",
      password : ""
    })
  }

  const tryLogin = () => {
    console.log(id, password);
    reset();
  }

  const trySignUp = () => {
    reset();
  }

  return (
    <div>
      <div className="head">
        <div className="leftLogo">

        </div>
      </div>
      <div className="body">
        <div className="welcomeWording">
          <div>
            <b>한국외국어대학교</b>
          </div>
          <div>
            <b>시간표 추천 시스템</b>
          </div>
          <div>
            <b>Hankuk University of Foreign Studies</b>
          </div>
          <div>
            <b>Schdule Maker</b>
          </div>
        </div>
        <div className="id,Password">
          <div className="id">
              <b>아디</b>
              <input 
                placeholder="학번"
                name="id"
                onChange={onChange} 
                value={id}
                ref={idRef}/>
          </div>
          <div className="password">
              <b>비번</b>
              <input 
                placeholder="비번" 
                name="password" 
                onChange={onChange} 
                value={password}/>
          </div>
        </div>
        <div className="Button">
            <button onClick={tryLogin}>로그인</button>
            <button onClick={trySignUp}>회원가입</button>
        </div>
      </div>
    </div>
  );
}

export default App;
