import styled from 'styled-components';
import Comment from './Comment';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

const CommentContentsContainer = ({ basePath, contentId, comments, callback }) => {
  return (
    <Container>
      {comments && comments.length > 0
        ? comments.map(el => {
            return <Comment key={el.id} contentId={contentId} comment={el} basePath={basePath} callback={callback} />;
          })
        : ''}
    </Container>
  );
};

export default CommentContentsContainer;
