import styled from 'styled-components';
import PageHeader from '../organisms/PageHeader';
import FilterContainer from '../organisms/listcontents/ListFilter';
import PaginationContainer from '../organisms/listcontents/ListPagination';
import SeriesListContainer from '../organisms/listcontents/SeriesListContainer';
import SearchResultTitle from '../organisms/search/SearchResultTitle';
import PostListContainer from '../organisms/listcontents/PostListContainer';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  justify-content: center;
  align-items: center;
`;

const Search = () => {
  return (
    <Container>
      <PageHeader />
      <SearchResultTitle title="검색결과" amount="120" />
      <FilterContainer />
      <SearchResultTitle title="Post" amount="60" />
      <PostListContainer />
      <SearchResultTitle title="Series" amount="60" />
      <SeriesListContainer />
      <PaginationContainer />
    </Container>
  );
};

export default Search;
