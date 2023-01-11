import styled from 'styled-components';
import CommentContentsContainer from '../../molecules/comments/CommentContentsContainer';
import CommentHeader from '../../molecules/comments/CommentHeader';
import CommentInputContainer from '../../molecules/comments/CommentInputContainer';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 83%;
  background-color: blanchedalmond;
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
