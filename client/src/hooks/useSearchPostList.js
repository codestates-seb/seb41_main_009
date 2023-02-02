import axios from 'axios';
import { useState, useEffect } from 'react';

/**
 *
 * @param {string} category
 * @param {number} page
 * @returns {seriesList[], seriesPageInfo{}, boolean, boolean}
 */
const useSearchPostList = (query, page = 1) => {
  const [postList, setPostList] = useState([]);
  const [postPageInfo, setPostPageInfo] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [isLoadingError, setIsLoadingError] = useState(false);

  const URL = `posts/search?page=${page}&size=10&query=${query}`;

  useEffect(() => {
    setIsLoading(true);

    axios
      .get(URL)
      .then(res => {
        const { data, pageInfo } = res.data;
        setPostList(data);
        setPostPageInfo(pageInfo);
        setIsLoading(false);
      })
      .catch(err => {
        console.log(err);
        setIsLoading(false);
        setIsLoadingError(true);
      });
  }, [query, page]);

  // 아직은 seriesList에 seriesListDummy의 값을 넣음
  return { postList, postPageInfo, isLoading, isLoadingError };
};

export default useSearchPostList;
