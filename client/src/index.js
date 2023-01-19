import { StrictMode } from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import axios from 'axios';
import App from './App';

axios.defaults.baseURL = 'http://localhost:1000';
axios.defaults.headers.common.Authorization = 'AUTH_TOKEN';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <StrictMode>
    <App />
  </StrictMode>,
);
