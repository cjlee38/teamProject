import React from 'react';
import Button from '@material-ui/core/Button';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import TimeBlock from './TimeBlock';
import Normal from '../options/Normal';
import Kitakubu from '../options/Kitakubu';
import jammanbo from '../options/jammanbo'
import Tablelocation from '../options/tablelocation'
import { useState } from "react";
import styled from "styled-components";



class Timetable extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      backgroundColor: '',

      option: 'normal',
      weekday: ['월', '화', '수', '목', '금', '토'],
      timeUnitAlphabet: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13],
      timeUnitString: [
        '1 9:00 ~ 9:50',
        '2 10:00 ~ 10:50',
        '3 11:00 ~ 11:50',
        '4 12:00 ~ 12:50',
        '5 13:00 ~ 13:50',
        '6 14:00 ~ 14:50',
        '7 15:00 ~ 15:50',
        '8 16:00 ~ 16:50',
        '9 17:00 ~ 17:50',
        '10 18:00 ~ 18:50',
        '11 19:00 ~ 19:50',
        '12 20:00 ~ 20:50',
        '13 21:00 ~ 21:50'
      ],
      mytime : [],
    
      displayLectures: {}
    };
  }



  set1(text){
    const mytime = this.state.mytime
    this.setState(mytime.concat(text))
  }


  async handleClick(w, t) {
    const mytime12 = this.state.mytime
    console.log(mytime12)
    let text = this.state.weekday[w] + String(this.state.timeUnitAlphabet[t]);
    console.log(mytime12.includes(text))
    console.log(this.state.timeUnitAlphabet[t])
    if (this.state.mytime.includes(text)){
      await this.setState({mytime:this.state.mytime.filter(val => val !== text)})
    }
    else{
      await this.setState({mytime: this.state.mytime.concat(text)})

    }
    console.log(this.state.mytime)
  }


  setOption = (e) => {
    this.setState({
      option: e.target.value
    });
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
    const { credit, lectureForms, lectures } = this.props;
    const { option } = this.state;

    let computedLectures = null;

    this.setState({ displayLectures: {} });

    // TODO: option 값에 따라 인스턴스 생성하도록 개선.
    if (option === 'normal') {
      computedLectures = new Normal(lectureForms, lectures).execute();
    } else if (option === 'kitakubu') {
      computedLectures = new Kitakubu(lectureForms, lectures, credit, this.set1).execute();
    }
    else if (option === 'jammanbo') {
      computedLectures = new jammanbo(lectureForms, lectures, credit, this.handleClick).execute();
    }
   
    this.setState({mytime:this.state.mytime.concat(Object.keys(computedLectures))})
    this.setState({
      displayLectures: computedLectures
    });
  }

  render() {
    console.log(this.state.mytime)
    const {
      option,
      weekday,
      timeUnitAlphabet,
      timeUnitString,
      displayLectures
    } = this.state;


    return (

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
              <FormControlLabel value="normal" control={<Radio />} label="일반"/>
              <FormControlLabel value="kitakubu" control={<Radio />} label="아침형" />
              <FormControlLabel value="jammanbo" control={<Radio />} label="오후형" />

            </RadioGroup>
          </FormControl>
        </div>

        <Button variant="contained" color="primary" onClick={this.setTable}>
          {'적용하기!'}
        </Button>

        <table>
          <thead>
            <tr>
              <th>
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
                <tr key={time} id={time.split('')[0]+time.split('')[1]} className="block" >
                  
                  <td className="block-time" >
                    {time}
                  </td>
                  {Array.from(Array(6).keys()).map((w) => {

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
    );
  }
}

export default Timetable;