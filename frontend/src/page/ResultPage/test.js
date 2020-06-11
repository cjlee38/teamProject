import React from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import TimeBlock from './Timeblock';
import Normal from './options/Normal';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import { Link } from 'react-router-dom';

class LectureList extends React.Component {
  constructor(props) {
    super(props);   // this.setTime = this.props.setTime.bind(this)
    // this.setmyCredit = this.props.setmyCredit.bind(this)
    // this.timeList = this.props.timeList
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
      mytime : [],
      displayLectures: {}
    };
  }


  async handleClick(w, t) {

    let text = this.state.weekday[w] + String(this.state.timeUnitAlphabet[t]);
    if (this.state.mytime.includes(text)){
      // await this.setTime(this.props.mytime.filter(val => val !== text))
      await this.setState({mytime:this.state.mytime.filter(val => val !== text)})
    }
    else{
      // await this.setTime(this.props.mytime.concat(text))
      await this.setState({mytime:this.state.mytime.concat(text)})

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

    // TODO: option 값에 따라 인스턴스 생성하도록 개선.
    if (option === 'normal') {
      computedLectures = new Normal(lectureForms,lectures).execute();
    } 
    // this.setTime([...new Set(Object.keys(computedLectures).filter(time => !this.timeList.includes(time)).concat(this.state.mytime))])

    this.setState({
      displayLectures: computedLectures
    });
  }

  componentDidMount() {
    this.setTable()

  }
  render() {
    
    const {
      weekday,
      timeUnitAlphabet,
      timeUnitString,
      displayLectures,
    } = this.state;

    // const {myCourse, mytime, myCredit} = this.props.getData();
    console.log(this.state)
    return (
      <div id="lecture-list">
       
        <div id="timetable">
        <table>
          <thead>
            <tr>
              <th style={{width: "10%"}}>
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
                        displayLecture={displayLectures[displayLectureKey]}
                        onClick={() => this.handleClick(w, t)}
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
