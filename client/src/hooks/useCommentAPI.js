import axios from 'axios';
import { useState } from 'react';
import { COMMENT_DUMMY2 } from '../constants/dummyData';

const useCommentAPI = () => {
  const [comments, setcomments] = useState(COMMENT_DUMMY2);
  const [commentCount, setCommentCount] = useState(0);
  const [isLoading, setIsLoading] = useState(false);
  const [isLoadingError, setIsLoadingError] = useState(false);

  /**
   * 댓글을 가져올때 사용
   * @param {string | number} postId
   * @returns {post{}, boolean, boolean}
   */
  const getComment = (basePath, id, params) => {
    const url = `/${basePath}/${id}/comments`;
    axios
      .get(url, {
        params,
      })
      .then(res => {
        setcomments(res.data.data);
        setCommentCount(res.data.pageInfo.totalElements);
      })
      .finally(() => {
        setIsLoading(false);
        setIsLoadingError(false);
      });
  };

  /**
   * 댓글을 업로드 할 때 사용
   * @param {string | number} postId : comment가 종속되어 있는 글의 아이디
   * @param {string} content : 댓글 내용
   * @returns {post{}, boolean, boolean}
   */
  const postComment = (basePath, id, content) => {
    const url = `/${basePath}/comments/${id}`;

    axios
      .post(
        url,
        {
          content,
        },
        {
          'Content-Type': 'application/json',
        },
      )
      .then(() => {
        setIsLoading(false);
      })
      .catch(() => {
        setIsLoading(false);
        setIsLoadingError(true);
      })
      .finally(() => {
        setIsLoading(false);
        setIsLoadingError(false);
      });
  };
  /**
   * 댓글 내용을 수정할 때 사용
   * @param {string | number} postId : comment가 종속되어 있는 글의 아이디
   * @param {string} content : 댓글 내용
   * @returns {post{}, boolean, boolean}
   */
  const patchComment = (basePath, contentId, id, content) => {
    const url = `/${basePath}/${contentId}/comments/${id}`;

    axios
      .patch(
        url,
        {
          content,
        },
        {
          'Content-Type': 'application/json',
        },
      )
      .then(() => {
        setIsLoading(false);
      })
      .catch(err => {
        console.log(err);
        setIsLoading(false);
        setIsLoadingError(true);
      })
      .finally(() => {
        // 현재는 더미데이터에서 가져옴
        setIsLoading(false);
        setIsLoadingError(false);
      });
  };

  /**
   * 댓글을 삭제할 때 사용 사용
   * @param {string | number} postId
   * @returns {post{}, boolean, boolean}
   */
  const deleteComment = (basePath, contentId, id) => {
    const url = `/${basePath}/${contentId}/comments/${id}`;
    axios.delete(url).catch(err => console.log(err));
  };

  return { comments, commentCount, isLoading, isLoadingError, getComment, postComment, patchComment, deleteComment };
};

export default useCommentAPI;
