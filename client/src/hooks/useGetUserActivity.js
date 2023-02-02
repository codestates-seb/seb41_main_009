import { useEffect, useState } from 'react';
import axios from 'axios';
import getUserActivityUrl from '../functions/getUserActivityUrl';

const useGetUserActivity = (activity, id, page) => {
  const [userContents, setUserContents] = useState([]);
  const [totalPages, setTotalPages] = useState(0);
  const [isLoadingActivity, setIsLoadingActivity] = useState(false);
  const [isLoadingActivityError, setIsLoadingActivityError] = useState(false);

  const url = getUserActivityUrl(id, activity, page);

  useEffect(() => {
    setIsLoadingActivity(true);
    axios
      .get(url)
      .then(res => {
        const { data, pageInfo } = res.data;
        setIsLoadingActivity(false);
        setUserContents([...userContents, ...data]);
        setTotalPages(pageInfo);
      })
      .catch(err => {
        console.log(err);
        setIsLoadingActivity(false);
        setIsLoadingActivityError(true);
      });
  }, [activity, id, page]);

  return { userContents, totalPages, isLoadingActivity, isLoadingActivityError };
};

export default useGetUserActivity;
