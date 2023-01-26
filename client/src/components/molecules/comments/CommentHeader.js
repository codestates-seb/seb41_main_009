import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  width: 100%;
  height: 30px;
  align-items: center;
  font-size: var(--label-m);
`;

const CommentHeader = ({ count }) => {
  return <Container>총 댓글 {count}개 </Container>;
};

export default CommentHeader;
