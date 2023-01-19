import axios from 'axios';
import { useState, useEffect } from 'react';
import { PostDummy } from '../constants/dummyData';
import HOST from '../constants/URL';

/**
 *
 * @param {string | number} postId
 * @returns {{}, boolean, boolean}
 */
const useGetPost = ({ postId }) => {
  const [post, setPost] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [isLoadingError, setIsLoadingError] = useState(false);

  const URL = `${HOST}/posts/${postId}`;

  useEffect(() => {
    setIsLoading(true);

    axios
      .get(URL)
      .then(({ data }) => {
        setPost(data);
        setIsLoading(false);
      })
      .catch(err => {
        console.log(err);
        setIsLoading(false);
        setIsLoadingError(true);
      })
      .finally(() => {
        // 현재는 더미데이터에서 가져옴
        setPost(PostDummy);
        setIsLoading(false);
        setIsLoadingError(false);
      });
  }, []);

  return { post, isLoading, isLoadingError };
};

export default useGetPost;
