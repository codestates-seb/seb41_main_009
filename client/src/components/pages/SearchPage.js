import styled from 'styled-components';
import { useSearchParams } from 'react-router-dom';

import PageHeader from '../organisms/PageHeader';
import Lnb from '../organisms/Lnb';
import SeriesListContainer from '../organisms/listContainter/SeriesListContainer';
import SearchResultTitle from '../organisms/search/SearchResultTitle';
import PostListContainer from '../organisms/listContainter/PostListContainer';
import useGetSeriesList from '../../hooks/useGetSeriesList';
import useGetPostList from '../../hooks/useGetPostList';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  justify-content: center;
  align-items: center;
  gap: 30px;
`;

// 조건 문을 통해서 하나가 켜지면 다른게 꺼지는 형태가 되도록 조건문을 작성해야함
const Search = () => {
  const [searchParams] = useSearchParams();
  const curPage = searchParams.get('page');

  const value = searchParams.get('value');

  const { seriesList } = useGetSeriesList(value, curPage);
  const { postList } = useGetPostList(value, curPage);

  return (
    <Container>
      <PageHeader headerSubTitle="Results for Keyword" headerTitle={`Search results ${value}`} />
      <SearchResultTitle title="검색결과" amount="120" />
      <Lnb />
      <SearchResultTitle title="Post" amount="60" />
      <PostListContainer postList={postList} />
      <SearchResultTitle title="Series" amount="60" />
      <SeriesListContainer seriesList={seriesList} />
    </Container>
  );
};

export default Search;
