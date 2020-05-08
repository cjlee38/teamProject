import OverlayTrigger from 'react-bootstrap/OverlayTrigger';
import Overlay from 'react-bootstrap/Overlay';
import Tooltip from 'react-bootstrap/Tooltip';
import Popover from 'react-bootstrap/Popover';
import PopoverContent from 'react-bootstrap/PopoverContent';
import PopoverTitle from 'react-bootstrap/PopoverTitle';
import Button from '@material-ui/core/Button';  
import React, { Component } from 'react';
import api from '../crawl'
import TextField from '@material-ui/core/TextField';
import CloudUploadIcon from '@material-ui/icons/CloudUpload';




class popover extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            std: '',
            password: '',}
        
    }

    handlingChange = (event) => {
        this.setState({ [event.target.name]: event.target.value })
        console.log(event.target.name, "촘  ")
    }

    handlingSubmit = async (event) => {
        // event.preventDefault()
        console.log("크롤링 시작")
        let result = await api.crawlUser({ std_num: this.state.std, password: this.state.password })
        console.log(result.data)
        this.setState({ std: "", password: "" })
    }

    render () {
        console.log("크롤링 시작")

        return (

        <Popover id="popover-basic">
        <Popover.Title as="h3">Update</Popover.Title>
        <Popover.Content>            
            <div className="Crwaler">
      <form onSubmit={this.handlingSubmit}>


        <TextField
          className="input" margin="normal"
          id="standard-textarea"
          label="학번"
          name="std" variant="outlined"
          placeholder="학번" value={this.state.std} onChange={this.handlingChange}
        />
        <TextField
          className="input" margin="normal"
          id="standard-textarea"
          label="종합정보시스템 비밀번호"
          name="password" variant="outlined"
          placeholder="종정시 비밀번호" value={this.state.password} type="password" onChange={this.handlingChange}
        />
        <Button startIcon={<CloudUploadIcon />} variant="outlined" size="large" color="primary" type="submit" onSubmit={this.handlingSubmit} className="crawlingbtn">
          업데이트      </Button>
      </form>
    </div>
      </Popover.Content>
    </Popover>

    )}


}

function Example (){

    return (
<OverlayTrigger trigger="click" placement="right" children={popover}>
    <Button variant="success">Click me to see</Button>
  </OverlayTrigger>

    );
}

  

  


export default Example
