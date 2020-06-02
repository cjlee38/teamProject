import React from 'react';
import LectureList from './LectureList';
import TimeTable from './Timetable';


class TimeBlock extends React.Component {
  constructor(props) {
    super(props);
    this.handle =  this.props.onClick;
    this.state ={
      color: ""
    }
  }
  onClick1 () {
    console.log("onclick1 ok")
    if (this.state.color === ""){
      this.setState({color:"gray"})}
    else{this.setState({color:""})}
    this.handle()
  }
  render() {
    const { displayLecture } = this.props;
    if (displayLecture) {
      const {
        name,
        professor,
        location,
        isRequired
      } = displayLecture;

      return (
        <td className={isRequired ? 'unit lecture-required' : 'unit lecture-unrequired'} onClick={()=>{this.onClick1(); this.handle();}}>
          <span className="lecture-name">
            {name}
          </span>
          <span className="lecture-info">
            {professor}
            {professor && location ? ' · ' : ''}
            {location}
            {LectureList}
            
          </span>
        </td>
      );
    }

    return (
      <td style={{ backgroundColor: this.state.color}}className="unit" onClick={()=>this.onClick1()}/>
    );
  }
}

export default TimeBlock;
