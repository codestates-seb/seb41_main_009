import styled from 'styled-components';
import PageHeader from '../organisms/PageHeader';
import SeriesListContainer from '../organisms/listContainter/SeriesListContainer';
import Lnb from '../organisms/Lnb';
import Pagination from '../molecules/Pagination';
import SearchResultTitle from '../organisms/search/SearchResultTitle';

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
      <SearchResultTitle title="검색결과" amount="120" />

      <Lnb />
      <SeriesListContainer />
      <Pagination totalPages={10} />
    </Container>
  );
};

export default SeriesList;
