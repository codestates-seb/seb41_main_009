import styled from 'styled-components';
import Pagination from '../Pagination';
import Comment from './Comment';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

const NoComment = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  align-items: center;
`;

const CommentContentsContainer = ({ basePath, comments, totalPages }) => {
  return (
    <Container>
      {comments && comments.length > 0
        ? comments.map(el => {
            return <Comment key={el.id} comment={el} basePath={basePath} />;
          })
        : ''}
      {totalPages === 0 ? <NoComment>No Comment Here</NoComment> : <Pagination totalPages={totalPages} />}
    </Container>
  );
};

export default CommentContentsContainer;
