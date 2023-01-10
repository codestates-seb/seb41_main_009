import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  width: inherit;
  height: 116px;
  background-color: aqua;
  align-items: center;
  justify-content: space-around;
`;

const ListHeaderContainer = () => {
  return (
    <Container>
      <h1>Post In Category</h1>
      <button type="button">Create Post</button>
    </Container>
  );
};

export default ListHeaderContainer;
