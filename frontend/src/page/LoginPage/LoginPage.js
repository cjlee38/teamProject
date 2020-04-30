import React, {useState} from 'react';
import Input from './components/Input';
import Button from './components/Button';
import Title from './components/Title';
import Logo from '../../image/logo.png';
import './LoginPage.scss';

function LoginPage() {
    const [inputs, setInputs] = useState({
      id: "",
      password: ""
    });
    
    const {id, password} = inputs;

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

    const tryLogin = async() => {
      console.log(id, password);
      try {
        let response = await fetch(`http://localhost:1415/web/v1/user/Login?studentNumber=${id}&password=${password}`);
        let json = await response.json();
        console.log(json.success);
      } catch (error) {
        console.log(error);
      };
      reset();
    }

    const trySignUp = () => {
      reset();
    }
    
    return (
    <div className="LoginPage">
      <div className="head">
        <div>
          <img src={Logo} classname="logo" alt="logo" width='15%'/>
        </div>
      </div>
      <div className="body">
        <Title/>
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
            onChange={onChange}
            value={password}
            text={"비밀번호"}
            />
        </div>
        <div className="Button">
            <Button onClick={tryLogin} name={"로그인"} value={"login"}/>
            <Button onClick={trySignUp} name={"회원가입"} value={"signUp"}/>
        </div>
      </div>
      <div className="foot"/>
    </div>
    )
}

export default LoginPage
