// src/components/CustomerControl.jsx
import React from 'react';
import { startCustomers } from '../services/api';

const CustomerControl = () => {
  const handleStart = async () => {
    try {
      await startCustomers();
      alert('Customers started purchasing tickets.');
    } catch (error) {
      console.error(error);
      alert('Failed to start customers.');
    }
  };

  return (
    <div>
      <h2>Customer Control</h2>
      <button onClick={handleStart}>Start Customers</button>
    </div>
  );
};

export default CustomerControl;