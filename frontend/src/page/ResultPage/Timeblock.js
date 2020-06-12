import React from 'react';


class TimeBlock extends React.Component {
  constructor(props) {
    super(props);
    this.handle =  this.props.onhandle;

    this.state ={
      color: "",
      opacity:"",
      opacity2:"0.5",
      color2: "#FFB0CF",
      color3 : "#A4C3FF",
      isdeleted: false,
    }
  }
  async onClick1  () {
    if (this.state.color === ""){
      await this.setState({color:"#FFB0CF", opacity:"0.5"})}
    else{this.setState({color:"", opacity:""})}
    await this.handle()
  }
  async onClick2 (name) {
    this.props.displayLecture.deleted = !this.props.displayLecture.deleted
    this.props.remove(this.props.displayLecture)
  } 

  render() {

    const { displayLecture } = this.props;
    if (displayLecture) {
      const {
        subject,
        professor,
        location,
        isRequired,
        url,
        deleted
      } = displayLecture;
      return (
        <td style={deleted? {backgroundColor:"gray"}:isRequired ? { backgroundColor: '#FF5675' } : subject ? { backgroundColor: this.state.color3} : { backgroundColor: this.state.color2, opacity:this.state.opacity2}}  >
          <a href={url} target="_blank"><span className="lecture-name">
            {subject}
          </span>
          <span className="lecture-info">
            {professor}
            {professor && location ? ' · ' : ''}
            
          </span></a>
          <button onClick={()=>{this.onClick2(subject);}}>{deleted?'복구':'삭제'}</button>
        </td>
      );
    }

    return (
      <td style={{ backgroundColor: this.state.color, opacity: this.state.opacity}}className="unit" onClick={()=>this.onClick1()}/>
    );
  }
}

export default TimeBlock;
