import React from 'react';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Title from '../../LoginPage/components/Title'
//import Header from '../../LoginPage/Layout/Header'

function Navigation() {
  return (
    <div className="navigation">
      <AppBar position="fixed" color="Navy">
        <br/>
      {/* <Title style={{color: 'navy'}, {fontsize:'20'}} /> */}
      <h1 style={{color: 'navy'}, {textAlign: 'center'}, {fontSize: 50}}>HUFS SCHEDULER</h1>
      
             
      </AppBar>
    </div>
  );
}

export default Navigation;
