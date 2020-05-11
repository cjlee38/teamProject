import React, { Component } from 'react';
import Spinner from 'react-bootstrap/Spinner';
import Button from '@material-ui/core/Button';


export default function Spin () {
    return (
        <Button variant="secondary" disabled>
            <Spinner
              as="span"
              animation="grow"
              size="sm"
              role="status"
              aria-hidden="true"
            />
    Loading..
    <br />약 1분 소요
  </Button>
    );
}