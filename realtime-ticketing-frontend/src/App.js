// src/App.js
import React from 'react';
import ConfigurationForm from './components/ConfigurationForm';
import VendorControl from './components/VendorControl';
import CustomerControl from './components/CustomerControl';
import StatusDashboard from './components/StatusDashboard';

const App = () => {
  return (
    <div className="App">
      <h1>Real-Time Event Ticketing System</h1>
      <ConfigurationForm />
      <VendorControl />
      <CustomerControl />
      <StatusDashboard />
    </div>
  );
};

export default App;