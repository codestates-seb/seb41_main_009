import styled from 'styled-components';
import SeriesList from '../../molecules/list/SeriesList';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 50px;
`;

const SeriesListContainer = ({ seriesList }) => {
  return (
    <Container>
      {seriesList.map(series => {
        return <SeriesList key={series.id} series={series} />;
      })}
    </Container>
  );
};

export default SeriesListContainer;
