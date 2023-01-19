import { useEffect, useState } from 'react';
import axios from 'axios';
import { userActivitiesDummy } from '../constants/dummyData';

const useGetUserActivity = (activity, id, page) => {
  const [userContents, setUserContents] = useState([]);
  const [totalPages, setTotalPages] = useState(0);
  const [isLoadingActivity, setIsLoadingActivity] = useState(false);
  const [isLoadingActivityError, setIsLoadingActivityError] = useState(false);

  const url = `members/${id}/${activity}?page=${page}&size=5`;

  useEffect(() => {
    setIsLoadingActivity(true);
    axios
      .get(url)
      .then(({ data, pageInfo }) => {
        setIsLoadingActivity(false);
        setUserContents([...userContents, ...data]);
        setTotalPages(pageInfo);
      })
      .catch(err => {
        console.log(err);
        setIsLoadingActivity(false);
        setIsLoadingActivityError(true);
      })
      .finally(() => {
        setIsLoadingActivity(false);
        setIsLoadingActivityError(false);
        setUserContents([...userContents, ...userActivitiesDummy]);
        setTotalPages(10);
      });
  }, [page]);

  return { userContents, totalPages, isLoadingActivity, isLoadingActivityError };
};

export default useGetUserActivity;
