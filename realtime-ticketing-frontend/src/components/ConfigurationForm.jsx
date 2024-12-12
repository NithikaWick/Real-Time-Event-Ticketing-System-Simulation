// src/components/ConfigurationForm.jsx
import React, { useState } from 'react';
import { setupConfiguration } from '../services/api';

const ConfigurationForm = () => {
  const [config, setConfig] = useState({
    eventName: '',
    maxTotalTickets: '',
    ticketPrice: '',
    maximumCapacity: '',
    vendorCount: '',
    totalTicketsPerVendor: '',
    quantity: '',
    ticketReleaseRate: '',
    customerRetrievalRate: '',
  });

  const handleChange = (e) => {
    setConfig({ ...config, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await setupConfiguration(config);
      alert('Configuration saved successfully.');
    } catch (error) {
      console.error(error);
      alert('Failed to save configuration.');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Setup Configuration</h2>
      {/* Repeat for all fields */}
      <input
        type="text"
        name="eventName"
        value={config.eventName}
        onChange={handleChange}
        placeholder="Event Name"
        required
      />
      {/* Add other input fields similarly */}
      <button type="submit">Save Configuration</button>
    </form>
  );
};

export default ConfigurationForm;