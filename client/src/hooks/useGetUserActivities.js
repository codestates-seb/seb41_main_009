import axios from 'axios';
import { useEffect, useState } from 'react';
import { userActivitiesDummy } from '../constants/dummyData';
import HOST from '../constants/URL';
import { ACTIVITIES } from '../constants/UserPageDataLists';

const useGetUserActivities = (id, page = 1) => {
  const [userActivities, setUserActivities] = useState([]);
  const [isLoadingActivities, setIsLoadingActivities] = useState(false);
  const [isLoadingActivitiesError, setIsLoadingActivitiesError] = useState(false);

  const requests = ACTIVITIES.map(activity => {
    return axios.get(`${HOST}/members/${id}/${activity}?page=${page}&size=5`);
  });

  useEffect(() => {
    setIsLoadingActivities(true);
    axios
      .all(requests)
      .then(responses => {
        setIsLoadingActivities(false);
        responses.forEach(({ data }) => {
          setUserActivities([...userActivities, data]);
        });
      })
      .catch(err => {
        console.log(err);
        setIsLoadingActivities(false);
        setIsLoadingActivitiesError(true);
      })
      .finally(() => {
        setIsLoadingActivities(false);
        setIsLoadingActivitiesError(false);
        setUserActivities(userActivitiesDummy);
      });
  }, []);

  return { userActivities, isLoadingActivities, isLoadingActivitiesError };
};

export default useGetUserActivities;
