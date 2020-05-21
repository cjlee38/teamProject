import React, { Component } from 'react';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';

const qualityType = {

  1: 1,
  2: 2,
  3: 3,
  4: 4
  
};


const areaType = {

  "1전공": "1전공",
  "이중": "이중",
  "부전공": "부전공",
  "교직": "교직",
  
};



export default class AllFilters extends React.Component {

  constructor(props){
    super(props)
    this.lib = ()=>{
      
      let dd = this.props.data.lib
      console.log(this.props.data.lib)
      dd.map(function(obj){ 
        
        areaType[obj.area] = obj.area + "(교양)"
        console.log(obj.area)
      })
    };


  }
  handlerClickCleanFiltered() {
    this.refs.name1.cleanFiltered();
    this.refs.name2.cleanFiltered();
    this.refs.quality.cleanFiltered();
    this.refs.price.cleanFiltered();
    this.refs.satisfaction.cleanFiltered();
    this.refs.inStockDate.cleanFiltered();
  }

  render() {
    console.log(this.lib())
    return (
      <div className= "coursetbl" style={{fontSize:"5pt !important"}}>
        <a onClick={this.handlerClickCleanFiltered.bind(this)} style={{ cursor: 'pointer', color: "orange" }}>clear filters</a>
      <BootstrapTable ref='table' data={this.props.data.data} pagination>
        
        <TableHeaderColumn isKey width="13%" dataAlign='center' ref='name1' dataField='dept' filter={{ type: 'TextFilter', placeholder: 'ELLT학과 or 교양' }}>학과/교양

        </TableHeaderColumn>
        <TableHeaderColumn width="9%" dataSort={true} dataAlign='center' ref='name2' dataField='area' filter={{ type: 'SelectFilter', options: areaType}}>구분</TableHeaderColumn>
        <TableHeaderColumn width="4%" dataAlign='center' ref='quality' dataField='year' filter={{ type: 'SelectFilter', options: qualityType, defaultValue:0,}}>학년</TableHeaderColumn>
        <TableHeaderColumn width="21%" dataAlign='center' ref='price' dataField='subject' filter={{ type: 'TextFilter', placeholder: 'Please enter a value' }}>과목명</TableHeaderColumn>
        <TableHeaderColumn width="4%"dataAlign='center'  ref='satisfaction' dataField='required'>전필</TableHeaderColumn>
        <TableHeaderColumn width="14%" dataAlign='center' ref='inStockDate' dataField='professor' filter={{ type: 'TextFilter', placeholder: 'Please enter a value' }}>담당 교수</TableHeaderColumn>
        <TableHeaderColumn width="4%" dataAlign='center' ref='inStockDate' dataField='credit'>학점</TableHeaderColumn>
        <TableHeaderColumn width="4%" dataAlign='center' ref='inStockDate' dataField='time'>시간</TableHeaderColumn>
        <TableHeaderColumn width="4%" dataAlign='center' ref='inStockDate' dataField='class_time'>강의 시간</TableHeaderColumn>
        <TableHeaderColumn width="4%" dataAlign='center' ref='inStockDate' dataField='number_of_people'>제한 인원</TableHeaderColumn>
        <TableHeaderColumn width="15%" dataAlign='center' ref='inStockDate' dataField='note'>비고</TableHeaderColumn>
        <TableHeaderColumn  dataAlign='center' ref='inStockDate' dataField='select'>선택</TableHeaderColumn>
      </BootstrapTable>
      </div>
    );
  }
}