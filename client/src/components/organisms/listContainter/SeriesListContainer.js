import styled from 'styled-components';
import SeriesList from '../../molecules/list/SeriesList';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 1056px;
  margin-bottom: 50px;
`;

const SeriesListContainer = () => {
  return (
    <Container>
      <SeriesList />
      <SeriesList />
      <SeriesList />
      <SeriesList />
      <SeriesList />
    </Container>
  );
};

export default SeriesListContainer;
