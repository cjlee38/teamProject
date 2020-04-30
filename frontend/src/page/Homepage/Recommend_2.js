import React, {useState} from 'react';
import CreditRange from './components/CreditRange';
import {BrowserRouter as Router, Route, Link} from 'react-router-dom';



function Recommend_2(){ //2:공강시간 설정
    return(
        <div className="select_2">
            <div className="head">
                <div>
                    <img/>
                </div>
            </div>

            <div className="body">
                <Chart/>
                <Button/>
            </div>

            <div className="footer">

            </div>
        </div>
    )
}


export default Recommend_2;
