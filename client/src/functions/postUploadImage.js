import axios from 'axios';
import getSignedUrl from './getSignedUrl';

const postUploadImage = async (image, callback) => {
  const { size, type } = image;
  const newBlob = new Blob([image], { type });

  const { signedURL, fileURL } = await getSignedUrl('posts', size, type);

  axios
    .put(signedURL, newBlob, {
      headers: {
        'Content-Type': type,
        Authorization: null,
      },
      withCredentials: false,
    })
    .then(() => {
      callback(fileURL, 'alt');
    })
    .catch(err => console.log(err));

  return false;
};

export default postUploadImage;
