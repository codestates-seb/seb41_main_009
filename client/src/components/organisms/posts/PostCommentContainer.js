import styled from 'styled-components';
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

const PostCommentContainer = () => {
  return (
    <Container>
      <CommentHeader />
      <CommentInputContainer />
      <CommentContentsContainer />
    </Container>
  );
};

export default PostCommentContainer;
