import Button from '@material-ui/core/Button';
import React from 'react';
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
  }


  handlingSubmit = async (event) => {
    this.props.Change()
    event.preventDefault()
    this.setState({ loading: true });
    let result = await api.crawlUser({ std_num: this.state.std, password: this.state.password, userId : this.props.userID })
    this.setState({ loading: false });
    if (result.data.error) {
      alert(result.data.error)
      
    }
    this.setState({ std: "", password: "" })
    this.props.Change()
  }

  render() {

    return (

        <form onSubmit={this.handlingSubmit} method="POST" className="ccc">

          {this.state.loading ? <TextField
            className="input" margin="normal" disabled
            id="standard-textarea"
            label="학번"
            name="std" variant="outlined"
            placeholder="학번" value={this.state.std} onChange={this.handlingChange}
          /> :
            <TextField
              className="input" margin="normal"
              id="standard-textarea"
              label="학번"
              name="std" variant="outlined"
              placeholder="학번" value={this.state.std} onChange={this.handlingChange}
            />}

          {this.state.loading ?
            <TextField
              className="input" margin="normal" disabled
              id="standard-textarea"
              label="종합정보시스템 비밀번호"
              name="password" variant="outlined"
              placeholder="종정시 비밀번호" value={this.state.password} type="password" onChange={this.handlingChange}
            /> :
            <TextField
              className="input" margin="normal"
              id="standard-textarea"
              label="종합정보시스템 비밀번호"
              name="password" variant="outlined"
              placeholder="종정시 비밀번호" value={this.state.password} type="password" onChange={this.handlingChange}
            />}

          {this.state.loading ? <Spinner /> : <Button startIcon={<CloudUploadIcon />} variant="outlined" size="large" color="primary" type="submit" onSubmit={()=>{if(window.confirm("종합정보시스템 개인정보이용에 동의하십니까?")){this.handlingSubmit()}}} className="crawlingbtn">
            업데이트      </Button>}

        </form>
    )
  }
}




export default crawler;
