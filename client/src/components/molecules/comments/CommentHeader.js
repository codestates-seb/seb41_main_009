import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  width: 100%;
  height: 30px;
  align-items: center;
  font-size: var(--label-m);
`;

const CommentHeader = ({ comments }) => {
  return <Container>총 댓글 {comments.pageInfo.size}개 </Container>;
};

export default CommentHeader;
