import styled from 'styled-components';

import { PostList } from '../../molecules/list/PostList';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 1056px;
  gap: 30px;
  margin-top: 50px;
  margin-bottom: 50px;
  justify-content: center;
  align-items: center;
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
