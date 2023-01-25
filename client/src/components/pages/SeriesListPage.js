import styled from 'styled-components';
import { useParams, useSearchParams } from 'react-router-dom';
import PageHeader from '../organisms/PageHeader';
import Lnb from '../organisms/Lnb';
import { BlueShadowButton } from '../atoms/Buttons';
import Pagination from '../molecules/Pagination';
import SeriesListContainer from '../organisms/listContainter/SeriesListContainer';
import useGetSeriesList from '../../hooks/useGetSeriesList';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin: 0 auto;
  justify-content: center;
  align-items: center;
  margin-bottom: 100px;
`;

const CreateSeriesButton = styled(BlueShadowButton)`
  width: 170px;
  height: 44px;
`;

const SeriesListPage = () => {
  const { category } = useParams();
  const [searchParams] = useSearchParams();
  const curPage = searchParams.get('page');

  // seriesList에 seriesListDummy.data
  // seriesPageInfo에 seriesListDummy.pageInfo
  const { seriesList, seriesPageInfo, isLoading, isLoadingError } = useGetSeriesList(category, curPage);

  // isLoading, isLoadingError 값에 따라 Loading 컴포넌트 변경 예정
  console.log(isLoading, isLoadingError);

  return (
    <Container>
      <PageHeader
        headerSubTitle="Intorest In Category"
        headerTitle={`Series In ${category}`}
        asideHeader={<CreateSeriesButton to="/series/new">Create Series</CreateSeriesButton>}
      />
      <Lnb />
      <SeriesListContainer seriesList={seriesList} />
      <Pagination totalPages={seriesPageInfo.totalPage} />
    </Container>
  );
};

export default SeriesListPage;
