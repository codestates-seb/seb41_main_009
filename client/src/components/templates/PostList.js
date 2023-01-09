import styled from 'styled-components';
import PostListHeaderContainer from '../organisms/postlist/PostListHeader';
import FilterContainer from '../organisms/postlist/PostListFilter';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 1024px;
  margin: 0 auto;
  padding-top: 100px;
  justify-content: center;
  align-items: center;
`;

const PostListContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: inherit;
`;

const PaginationContainer = styled.div`
  display: flex;
  width: inherit;
  height: 50px;
  align-items: center;
  justify-content: center;
  background-color: lightblue;
`;

const PostList = () => {
  return (
    <Container>
      <PostListHeaderContainer />
      <FilterContainer />
      <PostListContainer>
        <div />
      </PostListContainer>
      <PaginationContainer>
        <button type="button">1</button>
        <button type="button">2</button>
        <button type="button">3</button>
        <button type="button">4</button>
        <button type="button">5</button>
      </PaginationContainer>
    </Container>
  );
};

export default PostList;
