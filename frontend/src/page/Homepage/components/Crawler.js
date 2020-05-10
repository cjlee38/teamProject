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
import Spinner from './Spinner'



class crawler extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      std: '',
      password: '',
      loading: false,
    }

  }

  handlingChange = (event) => {
    this.setState({ [event.target.name]: event.target.value, loading: false })
    console.log(event.target.name, "change  ")
  } 

  handlingSubmit = (event) => {
    console.log("크롤링 시작")
    event.preventDefault()

    this.setState({ loading: true }, (event) => {
      console.log(this.state.std, this.state.password)

      api.crawlUser({ std_num: this.state.std, password: this.state.password })
        .then(result => this.setState({
          loading: false,
          data: [...result.data],
        }))
        .catch(function (error) {alert(error)});
    });

    // let result = await 
    console.log(this.data)
    this.setState({ std: "", password: "" })
  }

  render() {
    const { loading } = async () => {await this.state.loading}
    console.log(loading)

    return (

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


          {loading ? <Spinner /> : <Button startIcon={<CloudUploadIcon />} variant="outlined" size="large" color="primary" type="submit" onSubmit={this.handlingSubmit} className="crawlingbtn">
              업데이트      </Button>}

        </form>
      </div>
    )
  }
}




export default crawler;
