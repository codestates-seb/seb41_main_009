import styled from 'styled-components';
import { useParams, useSearchParams } from 'react-router-dom';

import PageHeader from '../organisms/PageHeader';
import { LnbSearch } from '../organisms/Lnb';
import { TextButton } from '../atoms/Buttons';
import Pagination from '../molecules/Pagination';
import SeriesListContainer from '../organisms/listContainter/SeriesListContainer';
import SearchResultTitle from '../organisms/search/SearchResultTitle';
import PostListContainer from '../organisms/listContainter/PostListContainer';
import useGetSeriesList from '../../hooks/useGetSeriesList';
import useGetPostList from '../../hooks/useGetPostList';
import { TextButton } from '../atoms/Buttons';
import Pagination from '../molecules/Pagination';

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

// 조건 문을 통해서 하나가 켜지면 다른게 꺼지는 형태가 되도록 조건문을 작성해야함
const Search = () => {
  const { keyword } = useParams();

  const [searchParams] = useSearchParams();
  const curPage = searchParams.get('page');
  const type = searchParams.get('type');

  const { seriesList, seriesPageInfo } = useGetSeriesList(keyword, curPage);
  const { postList, postPageInfo } = useGetPostList(keyword, curPage);

  return (
    <Container>
      <PageHeader headerSubTitle="Results for Keyword" headerTitle={`Search results ${keyword}`} />
      <SearchResultTitle title="검색결과" amount="120" />
      <LnbSearch keyword={keyword} />

      {type && type === 'All' ? (
        <>
          <ResultContainer>
            <InnerContainer>
              <SearchResultTitle title="Posts" amount="60" />
              <PostListContainer postList={postList} />
            </InnerContainer>
            <TextButton to={`/search/${keyword}?type=Posts`}>자세히 보기</TextButton>
          </ResultContainer>

          <ResultContainer>
            <SearchResultTitle title="Series" amount="60" />
            <SeriesListContainer seriesList={seriesList} />
            <TextButton to={`/search/${keyword}?type=Series`}>자세히 보기</TextButton>
          </ResultContainer>
        </>
      ) : (
        ''
      )}

      {type && type === 'Posts' ? (
        <>
          <SearchResultTitle title="Post" amount="60" />
          <PostListContainer postList={postList} />
          <Pagination totalPages={postPageInfo.totalPages} />
        </>
      ) : (
        ''
      )}

      {type && type === 'Series' ? (
        <>
          <SearchResultTitle title="Series" amount="60" />
          <SeriesListContainer seriesList={seriesList} />
          <Pagination totalPages={seriesPageInfo.totalPages} />
        </>
      ) : (
        ''
      )}
    </Container>
  );
};

export default Search;
