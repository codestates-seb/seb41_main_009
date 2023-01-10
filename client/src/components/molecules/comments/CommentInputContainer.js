import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  width: inherit;
  height: 60px;
  justify-content: space-between;
  align-items: center;
  background-color: chartreuse;
`;

const CommentInputContainer = () => {
  return (
    <Container>
      <input />
      <button type="button">ADD</button>
    </Container>
  );
};

export default CommentInputContainer;
