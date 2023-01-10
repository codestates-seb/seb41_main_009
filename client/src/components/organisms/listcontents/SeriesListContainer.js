import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 874px;
  height: 255px;
  background-color: lightcoral;
  border: 1px solid black;
  margin-bottom: 30px;
`;

const SeriesListContainer = () => {
  return (
    <Container>
      <div>Series</div>
    </Container>
  );
};

export default SeriesListContainer;
