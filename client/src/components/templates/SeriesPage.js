import styled from 'styled-components';
import SeriesHeader from '../organisms/SeriesHeader';
import PostPage from './PostPage';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 1056px;
  margin: 0 auto;
  padding-top: 100px;
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
