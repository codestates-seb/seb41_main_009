import axios from 'axios';
import { useState, useEffect } from 'react';
import { PostDummy } from '../constants/dummyData';
import HOST from '../constants/URL';

/**
 *
 * @param {string | number} postId
 * @returns {post}
 */
const useGetPost = postId => {
  const [post, setPost] = useState({});

  const URL = `${HOST}/posts/${postId}`;

  useEffect(() => {
    axios
      .get(URL)
      .then(({ data }) => {
        setPost(data);
      })
      .catch(err => console.log(err))
      .finally(() => {
        setPost(PostDummy);
      });
  }, []);

  return post;
};

export default useGetPost;
