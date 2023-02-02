import axios from 'axios';

const getSignedUrl = async (basePath, size, contentType) => {
  try {
    const response = await axios.post(
      `/${basePath}/files`,
      {
        size,
        contentType,
      },
      {
        headers: {
          'Content-Type': 'application/json',
        },
      },
    );
    return response.data;
  } catch (err) {
    console.log(err);
    return {};
  }
};

export default getSignedUrl;
