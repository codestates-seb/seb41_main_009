import styled from 'styled-components';
import SeriesList from '../../molecules/list/SeriesList';
import SeriesPostList from '../../molecules/list/SeriesPostList';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: fit-content;
  margin-bottom: 50px;
`;

const PostListContainer = () => {
  return (
    <Container>
      <SeriesList />
      <SeriesPostList />
    </Container>
  );
};

export default PostListContainer;
