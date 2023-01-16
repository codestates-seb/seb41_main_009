import styled from 'styled-components';
import PageHeader from '../organisms/PageHeader';
import FilterContainer from '../organisms/listcontents/ListFilter';
import Pagination from '../molecules/Pagination';

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
      <Pagination totalPages={30} />
    </Container>
  );
};

export default PostList;
