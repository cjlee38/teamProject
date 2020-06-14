import React from 'react';
import Button from '@material-ui/core/Button';
import TimeBlock from './Timeblock';
import Normal from './options/Normal';
import { Link } from 'react-router-dom';

class LectureList extends React.Component {
  constructor(props) {
    super(props);
   // this.Change = this.props.change.bind(this)
    this.state = {
      lectureForms: this.props.lectureLength,
      lectures: this.props.data,
      option: 'normal',
      weekday: ['월', '화', '수', '목', '금'],
      timeUnitAlphabet: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13],
      timeUnitString: [
        '1교시 9:00',
        '2교시 10:00',
        '3교시 11:00',
        '4교시 12:00',
        '5교시 13:00',
        '6교시 14:00',
        '7교시 15:00',
        '8교시 16:00',
        '9교시 17:00',
        '10교시 18:00',
        '11교시 19:00',
        '12교시 20:00',
        '13교시 21:00'
      ],
      mytime: [],
      deletedLecture: [],
      displayLectures: {}
    };
  }



  async handleClick(w, t) {

    let text = this.state.weekday[w] + String(this.state.timeUnitAlphabet[t]);
    if (this.state.mytime.includes(text)) {
      await this.setState({ mytime: this.state.mytime.filter(val => val !== text) })
    }
    else {
      await this.setState({ mytime: this.state.mytime.concat(text) })

    }

  }


  getWeekdayIndex(target) {
    const { weekday } = this.state;

    for (let i = 0; i < weekday.length; i += 1) {
      if (target === weekday[i]) {
        return i;
      }
    }

    return -1;
  }

  getTimeIndex(target) {
    const { timeUnitAlphabet } = this.state;

    for (let i = 0; i < timeUnitAlphabet.length; i += 1) {
      if (target === timeUnitAlphabet[i]) {
        return i;
      }
    }

    return -1;
  }
  setTable = () => {
    const { lectureForms, lectures } = this.state;
    const { option } = this.state;
    let computedLectures = null;

    this.setState({ displayLectures: {} });

    if (option === 'normal') {
      computedLectures = new Normal(lectureForms, lectures).execute();
    }

    this.setState({
      displayLectures: computedLectures
    });
  }

  removeLecture = (row) => {
    if (row.deleted) {
      this.setState({ lectures: this.state.lectures.filter(inst => inst.instruction_id !== row.instruction_id), deletedLecture: this.state.deletedLecture.concat(row) })
    }
    else {
      this.setState({ lectures: this.state.lectures.concat(row), deletedLecture: this.state.deletedLecture.filter(inst => inst.instruction_id !== row.instruction_id) })

    }
  }

  componentDidMount() {
    this.setTable()

  }

  

  saveTimetable(){

  }


  render() {
    const {
      weekday,
      timeUnitAlphabet,
      timeUnitString,
      displayLectures,
    } = this.state;
    return (
      <div id="lecture-list">
        <Button onClick={this.saveTimetable} variant="contained" size="large" color="primary" style={{ float: 'left',marginBottom:"1%",  backgroundColor:"gray !important"}}> 저장하기</Button>
        <Link onClick={this.props.change} to={{
        pathname:"/Result",
        state: {
          myCourse:this.state.lectures,
          mytime : this.state.mytime,
          myCredit : this.props.credit
        }
      }}>
        <Button variant="contained" color="primary" style={{marginBottom:"1%"}}>현재 조건으로 재생성</Button></Link>
        <div id="timetable">
          <table>
            <thead>
              <tr>
                <th style={{ width: "10%" }}>
                  {'시간'}
                </th>
                {weekday.map((w) => {
                  return (
                    <th key={w}>
                      {w}
                    </th>
                  );
                })}
              </tr>
            </thead>
            <tbody >
              {timeUnitString.map((time, t) => {

                return (
                  <tr key={time} id={time.split('')[0]} className="block" >
                    <td className="block-time" >
                      {time}
                    </td>
                    {Array.from(Array(5).keys()).map((w) => {

                      const displayLectureKey = `${weekday[w]}${timeUnitAlphabet[t]}`;
                      return (

                        <TimeBlock style={this.state.backgroundColor}
                          key={w}
                          remove={this.removeLecture}
                          displayLecture={displayLectures[displayLectureKey]}
                          onhandle={() => this.handleClick(w, t)}
                        />
                      );
                    })}
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
        
      </div>
    );
  }
}

export default LectureList;
