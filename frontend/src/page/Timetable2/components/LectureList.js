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
      lectures: this.props.lectures.lectures
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
       

        <Timetable credit={credit} lectureForms={lectureForms} lectures={lectures} />
      </div>
    );
  }
}

export default LectureList;
