import React, {useState} from 'react'
import Input from '../../components/Input'
import Button from '../../components/Button'
import Title from './Title'

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
        <Title/>
        <div className="id,Password">
          <Input 
            placeholder={"학번"}
            name={"id"}
            onChange={onChange}
            value={id}
            />
          <Input 
            placeholder={"비번"}
            name={"password"}
            onChange={onChange}
            value={password}
            />
        </div>
        <div className="Button">
            <Button onClick={tryLogin} name={"로그인"}/>
            <Button onClick={trySignUp} name={"회원가입"}/>
        </div>
      </div>
    </div>
    )
}

export default LoginPage
