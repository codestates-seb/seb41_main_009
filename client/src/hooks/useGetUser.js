import axios from 'axios';
import { useEffect, useState } from 'react';
// import { userInfoDummy } from '../constants/dummyData';

/**
 *
 * @param {string | number} id
 * @returns {userInfo{}, boolean, boolean}
 */
const useGetUser = id => {
  const [userInfo, setUserInfo] = useState({});
  const [isLoadingUser, setIsLoading] = useState(false);
  const [isLoadingUserError, setIsLoadingUserError] = useState(false);

  const url = `members/${id}`;

  useEffect(() => {
    setIsLoading(true);

    axios
      .get(url)
      .then(data => {
        setUserInfo(data.data);
        setIsLoading(false);
      })
      .catch(err => {
        console.log(err);
        setIsLoading(false);
        setIsLoadingUserError(true);
      });
  }, [id]);

  return { userInfo, isLoadingUser, isLoadingUserError };
};

export default useGetUser;
