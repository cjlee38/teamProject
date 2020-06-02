import React, { Component } from 'react';
import Tableitem from './Tableitem';

class TimeList extends Component {
  render() {
    const { todos, onToggle, onRemove } = this.props;

    const LectureList = todos.map(
        ({id, text, checked, background}) => (
          <Tableitem
            id={id}
            text={text}
            checked={checked}
            background={background}
            onToggle={onToggle}
            // onRemove={onRemove}
            key={id}
          />
        )
      );
    return (
      <div>
        {LectureList}  
      </div>
    );
  }
}

export default TimeList;