import styled from 'styled-components';
import SeriesHeader from '../organisms/SeriesHeader';
import PostPage from './PostPage';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin: 0 auto;
  justify-content: center;
  align-items: center;
`;

const SeriesPage = () => {
  return (
    <Container>
      <SeriesHeader />
      <PostPage />
    </Container>
  );
};

export default SeriesPage;
