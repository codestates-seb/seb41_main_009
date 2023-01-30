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
      {comments && comments.length > 0
        ? comments.map(el => {
            return <Comment key={el.id} comment={el} />;
          })
        : ''}
    </Container>
  );
};

export default CommentContentsContainer;
