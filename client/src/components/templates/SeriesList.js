import styled from 'styled-components';
import PageHeader from '../organisms/PageHeader';
import PaginationContainer from '../organisms/listcontents/ListPagination';
import SeriesListContainer from '../organisms/listcontents/SeriesListContainer';
import Lnb from '../organisms/Lnb';
import { BlueShadowButton } from '../atoms/Buttons';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin: 0 auto;
  justify-content: center;
  align-items: center;
`;

const CreateSeriesButton = styled(BlueShadowButton)`
  width: 170px;
  height: 44px;
`;

const SeriesList = () => {
  return (
    <Container>
      <PageHeader
        headerSubTitle="Intorest In Category"
        headerTitle="Series In Category"
        asideHeader={<CreateSeriesButton to="/series/new">Create Series</CreateSeriesButton>}
      />
      <Lnb />
      <SeriesListContainer />
      <PaginationContainer />
    </Container>
  );
};

export default SeriesList;
