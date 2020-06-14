import React, { Component } from 'react';
import { BootstrapTable, TableHeaderColumn } from
  'react-bootstrap-table';


class Table extends Component {
  render() {
    // console.log("?", this.props);
    return (
      <div className="table4">
        <BootstrapTable data={this.props.data}>
          <TableHeaderColumn isKey
            dataField='trow'
            dataAlign='center'
            headerAlign="center"
            width="5%"
            tdStyle={
              { backgroundColor: '' ,textAlign:'center !important'}}
            thStyle={
              { fontWeight: 'heavy' },
              { backgroundColor: '#CCCCCC' }
            }>
            전공 정보 (1전공 / 이중전공 / 부전공)

          </TableHeaderColumn>

        </BootstrapTable>
      </div>
    )
  }
}


export default Table;