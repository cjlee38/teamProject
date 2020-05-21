import React from 'react';
import LectureList from './LectureList';
import TimeTable from './Timetable';


class TimeBlock extends React.Component {
  render() {
    const { displayLecture, onClick } = this.props;
    // const LectureList=this.state.map(
    //   ({option, weekday, timeUnitAlphabet, timeUnitString})=>(
    //     <div 
    //     option={option}
    //     weekday={weekday}
    //     timeUnitAlphabet={timeUnitAlphabet}
    //     timeUnitString={timeUnitString}
    //     onClick={onClick}
    //     />
    //   )
    // );
    if (displayLecture) {
      const {
        name,
        professor,
        location,
        isRequired
      } = displayLecture;

      return (
        <td className={isRequired ? 'unit lecture-required' : 'unit lecture-unrequired'}>
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
      <td className="unit" />
    );
  }
}

export default TimeBlock;
