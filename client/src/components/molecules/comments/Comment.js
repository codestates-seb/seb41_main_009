import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  width: inherit;
  height: 36px;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
  background-color: floralwhite;
`;

const Comment = () => {
  return (
    <Container>
      <div>작성자</div>
      <div>댓글 어쩌구</div>
      <div>
        <button type="button">Edit</button>
        <button type="button">Delete</button>
      </div>
    </Container>
  );
};

export default Comment;
