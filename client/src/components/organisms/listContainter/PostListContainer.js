import styled from 'styled-components';

import { PostList } from '../../molecules/list/PostList';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: fit-content;
  margin-bottom: 50px;
`;

const PostListContainer = () => {
  return (
    <Container>
      <PostList />
      <PostList />
      <PostList />
      <PostList />
      <PostList />
    </Container>
  );
};

export default PostListContainer;
