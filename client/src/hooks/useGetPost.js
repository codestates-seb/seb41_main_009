import axios from 'axios';
import { useState, useEffect } from 'react';
import { PostDummy } from '../constants/dummyData';

/**
 *
 * @param {string | number} postId
 * @returns {post{}, boolean, boolean}
 */
const useGetPost = ({ id }) => {
  const [post, setPost] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [isLoadingError, setIsLoadingError] = useState(false);

  const url = `posts/${id}`;

  useEffect(() => {
    setIsLoading(true);

    axios
      .get(url)
      .then(({ data }) => {
        console.log(data);
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
        setPost(PostDummy.data);
        setIsLoading(false);
        setIsLoadingError(false);
      });
  }, []);

  return { post, isLoading, isLoadingError };
};

export default useGetPost;
