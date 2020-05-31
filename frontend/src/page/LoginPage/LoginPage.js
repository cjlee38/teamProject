import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom';
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
    console.log(value, name);

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
    console.log(props)
    console.log('sdffd')
    console.log(id, password);
    Axios.get('http://localhost:1415/web/v1/user/Login', {
      params: { studentNumber: id, password: password }
    })
      .then((response) => {
        console.log("userId : ", response.data.userId);
        props.onLogin(response.data.userId);
        props.history.push({
          pathname: '/Check',
          state: { user_id: response.data.userId }
        })
        doSignin(id)
       
    })
      .catch(async function (error) {
        // props.onLogin(3);
        await doSignin(3)
        props.history.push({
          pathname: '/Check',
        })


        
     console.log(error);
        // alert("아이디/비밀번호를 확인해주세요!")

        
      });
      
    reset();
  }

  const doSignin = (id_input) => {
    console.log(id_input)
    window.sessionStorage.setItem('id', id_input);
    props.onLogin(id_input);
    props.history.push('/Check')
  }
  console.log(user)

  return (
    <>
      <div className="body">
        <Title />
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
        <div className="Button">
{/* 
          <Link to={{
            pathname : `/Check/${user}`,
            state : {
              userid : user
            }
          }}> <Button onClick={tryLogin} name={"로그인"} value={"login"} /></Link> */}
          {/* <Link to='/Check'> */}
            <Button onClick={tryLogin} name={"로그인"} value={"login"} />
          {/* </Link> */}
          <Link to="/Signup">
            <Button name={"회원가입"} value={"signUp"} />
          </Link>
        </div>
    </div>
    </>
  )
}




export default withRouter(LoginP)