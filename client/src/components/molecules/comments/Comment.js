import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  width: inherit;
  height: 36px;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
`;

const InfoContainer = styled.div`
  display: flex;
  width: 120px;
  align-items: center;
  justify-content: space-between;
`;

const CommentContainer = styled.div`
  width: 80%;
  padding: 0 24px;
  font-size: var(--paragraph-m);
`;

const CommentButton = styled.button`
  width: ${props => (props.width ? props.width : '51px')};
  height: ${props => (props.height ? props.height : '36px')};
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #ffffff;
  border: none;
  font-size: var(--label-xs);
  color: black;

  &:hover {
    background-color: var(--gray-50);
    cursor: pointer;
  }
`;

const Comment = () => {
  return (
    <Container>
      <InfoContainer>
        <span>이미지</span>
        <span>작성자</span>
      </InfoContainer>
      <CommentContainer>댓글 어쩌구</CommentContainer>
      <InfoContainer>
        <CommentButton type="button">Edit</CommentButton>
        <CommentButton type="button">Delete</CommentButton>
      </InfoContainer>
    </Container>
  );
};

export default Comment;
