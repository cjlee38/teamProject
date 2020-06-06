import React, {Component} from 'react';
import Store from '../../Store/Store';
import Result from './ResultTabContent';

const RecoContainer = () =>(
    <Store.Consumer>
        {store => (
            <Result userId={store.userId} />
        )}
    </Store.Consumer>
)

export default RecoContainer