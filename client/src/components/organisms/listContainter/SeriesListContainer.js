import styled from 'styled-components';
import SeriesCard from '../../molecules/list/SeriesCard';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 50px;
`;

// seriesList에 seriesListDummy.data
const SeriesListContainer = ({ seriesList }) => {
  return (
    <Container>
      {seriesList.map(series => {
        return <SeriesCard key={series.id} series={series} />;
      })}
    </Container>
  );
};

export default SeriesListContainer;
