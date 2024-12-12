// src/services/api.js
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
});

export const setupConfiguration = (config) => api.post('/configuration/setup', config);
export const loadConfiguration = () => api.get('/configuration/load');
export const startVendors = () => api.post('/vendor/start');
export const startCustomers = () => api.post('/customer/start');
export const getStatus = () => api.get('/status'); // Ensure backend has this endpoint

export default api;