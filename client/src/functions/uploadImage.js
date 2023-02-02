import axios from 'axios';
import getSignedUrl from './getSignedUrl';

const uploadImage = async (image, callback) => {
  const { size, type } = image;
  console.log(size, type);

  const { signedURL } = await getSignedUrl('posts', size, type);

  console.log(signedURL);

  const base64Image = await new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(image);
    reader.onload = event => resolve(event.target.result);
    reader.onerror = error => reject(error);
  });

  console.log(base64Image);

  axios
    .put(signedURL, base64Image, {
      headers: {
        'Content-Type': type,
        Authorization: null,
      },
      withCredentials: false,
    })
    .then(({ data }) => {
      console.log(data);
      console.log(data.url);
      callback(data.url, 'alt');
    })
    .catch(err => console.log(err));

  return false;
};

export default uploadImage;
