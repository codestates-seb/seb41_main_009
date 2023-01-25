import axios from 'axios';
import { useState, useEffect } from 'react';
import { postListDummy } from '../constants/dummyData';
import HOST from '../constants/URL';

/**
 *
 * @param {string} category
 * @param {string} page
 * @returns {postList[], postPageInfo{}, boolean, boolean}
 */
const useGetPostList = (category, page) => {
  const [postList, setPostList] = useState([]);
  const [postPageInfo, setPostPageInfo] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [isLoadingError, setIsLoadingError] = useState(false);

  const URL = `${HOST}/categories/${category}/posts?page=${page}&size=10`;
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
        const { data, pageInfo } = postListDummy;
        setPostList(data);
        setPostPageInfo(pageInfo);
        setIsLoading(false);
        setIsLoadingError(false);
      });
  }, []);

  return { postList, postPageInfo, isLoading, isLoadingError };
};

export default useGetPostList;
