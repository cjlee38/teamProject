import React from 'react';
import {BrowserRouter as Router, Route, Link} from 'react-router-dom';
import Button from '../LoginPage/components/Button';

function SchedulePage(){
    return(
        <div className="SchedulePage">
            <div className="head">
                <div>
                    <img/>
                </div>
            </div>

            <div className="body">
                <Chart/>
                <Instruction/>
                <Button/>
            </div>

            <div className="footer">
            </div>
        </div>
    )
}

export default SchedulePage;