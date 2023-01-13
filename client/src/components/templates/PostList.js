import styled from 'styled-components';
import PageHeader from '../organisms/PageHeader';
import FilterContainer from '../organisms/listcontents/ListFilter';
import PaginationContainer from '../organisms/listcontents/ListPagination';
import PostListContainer from '../organisms/listcontents/PostListContainer';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 1060px;
  margin: 0 auto;
  justify-content: center;
  align-items: center;
`;

const PostList = () => {
  return (
    <Container>
      <PageHeader />
      <FilterContainer />
      <PostListContainer />
      <PaginationContainer />
    </Container>
  );
};

export default PostList;
