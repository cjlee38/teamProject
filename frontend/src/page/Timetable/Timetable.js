import React, { Component } from 'react';
import TimetableTemplate from './components/template';
import Form from './components/Form';
import Tableitem from './components/Tableitem';
import Timelist from './components/Timelist'
import { BootstrapTable, TableHeaderColumn } from
  'react-bootstrap-table';

class App extends Component {
  constructor(props) {
    super(props)
    this.table = [{
      id: 'row', text: '1', checked:false, },
      {id: 'row', text: '2', checked:false},
      {id: 'row', text: '3', checked:false},
      {id: 'row', text: '4', checked:false},
      {id: 'row', text: '5', checked:false},
      {id: 'row', text: '6', checked:false},
      {id: 'row', text: '7', checked:false},
      {id: 'row', text: '8', checked:false},
      {id: 'row', text: '9', checked:false},
    ]
  
  

    this.state = {
      input: '',
      todos: [
        { id: 0, text: '요일', checked:false},
        { id: 1, text: ' ', checked: false },
        { id: 2, text: ' ', checked: false },
        { id: 3, text: ' ', checked: false },
        { id: 4, text: ' ', checked: false },
        { id: 5, text: ' ', checked: false },
        { id: 6, text: ' ', checked: false },
        { id: 7, text: ' ', checked: false },
        { id: 8, text: ' ', checked: false },
        { id: 9, text: ' ', checked: false },
     
      ],
    
    todos1: [
      {text:'1교시'},
      {text: '2교시'},
      {text:'3교시'}
     
    ]};
  }
    handleToggle = (id) => {
        const { todos } = this.state;
    
        // 파라미터로 받은 id 를 가지고 몇번째 아이템인지 찾습니다.
        const index = todos.findIndex(todo => todo.id === id);
        const selected = todos[index]; // 선택한 객체
    
        const nextTodos = [...todos]; // 배열을 복사
    
        // 기존의 값들을 복사하고, checked 값을 덮어쓰기
        nextTodos[index] = { 
          ...selected, 
          checked: !selected.checked,
          
         // text: todos.checked? "공강": "공강x"
         text:"  "
          //text: todos.text==="공강"? " - " :"공강"  
          , background: 'gray'
        };
        
        
        this.setState({
          todos: nextTodos
        });
      }
    
    // onClick=(id)=>{
    //   if (id === "row"){
    //     return
    //   }
    //     const { todos } = this.state;
    
    //     // 파라미터로 받은 id 를 가지고 몇번째 아이템인지 찾습니다.
    //     const index = todos.findIndex(todo => todo.id === id);
    //     const selected = todos[index]; // 선택한 객체
    
    //     const nextTodos = [...todos]; // 배열을 복사
    //     nextTodos[index] = { 
    //         ...selected, 
    //         background: 'yellow' 
    //       };
    //     this.setState({background:'yellow'})
    // }

    handleRemove = (id) => {
      const { todos } = this.state;
      this.setState({
        todos: todos.filter(todo => todo.id !== id)
      });
    }

  render() {
    const { input,todos } = this.state;
    // const {
    //   handleChange,
    //   handleCreate,
    //   handleKeyPress
    // } = this;

    return (
  
        
   <TimetableTemplate>
           <div className="whole-table">
           <BootstrapTable data={this.table}>
             <TableHeaderColumn isKey
               dataField='trow'
               dataAlign='center'
               headerAlign="left"
               width="10%"
               tdStyle={
                 { backgroundColor: '' }}
               thStyle={
                 { fontWeight: 'heavy' }
               }>
                 
                 <Timelist todos={this.table} onToggle={this.handleToggle}  
                   
                   />
             </TableHeaderColumn>
             <TableHeaderColumn dataField='first_major'
               dataAlign='center'
               headerAlign="center"
               width="15%"
               column='1'
               thStyle={
                 {
                   fontWeight: 'heavy',
                  
                 }}>
   
               
               <Timelist todos={this.state.todos} onToggle={this.handleToggle}
                   
           />
             </TableHeaderColumn>
             <TableHeaderColumn dataField='second_major'
               dataAlign='center'
               width="15%"
               column='2'
               thStyle={
                 {
                   fontWeight: 'heavy',
                   
                 }}
               headerAlign="center">
              
               <Timelist todos={this.state.todos} onToggle={this.handleToggle}  
                   
           />
             </TableHeaderColumn>
        
             <TableHeaderColumn dataField='liberal_arts'
               dataAlign='center'
               width="15%"
               column1='3'
               thStyle={
                 {
                   fontWeight: 'heavy',
                   
                 }}
               headerAlign="center">
               
               <Timelist column='3' todos={this.state.todos} onToggle={this.handleToggle}  
                    onToggle={()=>this.handleToggle(this.todos.id, '1')}
           />
             </TableHeaderColumn>

             <TableHeaderColumn dataField='second_major'
               dataAlign='center'
               width="15%"
               column='4'
               thStyle={
                 {
                   fontWeight: 'heavy',
                   
                 }}
               headerAlign="center">
              
               <Timelist todos={this.state.todos} onToggle={this.handleToggle}  
                   
           />
             </TableHeaderColumn>

             <TableHeaderColumn dataField='second_major'
               dataAlign='center'
               width="15%"
               column='5'
               thStyle={
                 {
                   fontWeight: 'heavy',
                   
                 }}
               headerAlign="center">
              
               <Timelist todos={this.state.todos} onToggle={this.handleToggle}  
                    
           />
             </TableHeaderColumn>
         
   
           </BootstrapTable>
           
         </div>

      
         </TimetableTemplate>

    );
  }
}

export default App;