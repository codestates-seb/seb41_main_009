import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  width: inherit;
  height: 50px;
  align-items: center;
  justify-content: center;
  background-color: lightblue;
`;

const PaginationContainer = () => {
  return (
    <Container>
      <button type="button">1</button>
      <button type="button">2</button>
      <button type="button">3</button>
      <button type="button">4</button>
      <button type="button">5</button>
    </Container>
  );
};

export default PaginationContainer;
