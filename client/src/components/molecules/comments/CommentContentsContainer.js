import styled from 'styled-components';
import Pagination from '../Pagination';
import Comment from './Comment';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

const CommentContentsContainer = ({ basePath, contentId, comments, callback }) => {
const NoComment = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  align-items: center;
`;

const CommentContentsContainer = ({ basePath, comments, contentId, totalPages }) => {
  console.log(totalPages, 'totalPages in CommentContentsContainer');
  return (
    <Container>
      {comments && comments.length > 0
        ? comments.map(el => {
            return <Comment key={el.id} contentId={contentId} comment={el} basePath={basePath} callback={callback} />;
          })
        : ''}
      {totalPages === 0 ? <NoComment>No Comment Here</NoComment> : <Pagination totalPages={totalPages || 1} />}
    </Container>
  );
};

export default CommentContentsContainer;
