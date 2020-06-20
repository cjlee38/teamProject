import React from 'react';
import Button from '@material-ui/core/Button';
import TimeBlock from './Timeblock';
import Normal from './Normal';
import Axios from 'axios';


class LectureList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      lectureForms: null,
      lectures: null,
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
      displayLectures: {}
    };
  }


  setTable = (lectures, lectureForms) => {
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

   componentDidMount() {
    Axios.get('http://ec2-13-209-184-168.ap-northeast-2.compute.amazonaws.com:1415/web/v1/makeTimeTable/check', {
      params: { userId: this.props.userId }
  }).then((response)=>{
    if (response.data.myCourse.length){
      let lecture = response.data.myCourse
      let arr = Array(lecture.length);
        for (let i = 0; i < lecture.length; i++) {
            arr[i] = i;
        }
        let lectureForms = arr
      this.setState({lectures : lecture, lectureForms:lectureForms})
      this.setTable(lecture, lectureForms)
    }
    else{
      return
    }
    
  }
  ).catch((err)=>{
    alert(err)    
  })
   

  }



  render() {
    console.log(this.state.lectures)
    const {
      weekday,
      timeUnitAlphabet,
      timeUnitString,
      displayLectures,
    } = this.state;
    return (
      <div id="mylecture-list"style={{marginTop:"10% !important"}}>
        <div id="timetable" >
          <table >
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
                          // remove={this.removeLecture}
                          displayLecture={displayLectures[displayLectureKey]}
                          // onhandle={() => this.handleClick(w, t)}
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
