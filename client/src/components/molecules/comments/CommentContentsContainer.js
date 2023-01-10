import styled from 'styled-components';
import Comment from './Comment';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: inherit;
  background-color: darkmagenta;
`;

const CommentContentsContainer = () => {
  return (
    <Container>
      <Comment />
      <Comment />
      <Comment />
      <Comment />
    </Container>
  );
};

export default CommentContentsContainer;
