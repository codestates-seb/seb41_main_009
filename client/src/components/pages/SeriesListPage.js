import styled from 'styled-components';
import { useParams, useSearchParams } from 'react-router-dom';
import PageHeader from '../organisms/PageHeader';
import { Lnb } from '../organisms/Lnb';
import { BlueShadowButton } from '../atoms/Buttons';
import Pagination from '../molecules/Pagination';
import SeriesListContainer from '../organisms/listContainter/SeriesListContainer';
import useGetSeriesList from '../../hooks/useGetSeriesList';
import Loading from '../atoms/Loading';

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
  const { seriesList, seriesPageInfo, isLoading } = useGetSeriesList(category || 'All', curPage);

  console.log(seriesList);

  return (
    <Container>
      <PageHeader
        headerSubTitle="Intorest In Category"
        headerTitle={`Series In ${category || 'All'}`}
        asideHeader={<CreateSeriesButton to="/series/new">Create Series</CreateSeriesButton>}
      />
      <Lnb currentTab="Series" category={category} />
      {isLoading ? (
        <Loading />
      ) : (
        <>
          <SeriesListContainer seriesList={seriesList} />
          <Pagination totalPages={seriesPageInfo?.totalPages || 1} />
        </>
      )}
    </Container>
  );
};

export default SeriesListPage;
