import { useEffect } from 'react';

import styled from 'styled-components';

import useCommentAPI from '../../../hooks/useCommentAPI';
import CommentContentsContainer from '../../molecules/comments/CommentContentsContainer';
import CommentHeader from '../../molecules/comments/CommentHeader';
import CommentInputContainer from '../../molecules/comments/CommentInputContainer';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
  padding: 22px 92px;
  width: 100%;
`;

const Comments = ({ basePath, id }) => {
  const { comments, commentCount, totalPages, getComment } = useCommentAPI();
  const commentReloading = () => {
    getComment(basePath, id, {
      page: 1,
      size: 5,
    });
  };

  const refreshComment = page => {
    getComment(basePath, id, {
      page,
      size: 5,
    });
  };

  useEffect(() => {
    commentReloading();
  }, []);

  return (
    <Container>
      <CommentHeader commentsCount={commentCount} />
      {/* postId -댓글을 제출 할때 어떤 POST에 속해있는지 알려주기 위함 */}
      <CommentInputContainer basePath={basePath} id={id} callback={commentReloading} />
      <CommentContentsContainer
        totalPages={totalPages}
        comments={comments}
        contentId={id}
        basePath={basePath}
        callback={commentReloading}
        refreshComment={refreshComment}
      />
    </Container>
  );
};

export default Comments;
