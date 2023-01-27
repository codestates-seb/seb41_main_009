import axios from 'axios';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { COMMENT_DUMMY2 } from '../constants/dummyData';

const useCommentAPI = () => {
  const [comments, setcomments] = useState(COMMENT_DUMMY2);
  const [isLoading, setIsLoading] = useState(false);
  const [isLoadingError, setIsLoadingError] = useState(false);

  /**
   * 댓글을 가져올때 사용
   * @param {string | number} postId
   * @returns {post{}, boolean, boolean}
   */
  const getComment = ({ id }) => {
    const url = `posts/${id}/comments`;

    axios
      .get(url)
      .then(() => {
        setcomments(COMMENT_DUMMY2);
      })

      .finally(() => {
        // 현재는 더미데이터에서 가져옴
        setcomments(COMMENT_DUMMY2);
        setIsLoading(false);
        setIsLoadingError(false);
      });
    return { comments, isLoading, isLoadingError };
  };

  /**
   * 댓글을 업로드 할 때 사용
   * @param {string | number} postId : comment가 종속되어 있는 글의 아이디
   * @param {string} content : 댓글 내용
   * @returns {post{}, boolean, boolean}
   */
  const postComment = ({ id, content }) => {
    const url = `posts/${id}/comments`;

    axios
      .post(url, {
        content,
      })
      .then(({ data }) => {
        setcomments(data);
        setIsLoading(false);
      })
      .catch(err => {
        console.log(err);
        setIsLoading(false);
        setIsLoadingError(true);
      })
      .finally(() => {
        // 현재는 더미데이터에서 가져옴
        setcomments(COMMENT_DUMMY2);
        setIsLoading(false);
        setIsLoadingError(false);
        console.log('Comment');
      });
    return { comments, isLoading, isLoadingError };
  };
  /**
   * 댓글 내용을 수정할 때 사용
   * @param {string | number} postId : comment가 종속되어 있는 글의 아이디
   * @param {string} content : 댓글 내용
   * @returns {post{}, boolean, boolean}
   */
  const patchComment = ({ id, content }) => {
    const url = `posts/${id}/comments`;

    axios
      .patch(url, {
        content,
      })
      .then(({ data }) => {
        setcomments(data);
        setIsLoading(false);
      })
      .catch(err => {
        console.log(err);
        setIsLoading(false);
        setIsLoadingError(true);
      })
      .finally(() => {
        // 현재는 더미데이터에서 가져옴
        setcomments(COMMENT_DUMMY2);
        setIsLoading(false);
        setIsLoadingError(false);
      });
    return { comments, isLoading, isLoadingError };
  };

  /**
   * 댓글을 삭제할 때 사용 사용
   * @param {string | number} postId
   * @returns {post{}, boolean, boolean}
   */
  const deleteComment = ({ id }) => {
    const navigate = useNavigate();
    const url = `posts/${id}/comments`;
    axios
      .delete(url, {
        headers: {
          Authorization: '인증수단필요',
        },
      })
      .then(res => {
        console.log(res);
        navigate('/questions');
      })
      .catch(err => console.log(err));
    return { comments, isLoading, isLoadingError };
  };

  return { getComment, postComment, patchComment, deleteComment };
};

export default useCommentAPI;
