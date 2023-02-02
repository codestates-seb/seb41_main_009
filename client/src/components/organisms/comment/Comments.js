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
  const { comments, commentCount, getComment } = useCommentAPI();

  useEffect(() => {
    getComment(basePath, id, {
      page: 1,
      size: 5,
    });
  }, []);

  console.log(comments);
  return (
    <Container>
      <CommentHeader commentsCount={commentCount} />
      {/* postId -댓글을 제출 할때 어떤 POST에 속해있는지 알려주기 위함 */}
      <CommentInputContainer basePath={basePath} id={id} />
      <CommentContentsContainer comments={comments} contentId={id} basePath={basePath} />
    </Container>
  );
};

export default Comments;
