import React from 'react';
import DateTime from 'react-datetime';
import "react-datetime/css/react-datetime.css";

const DatePicker = ({ name, selectedDate, handleDateChange }) => {
  return (
    <DateTime 
    dateFormat="yyyy-MM-DD"
    timeFormat={false}
    value={selectedDate}
    onChange={date => handleDateChange(name, date)}
    inputProps={{ placeholder: 'Selecione a data' }}
    />
  );
};

export default DatePicker;
