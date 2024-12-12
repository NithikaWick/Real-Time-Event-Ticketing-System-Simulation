// src/components/VendorControl.jsx
import React from 'react';
import { startVendors } from '../services/api';

const VendorControl = () => {
  const handleStart = async () => {
    try {
      await startVendors();
      alert('Vendors started releasing tickets.');
    } catch (error) {
      console.error(error);
      alert('Failed to start vendors.');
    }
  };

  return (
    <div>
      <h2>Vendor Control</h2>
      <button onClick={handleStart}>Start Vendors</button>
    </div>
  );
};

export default VendorControl;