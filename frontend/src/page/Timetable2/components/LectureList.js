import React from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Timetable from './Timetable';

class LectureList extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      credit: 0,
      lectureForms: this.props.length1,
      lectures: this.props.lectures
    };
  }

  setCredit = (e) => {
    const input = e.target.value;
    this.setState({ credit: input });
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



  render() {
    console.log(this.state)
    const { credit, lectureForms, lectures } = this.state;

    return (
      <div id="lecture-list">
        <TextField id="credit" className="credit-textfield" label="신청학점" onChange={(e) => { this.setCredit(e); }} />
        {/* <ul>
          {lectureForms.map((lec) => {
            const nameId = `lecture-name-${lec}`;
            const professorId = `lecture-professor-${lec}`;
            const locationId = `lecture-location-${lec}`;
            const timeId = `lecture-time-${lec}`;

            return (
              <li key={lec}>
                <TextField id={nameId} className="lecture-textfield" label="과목명" value={lectures[lec].name} onChange={(e) => { this.setLectureInfo(e, lec, 'name'); }} />
                <TextField id={professorId} className="lecture-textfield" label="교수명" value={lectures[lec].professor} onChange={(e) => { this.setLectureInfo(e, lec, 'professor'); }} />
                <TextField id={locationId} className="lecture-textfield" label="강의실" value={lectures[lec].location} onChange={(e) => { this.setLectureInfo(e, lec, 'location'); }} />
                <TextField id={timeId} className="lecture-textfield" label="시간" value={lectures[lec].time} onChange={(e) => { this.setLectureInfo(e, lec, 'time'); }} />
                <FormControlLabel
                  className="lecture-checkbox"
                  control={
                    <Checkbox onChange={(e) => { this.setLectureInfo(e, lec, 'isRequired'); }} color="primary" />
                  }
                  label="필수"
                />
              </li>
            );
          })}
        </ul> */}

        <Timetable credit={credit} lectureForms={lectureForms} lectures={lectures} />
      </div>
    );
  }
}

export default LectureList;
