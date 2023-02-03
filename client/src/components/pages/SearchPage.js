import styled from 'styled-components';
import { useParams, useSearchParams } from 'react-router-dom';

import PageHeader from '../organisms/PageHeader';
import { LnbSearch } from '../organisms/Lnb';
import { TextButton } from '../atoms/Buttons';
import Pagination from '../molecules/Pagination';
import SeriesListContainer from '../organisms/listContainter/SeriesListContainer';
import SearchResultTitle from '../organisms/search/SearchResultTitle';
import PostListContainer from '../organisms/listContainter/PostListContainer';
import useSearchSeriesList from '../../hooks/useSearchSeriesList';
import useSearchPostList from '../../hooks/useSearchPostList';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  justify-content: center;
  align-items: center;
  gap: 30px;
`;

const ResultContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;

  justify-content: center;
  align-items: flex-end;
`;

const InnerContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-end;
`;

const LNBGroup = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
  width: 100%;
`;

// 조건 문을 통해서 하나가 켜지면 다른게 꺼지는 형태가 되도록 조건문을 작성해야함
const Search = () => {
  const { type } = useParams();

  const [searchParams] = useSearchParams();
  const curPage = searchParams.get('page');
  const query = searchParams.get('query');

  const { seriesList, seriesPageInfo } = useSearchSeriesList(query, curPage);
  const { postList, postPageInfo } = useSearchPostList(query, curPage);

  return (
    <Container>
      <PageHeader headerSubTitle="Results for Keyword" headerTitle={`Search results ${query}`} />
      <LNBGroup>
        <SearchResultTitle
          title="검색결과"
          amount={Number(postPageInfo?.totalElements) + Number(seriesPageInfo?.totalElements) || 0}
        />
        <LnbSearch query={query} />
      </LNBGroup>
      {type && type === 'all' ? (
        <>
          <ResultContainer>
            <InnerContainer>
              <SearchResultTitle title="Posts" amount={postPageInfo?.totalElements || 0} />
              <PostListContainer postList={postList} />
            </InnerContainer>
            <TextButton to={`/search/posts?query=${query}&page=1&size=10`}>자세히 보기</TextButton>
          </ResultContainer>

          <ResultContainer>
            <SearchResultTitle title="Series" amount={seriesPageInfo?.totalElements || 0} />
            <SeriesListContainer seriesList={seriesList} />
            <TextButton to={`/search/series?query=${query}&page=1&size=10`}>자세히 보기</TextButton>
          </ResultContainer>
        </>
      ) : (
        ''
      )}

      {type && type === 'posts' ? (
        <>
          <SearchResultTitle title="Post" amount="60" />
          <PostListContainer postList={postList} />
          <Pagination totalPages={postPageInfo.totalPages || 1} />
        </>
      ) : (
        ''
      )}

      {type && type === 'series' ? (
        <>
          <SearchResultTitle title="Series" amount="60" />
          <SeriesListContainer seriesList={seriesList} />
          <Pagination totalPages={seriesPageInfo.totalPages || 1} />
        </>
      ) : (
        ''
      )}
    </Container>
  );
};

export default Search;
