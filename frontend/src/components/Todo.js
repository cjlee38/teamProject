import React from 'react';
import ToDoItemList from './ToDoItemList'

function Todo(props) {
    return (
        props.toDoList.map(todo => (
            <ToDoItemList />
        ))
    );
}

export default Todo;
