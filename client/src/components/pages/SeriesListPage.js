import styled from 'styled-components';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';
import PageHeader from '../organisms/PageHeader';
import Lnb from '../organisms/Lnb';
import { BlueShadowButton } from '../atoms/Buttons';
import Pagination from '../molecules/Pagination';
import SeriesListContainer from '../organisms/listContainter/SeriesListContainer';
import SERIESURL from '../../constants/URL';

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

const SeriesListPage = () => {
  const [seriesList, setSeriesList] = useState([]);

  useEffect(() => {
    axios
      .get(SERIESURL)
      .then(({ data }) => setSeriesList(data))
      .catch(err => console.log(err))
      .finally(setSeriesList(['abc', 'def', 'ㅁㅁㅁ', 'ㅁㄴㅇㄹ', 'ㅁㄴㅇㄹㄹㄹㄹ']));
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

export default SeriesListPage;
