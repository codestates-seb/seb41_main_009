import styled from 'styled-components';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';
import PageHeader from '../organisms/PageHeader';
import SeriesListContainer from '../organisms/listcontents/SeriesListContainer';
import Lnb from '../organisms/Lnb';
import { BlueShadowButton } from '../atoms/Buttons';
import Pagination from '../molecules/Pagination';

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
  const [seriesList, setSeriesList] = useState([]);

  const SERIESURL = '';

  useEffect(() => {
    axios
      .get(SERIESURL)
      .then(({ data }) => setSeriesList(data))
      .catch(err => console.log(err))
      .finally(setSeriesList(['abc', 'def']));
  }, []);

  const { category } = useParams();

  return (
    <Container>
      <PageHeader
        headerSubTitle="Intorest In Category"
        headerTitle={`Series In ${category}`}
        asideHeader={<CreateSeriesButton to="/series/new">Create Series</CreateSeriesButton>}
      />
      <Lnb />
      <SeriesListContainer seriesList={seriesList} />
      <Pagination totalPages={10} />
    </Container>
  );
};

export default SeriesList;
