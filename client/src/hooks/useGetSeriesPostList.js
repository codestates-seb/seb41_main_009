import axios from 'axios';
import { useState, useEffect } from 'react';
import { SeriesPostLIstDummy } from '../constants/dummyData';
import HOST from '../constants/URL';

/**
 *
 * @param {string} seriesId post를 포함하고 있는 series의 ID
 * @param {string} page post의 갯수
 * @returns {postList[], postPageInfo{}, boolean, boolean}
 */
const useGetSeriesPostList = (seriesId, page) => {
  const [postList, setPostList] = useState([]);
  const [postPageInfo, setPostPageInfo] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [isLoadingError, setIsLoadingError] = useState(false);

  //   Series API 명 확인필요
  const URL = `${HOST}/categories/${seriesId}/posts?page=${page}&sort=newest'`;
  useEffect(() => {
    setIsLoading(true);

    axios
      .get(URL)
      .then(({ data, pageInfo }) => {
        setPostList(data);
        setPostPageInfo(pageInfo);
        setIsLoading(false);
      })
      .catch(err => {
        console.log(err);
        setIsLoading(false);
        setIsLoadingError(true);
      })
      .finally(() => {
        const { data, pageInfo } = SeriesPostLIstDummy;
        setPostList(data);
        setPostPageInfo(pageInfo);
        setIsLoading(false);
        setIsLoadingError(false);
      });
  }, []);

  return { postList, postPageInfo, isLoading, isLoadingError };
};

export default useGetSeriesPostList;
