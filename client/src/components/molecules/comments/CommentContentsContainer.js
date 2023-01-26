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
      {comments?.map(el => {
        const { id, writer, content } = el;
        return (
          <Comment key={id} id={writer.id} name={writer.nickname} image={writer.profileImageUrl} content={content} />
        );
      })}
    </Container>
  );
};

export default CommentContentsContainer;
