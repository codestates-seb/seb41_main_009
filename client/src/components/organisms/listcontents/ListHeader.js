import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  box-sizing: border-box;
  width: 100%;
  height: 116px;
  align-items: center;
  justify-content: space-between;
  align-items: center;
  padding: 20px 60px;
  gap: 10px;
  border: 5px solid var(--gray-700);
  border-radius: 15px;
  box-shadow: var(--boxShadow-01) var(--gray-700);

  margin: 500px;
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
