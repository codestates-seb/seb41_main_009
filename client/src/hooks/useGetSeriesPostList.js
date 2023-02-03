import axios from 'axios';
import { useState, useEffect } from 'react';

/**
 *
 * @param {string} seriesId post를 포함하고 있는 series의 ID
 * @param {string} page post의 갯수
 * @returns {postList[], postPageInfo{}, boolean, boolean}
 */
const useGetSeriesPostList = (seriesId, page = 1) => {
  const [postList, setPostList] = useState([]);
  const [postPageInfo, setPostPageInfo] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [isLoadingError, setIsLoadingError] = useState(false);
  //   Series API 명 확인필요
  const URL = `/series/${seriesId}/posts?page=${page}&size=10&sort=newest`;
  useEffect(() => {
    setIsLoading(true);

    axios
      .get(URL)
      .then(({ data, pageInfo }) => {
        setPostList(data.data);
        setPostPageInfo(pageInfo);
        setIsLoading(false);
      })
      .catch(err => {
        console.log(err);
        setIsLoading(false);
        setIsLoadingError(true);
      });
  }, [seriesId]);

  return { postList, postPageInfo, isLoading, isLoadingError };
};

export default useGetSeriesPostList;
