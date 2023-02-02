import axios from 'axios';

const uploadNewUserInfo = (newNickname, newDescription, newImage, userId) => {
  const url = `members/${userId}`;
  const body = {
    nickname: newNickname,
    introduction: newDescription,
    profileUrl: newImage,
  };

  axios
    .patch(url, body)
    .then(res => {
      console.log(res);
      window.history.push(`members/${userId}`);
    })
    .catch(err => console.log(err));
};

export default uploadNewUserInfo;
