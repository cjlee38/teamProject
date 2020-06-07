import React from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import TimeBlock from './TimeBlock';
import Normal from '../options/Normal';
import Kitakubu from '../options/Kitakubu';
import jammanbo from '../options/jammanbo';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import { Link } from 'react-router-dom';
import { ThemeProvider } from '@material-ui/core';

class LectureList extends React.Component {
  constructor(props) {
    super(props);
    this.setTime = this.props.setTime.bind(this)
    this.setmyCredit = this.props.setmyCredit.bind(this)
    this.timeList = this.props.timeList
    this.state = {
      lectureForms: this.props.length1,
      lectures: this.props.lectures,
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

  setCredit = async (e) => {
    const input = e.target.value;
    await this.setmyCredit( parseInt(input));
  }

  setLectureInfo = (e, key, type) => {
    let input = e.target.value;

    if (type === 'isRequired') {
      input = e.target.checked;
    }

    this.setState(prevState => ({
      lectures: {
        ...prevState.lectures,
        [key]: {
          ...prevState.lectures[key],
          [type]: input
        }
      }
    }));
  }


  async handleClick(w, t) {

    let text = this.state.weekday[w] + String(this.state.timeUnitAlphabet[t]);
    if (this.props.mytime.includes(text)){
      await this.setTime(this.props.mytime.filter(val => val !== text))
      await this.setState({mytime:this.state.mytime.filter(val => val !== text)})
    }
    else{
      await this.setTime(this.props.mytime.concat(text))
      await this.setState({mytime:this.state.mytime.concat(text)})

    }
  
  }


  setOption = async (e) => {
    await this.setState({
      option: e.target.value
    });
    this.setTable();
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
      computedLectures = new Normal(lectureForms, lectures.lectures).execute();
    } else if (option === 'kitakubu') {
      computedLectures = new Kitakubu(lectureForms, lectures.lectures).execute();
    }
    else if (option === 'jammanbo') {
      computedLectures = new jammanbo(lectureForms, lectures.lectures).execute();
    }
    this.setTime([...new Set(Object.keys(computedLectures).filter(time => !this.timeList.includes(time)).concat(this.state.mytime))])

    this.setState({
      displayLectures: computedLectures
    });
  }

  componentDidMount() {
    this.setTable()

  }
  render() {
    
    const {
      option,
      weekday,
      timeUnitAlphabet,
      timeUnitString,
      displayLectures,
    } = this.state;

    const {myCourse, mytime, myCredit} = this.props.getData();

    return (
      <div id="lecture-list">
        <TextField id="credit" className="credit-textfield" label="최대학점" onChange={(e) => { this.setCredit(e); }} />
       
        <div id="timetable">
        <div id="timetable-radios">
          <FormControl component="fieldset" required className="option-radio-form">
            <RadioGroup
              aria-label="옵션 설정"
              name="option-radio"
              value={option}
              onChange={this.setOption}
              className="option-radio-form"
            >
              <FormControlLabel value="normal" control={<Radio />} label="일반" />
              <FormControlLabel value="kitakubu" control={<Radio />} label="아침형" />
              <FormControlLabel value="jammanbo" control={<Radio />} label="오후형" />

            </RadioGroup>
          </FormControl>
        </div>
      <Link to={{
        pathname:"/Result",
        state: {
          myCourse:myCourse,
          mytime : mytime,
          myCredit : myCredit
        }
      }}>
        <Button variant="contained" color="primary" >
          {'시간표 생성!'}
        </Button>
        </Link>
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
