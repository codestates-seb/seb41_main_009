import styled from 'styled-components';
import PageHeader from '../organisms/PageHeader';
import FilterContainer from '../organisms/listcontents/ListFilter';
import PaginationContainer from '../organisms/listcontents/ListPagination';
import SeriesListContainer from '../organisms/listcontents/SeriesListContainer';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin: 0 auto;
  justify-content: center;
  align-items: center;
`;

const SeriesList = () => {
  return (
    <Container>
      <PageHeader />
      <FilterContainer />
      <SeriesListContainer />
      <PaginationContainer />
    </Container>
  );
};

export default SeriesList;
