import axios from 'axios';

const ImageInstance = axios.create({
  baseURL: 'http://localhost:4000',
});

export default ImageInstance;
