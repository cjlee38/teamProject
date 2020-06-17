import React, { useState } from 'react';
import { BrowserRouter as Link } from 'react-router-dom';
import Input from '../LoginPage/components/Input';
import Title from '../LoginPage/components/Title';
import Button from '../LoginPage/components/Button';
import Axios from 'axios';

const Signuppage = (props) => {
    const [inputs, setInputs] = useState({
        username: "",
        usernum: "",
        password: ""
    });


    const { username, usernum, password } = inputs;

    const onChange = e => {
        const { value, name } = e;
        setInputs({
            ...inputs,
            [name]: value
        });

    }

    const trySignUp = async (event) => {
        event.preventDefault()
        if (usernum === "" ||username === "" || password ==="") {
            alert("모두 입력해주세요!")
            return
        }
        Axios.post('http://ec2-13-209-184-168.ap-northeast-2.compute.amazonaws.com:1415/web/v1/user/SignUp', {
            "studentNumber": usernum,
            "password": password,
            "name": username
        })
            .then((response) => {
                alert("가입 완료!") 
                props.history.push('/Login')               
            })
            .catch(function (error) {
                alert('이미 존재하는 학번입니다!')
            });
    }

    return (
        <div className="SignupPage">
            <div className="body">
                <Title />
                <form onSubmit={trySignUp}>
                <div className="useridpassword">
                    <Input
                        placeholder={"이름"}
                        onChange={onChange}
                        name={"username"}
                        value={username}
                        text={"이름"}
                    />
                    <Input
                        placeholder={"학번 ex)201912345"}
                        onChange={onChange}
                        name={"usernum"}
                        value={usernum}
                        text={"학번"}
                    />
                    <Input
                        placeholder={"ex)12345678"}
                        onChange={onChange}
                        name={"password"}
                        value={password}
                        type="password"
                        text={"비밀번호"}
                    />
                </div>
                <div className="idPassword">
                <Button onClick={function(){}} name={"회원가입"} value={"signUp"} />
                </div>
                </form>
            </div>
        </div>

    )


}



export default Signuppage