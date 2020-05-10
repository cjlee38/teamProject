import React, { Component } from 'react';
import Spinner from 'react-bootstrap/Spinner';
import Button from '@material-ui/core/Button';


export default function Spin () {
    return (
        <Button variant="primary" disabled>
            <Spinner
              as="span"
              animation="grow"
              size="sm"
              role="status"
              aria-hidden="true"
            />
    Loading...
  </Button>
    );
}