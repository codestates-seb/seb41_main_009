import { useEffect } from 'react';
import styled from 'styled-components';
import { useParams } from 'react-router-dom';
import SeriesHeader from '../organisms/SeriesHeader';
import useGetSeries from '../../hooks/useGetSeries';
import PostInSeriesPage from './PostInSeriesPage';
import useSeriesStore from '../../store/seriesStore';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin: 0 auto;
  justify-content: center;
  align-items: center;
  gap: 30px;
`;

const SeriesPage = () => {
  const { id } = useParams();

  const { series, getSeries } = useGetSeries();
  const { currentPostId } = useSeriesStore();

  useEffect(() => {
    getSeries(id);
  }, []);

  return (
    <Container>
      <SeriesHeader series={series} seriesId={id} />
      {currentPostId === 0 ? '' : <PostInSeriesPage />}
    </Container>
  );
};

export default SeriesPage;
