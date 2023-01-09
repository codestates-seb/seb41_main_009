import styled from 'styled-components';
import PostListHeaderContainer from '../organisms/postlist/PostListHeader';
import FilterContainer from '../organisms/postlist/PostListFilter';
import PaginationContainer from '../organisms/postlist/PostListPagination';

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

const PostList = () => {
  return (
    <Container>
      <PostListHeaderContainer />
      <FilterContainer />
      <PostListContainer>
        <div />
      </PostListContainer>
      <PaginationContainer />
    </Container>
  );
};

export default PostList;
