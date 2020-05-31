import React, {Component} from 'react';
import Store from '../../Store/Store';
import Reco from './RecoAll';

const RecoContainer = () =>(
    <Store.Consumer>
        {store => (
            <Reco userId={store.userId} />
        )}
    </Store.Consumer>
)

export default RecoContainer