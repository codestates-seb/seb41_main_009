import axios from 'axios';

const url = '/api/v1/questions/image';
const host = 'http://HOSTURL';

const uploadImage = async (blob, callback) => {
  const img = blob;
  const base64Image = await new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(img);
    reader.onload = event => resolve(event.target.result);
    reader.onerror = error => reject(error);
  });

  axios
    .post(`${host}${url}`, {
      image: base64Image,
    })
    .then(({ data }) => {
      callback(data.url, 'alt');
    });
  return false;
};

export default uploadImage;
