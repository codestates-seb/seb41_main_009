import styled from 'styled-components';
import Comment from './Comment';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

const CommentContentsContainer = ({ comments }) => {
  return (
    <Container>
      {comments?.map()}
      <Comment />
      <Comment />
      <Comment />
      <Comment />
    </Container>
  );
};

export default CommentContentsContainer;
