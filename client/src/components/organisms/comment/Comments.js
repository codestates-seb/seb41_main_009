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
  const { comments, getComment } = useCommentAPI();

  useEffect(() => {
    getComment(basePath, id, {
      page: 1,
      size: 5,
    });
  }, []);

  return (
    <Container>
      <CommentHeader comments={comments?.pageInfo?.totalElements} />
      {/* postId -댓글을 제출 할때 어떤 POST에 속해있는지 알려주기 위함 */}
      <CommentInputContainer postId={id} />
      <CommentContentsContainer comments={comments} basePath={basePath} />
    </Container>
  );
};

export default Comments;
