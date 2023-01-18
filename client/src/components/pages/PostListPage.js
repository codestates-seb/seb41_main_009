import styled from 'styled-components';
import PageHeader from '../organisms/PageHeader';
import Pagination from '../molecules/Pagination';
import Lnb from '../organisms/Lnb';
import PostListContainer from '../organisms/listContainter/PostListContainer';
import SearchResultTitle from '../organisms/search/SearchResultTitle';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 1060px;
  margin: 0 auto;
  justify-content: center;
  align-items: center;
  margin-bottom: 100px;
`;

const PostList = () => {
  return (
    <Container>
      <PageHeader />
      <SearchResultTitle title="검색결과" amount="120" />
      <Lnb />
      <PostListContainer />
      <Pagination totalPages={30} />
    </Container>
  );
};

export default PostList;
