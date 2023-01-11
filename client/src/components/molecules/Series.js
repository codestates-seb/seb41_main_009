import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  width: inherit;
  height: 255px;
  margin-bottom: 30px;
  background-color: lightcoral;
  border: 1px solid black;
`;

const Series = () => {
  return (
    <Container>
      <div>Series</div>
    </Container>
  );
};

export default Series;
