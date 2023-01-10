import styled from 'styled-components';
import CommentHeader from '../../molecules/comments/CommentHeader';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 874px;
  height: 300px;
  background-color: blanchedalmond;
`;

const PostCommentContainer = () => {
  return (
    <Container>
      <CommentHeader />
    </Container>
  );
};

export default PostCommentContainer;
