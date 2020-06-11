import React from 'react';
import Store from '../../Store/Store';
import Result from './ResultTabContent';

const RecoContainer = (props) =>(
    <Store.Consumer>
        {store => (
            <Result userId={store.userId} data={props.location} />
        )}
    </Store.Consumer>
)

export default RecoContainer