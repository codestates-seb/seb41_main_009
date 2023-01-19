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
      {comments?.map(el => (
        <Comment
          key={el.id}
          id={el.writer.id}
          name={el.writer.nickname}
          image={el.writer.profileImageUrl}
          content={el.content}
        />
      ))}
    </Container>
  );
};

export default CommentContentsContainer;
