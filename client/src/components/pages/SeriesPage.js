import styled from 'styled-components';
import { useParams } from 'react-router-dom';
import SeriesHeader from '../organisms/SeriesHeader';
import useGetSeries from '../../hooks/useGetSeries';
import PostInSeriesPage from './PostInSeriesPage';

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
  const { id } = useParams('id');

  // `${HOST}/series/${postId}` 해당하는 정보를 가져옴
  const { series } = useGetSeries(id);

  console.log(series, 'series in SeriesPage');
  return (
    <Container>
      <SeriesHeader series={series} />
      <PostInSeriesPage />
    </Container>
  );
};

export default SeriesPage;
