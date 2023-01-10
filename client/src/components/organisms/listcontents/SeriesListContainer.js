import styled from 'styled-components';
import Series from '../../molecules/Series';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 1056px;
  margin-bottom: 50px;
`;

const SeriesListContainer = () => {
  return (
    <Container>
      <Series />
      <Series />
      <Series />
      <Series />
      <Series />
    </Container>
  );
};

export default SeriesListContainer;
