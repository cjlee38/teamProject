import React, { useState, } from 'react';
import { BrowserRouter as Link } from 'react-router-dom';
import Input from './components/Input';
import Button from './components/Button';
import Title from './components/Title';
import './LoginPage.scss';
import Axios from 'axios';
import {withRouter} from 'react-router-dom';


const LoginP = (props) => {
  const [inputs, setInputs] = useState({
    id: "",
    password: ""
  });

  const { id, password } = inputs;
  const [user, setUser] = useState();
  const onChange = e => {
    const { value, name } = e;

    setInputs({
      ...inputs,
      [name]: value
    });
  }

  const reset = () => {
    setInputs({
      id: "",
      password: ""
    })
  }

  const tryLogin = async() => {
    
    let config = {
      headers: {
          "Access-Control-Allow-Origin": "*"
      },
  };
  console.log(id, password)
    Axios.post('http://ec2-13-209-184-168.ap-northeast-2.compute.amazonaws.com:1415/web/v1/user/Login', {
     "studentNumber": id, "password": password 
    }, config)
      .then(async (response) => {
        console.log(response);
        await doSignin(response.data.data.userId);       
    })
      .catch(async function (error) {
        console.log(error);
        alert("아이디/비밀번호를 확인해주세요!");
      });
      
    reset();
  }

  const doSignin = async (id_input) => {
    await window.sessionStorage.setItem('id', id_input);
    await props.onLogin(id_input);
    await props.history.push('/Check')
  }
  const toSignup = () => {
    props.history.push('/Signup')
  }
  return (
    <>
      <div className="body">
        <Title />
        {/* <form onSubmit={tryLogin} > */}
        <form action="">
        <div className="idPassword">
          <Input
            placeholder={"ex)195002215"}
            name={"id"}
            onChange={onChange}
            value={id}
            text={"학번"}
          />
          <Input
            placeholder={"ex)12345678"}
            name={"password"}
            type={"password"}
            onChange={onChange}
            value={password}
            text={"비밀번호"}
          />
        </div>
        <div className="idPassword">

            <button type={"submit"}className={"login"} name={"로그인"} onClick={tryLogin} > 로그인 </button>

            <Button onClick={toSignup} name={"회원가입"} value={"signUp"} />
        </div></form>
        {/* </form> */}
    </div>
    </>
  )
}




export default withRouter(LoginP)