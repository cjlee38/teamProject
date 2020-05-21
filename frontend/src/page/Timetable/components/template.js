import React from 'react';
import './template.css';

const TimetableTemplate = ({form, children}) => {
  return (
    <main className="todo-list-template">
      <div className="title">
        Timetable
      </div>
      <section className="form-wrapper">
        {form}
      </section>
      <section className="todos-wrapper">
        { children }
      </section>
    </main>
  );
};

export default TimetableTemplate;