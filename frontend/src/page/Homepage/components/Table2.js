import React, { Component } from 'react';
import { BootstrapTable, TableHeaderColumn } from
  'react-bootstrap-table';


class Table extends Component {

  Formatter1(cell, row) {
    let a = row.first_major

    return (
      <>{a.map(function(obj) {return <span>{obj}<br/></span>})} </>
    );
  }

  Formatter2(cell, row) {
    let a = row.second_major

    return (
      <>{a.map(function(obj) {return <span>{obj}<br/></span>})} </>
    );
  }
  Formatter3(cell, row) {
    let a = row.minor
    if(a !=='-' ) {return (
      <>{a.map(function(obj) {return <span>{obj}<br/></span>})} </>
    );}else{
      return (
        <></>
      );
    }
  }
  Formatter4(cell, row) {
    let a = row.liberal_arts

    return (
      <>{a.map(function(obj) {return <span>{obj}<br/></span>})} </>
    );
  }
  Formatter5(cell, row) {
    let a = row.trow.teaching

    return (
      <>{a.map(function(obj) {return <span>{obj}<br/></span>})} </>
    );
  }

  Formatter6(cell, row) {
    let a = row.optional
    console.log(a)

    return (
      <>{a.map(function(obj) {return <span>{obj}<br/></span>})} </>
    );
  }


  render() {
    console.log("table:", this.props);
    return (
      <div className="course_t">
        <BootstrapTable data={this.props.data}>
          <TableHeaderColumn 
            isKey
            dataField='trow'
            dataAlign='center'
            headerAlign="left"
            width="40"
            tdStyle={
              { backgroundColor: '' }}
            thStyle={
              { fontWeight: 'heavy' },
              { backgroundColor: '#CCCCCC' }
            }>

          </TableHeaderColumn>
          <TableHeaderColumn 
           dataFormat={this.Formatter1}
            dataField='first_major'
            dataAlign='center'
            headerAlign="center"
            width="15%"
            maxHeight="20%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}>

            1전공
          </TableHeaderColumn>
          <TableHeaderColumn dataField='second_major'
                      maxHeight="20%"
                      dataFormat={this.Formatter2}

            dataAlign='center'
            width="15%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            이중전공
          </TableHeaderColumn>
          <TableHeaderColumn dataField='minor'
                      maxHeight="20%"
                      dataFormat={this.Formatter3}

            dataAlign='center'
            width="15%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            부전공
          </TableHeaderColumn>
     
          <TableHeaderColumn dataField='liberal_arts'
                      maxHeight="20%"
                      dataFormat={this.Formatter4}

            dataAlign='center'
            width="15%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            교양
          
          
          </TableHeaderColumn>
          <TableHeaderColumn dataField='teaching'
                      maxHeight="20%"
                      dataFormat={this.Formatter5}

            dataAlign='center'
            width="15%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            교직
          
          
          </TableHeaderColumn>
          <TableHeaderColumn dataField='optional'
                      maxHeight="20%"
                      dataFormat={this.Formatter6}

            dataAlign='center'
            width="15%"
            thStyle={
              {
                fontWeight: 'heavy',
                backgroundColor: '#CCCCCC'
              }}
            headerAlign="center">
            자선
          
          
          </TableHeaderColumn>

        </BootstrapTable>
      </div>
    )
  }
}


export default Table;