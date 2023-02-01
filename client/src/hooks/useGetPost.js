import axios from 'axios';
import { useState, useEffect } from 'react';

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
      });
  }, []);

  return { post, isLoading, isLoadingError };
};

export default useGetPost;
