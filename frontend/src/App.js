import React, {useState} from 'react';
import Todo from './components/Todo'
import './App.css';

function App() {
  const [toDoList, setToDoList] = useState([
    {
      id: 1, 
      title: '베트남어 공부', 
      completed: false
    },
    {
      id: 2, 
      title: '산타토익', 
      completed: false
    },
    {
      id: 3, 
      title: '인강처리', 
      completed: false
    },    
    {
      id: 4, 
      title: '스프링공부', 
      completed: false
    },
    {
      id: 5, 
      title: '리엑트공부', 
      completed: false
    },
  ]);
  return (
    <div className="App">
      <Todo toDoList={toDoList}/>
    </div>
  );
}

export default App;
