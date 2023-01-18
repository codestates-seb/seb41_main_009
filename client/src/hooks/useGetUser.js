import axios from 'axios';
import { useEffect, useState } from 'react';
import HOST from '../constants/URL';

/**
 *
 * @param {string | number} id
 * @returns {userInfo{}, boolean, boolean}
 */
const useGetUser = id => {
  const [userInfo, setUserInfo] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [isLoadingError, setIsLoadingError] = useState(false);
  const url = `${HOST}/members/${id}`;

  useEffect(() => {
    setIsLoading(true);

    axios
      .get(url)
      .then(({ data }) => {
        setUserInfo(data);
        setIsLoading(false);
      })
      .catch(err => {
        console.log(err);
        setIsLoading(false);
        setIsLoadingError(true);
      })
      .finally(() => {
        setIsLoading(false);
        setIsLoadingError(false);
        setUserInfo();
      });
  });

  return { userInfo, isLoading, isLoadingError };
};

export default useGetUser;
