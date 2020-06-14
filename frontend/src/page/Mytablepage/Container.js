import React from 'react';
import Store from '../../Store/Store';
import Mytable from './mytable';

const RecoContainer = (props) =>(
    <Store.Consumer>
        {store => (
            <Mytable userId={store.userId} data={props.location} />
        )}
    </Store.Consumer>
)

export default RecoContainer