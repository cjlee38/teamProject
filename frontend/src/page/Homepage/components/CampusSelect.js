import React, { Component } from 'react'

class Campus extends React.Component {
  
    setCampus(event) {
      console.log(event.target.value);
    }
    
    render() {
      return ( 
        <div onChange={this.setCampus.bind(this)}>
          <input type="radio" value="Seoul" name="campus"/> 서울(Seoul)
          <input type="radio" value="Global" name="campus"/> 글로벌(Global)
        </div>
       )
    }
  }

  export default Campus;