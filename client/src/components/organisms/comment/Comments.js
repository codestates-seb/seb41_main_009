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

const Comments = ({ id }) => {
  const { getComment } = useCommentAPI();

  const { comments } = getComment(id);

  console.log('comments', comments);
  return (
    <Container>
      <CommentHeader comments={comments} />
      {/* postId -댓글을 제출 할때 어떤 POST에 속해있는지 알려주기 위함 */}
      <CommentInputContainer postId={id} />
      <CommentContentsContainer comments={comments.data} />
    </Container>
  );
};

export default Comments;
