import axios from 'axios';

const useSignUp = (email, nickname, password) => {
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
    })
    .catch(err => {
      console.log(err);
    });
};

export default useSignUp;
