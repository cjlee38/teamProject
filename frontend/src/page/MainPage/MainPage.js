import React from 'react';
import { BrowserRouter as Link } from 'react-router-dom';
import Button from './components/Button';
import Title from './components/Title';
import './MainPage.scss';


const Main = ({ match }) => {

    console.log(match.params.id);
    return (

        <div className="body">
            <Title />
            <Link to="/Check">
                <Button name={"졸업요건 확인하기"} value={"check"} />
            </Link>
            <Link to="/Recommend">
                <Button name={"시간표 추천받기"} value={"check"} />
            </Link>
            <Link to="/Login">
                <Button name={"로그인"} value={"check"} />
            </Link>


        </div>
    )
}



export default Main