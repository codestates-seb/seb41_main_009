import axios from 'axios';
import ImageInstance from '../api/ImageInstance';

const postImage = async image => {
  const imagePath = URL.createObjectURL(image);

  const { fileUrl, contentType } = await ImageInstance.post(`${imagePath}/files`);

  const header = {
    'Content-Type': contentType,
  };

  const gcpInstance = axios.create({
    baseURL: fileUrl,
    headers: header,
  });

  const response = await gcpInstance.put();

  return response;
};

export default postImage;
