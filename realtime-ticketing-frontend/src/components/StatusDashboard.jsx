// src/components/StatusDashboard.jsx
import React, { useEffect, useState } from 'react';
import { getStatus } from '../services/api';
import socket from '../services/socket';

const StatusDashboard = () => {
  const [status, setStatus] = useState(null);

  useEffect(() => {
    // Initial fetch
    fetchStatus();

    // Listen for real-time updates
    socket.on('statusUpdate', (data) => {
      setStatus(data);
    });

    return () => {
      socket.off('statusUpdate');
    };
  }, []);

  const fetchStatus = async () => {
    try {
      const response = await getStatus();
      setStatus(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  if (!status) return <div>Loading status...</div>;

  return (
    <div>
      <h2>System Status</h2>
      <p>Event Name: {status.eventName}</p>
      <p>Total Tickets Released: {status.totalTicketsReleased}</p>
      <p>Total Tickets Sold: {status.totalTicketsSold}</p>
      <p>Tickets Remaining: {status.ticketsRemaining}</p>
      <p>Current Pool Size: {status.currentPoolSize}</p>
    </div>
  );
};

export default StatusDashboard;