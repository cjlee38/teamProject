import React from 'react';
import Store from '../../Store/Store';
import Check from './Check';

const CheckContainer = () =>(
    <Store.Consumer>
        {store => (
            <Check userId={store.userId} />
        )}
    </Store.Consumer>
)

export default CheckContainer