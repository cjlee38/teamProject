import React from 'react';
import Button from './components/Button';
import Title from './components/Title';
import Logo from '../../image/logo.png';
import './MainPage.scss';

function MainPage() {
    const goToCheckGraduate = () => {
        console.log("cg")
      }
      const goToMakeTimeTable = () => {
        console.log("mt")
      }
    return (
        <div className="MainPage">
            <img src={Logo} className="logo" alt="logo"/>
            <div className="body">
                <Title/>
                <Button onClick={goToCheckGraduate} name={"졸업요건 확인하기"} value={"check"}/>
                <Button onClick={goToMakeTimeTable} name={"시간표 추천받기"} value={"check"}/>
            </div>
        </div>
    )
}

export default MainPage
