import axios from 'axios';

const postSignUpData = (email, nickname, password) => {
  const url = 'members';

  const body = {
    email,
    nickname,
    password,
  };

  axios
    .post(url, body)
    .then(res => {
      console.log(res);
      return res.message;
    })
    .catch(err => {
      console.log(err);
      return err.message;
    })
    .finally(() => {
      console.log('finally');
      return 'success';
    });
};

export default postSignUpData;
