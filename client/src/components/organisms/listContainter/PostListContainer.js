import styled from 'styled-components';

import { PostCard } from '../../molecules/list/PostCard';

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
      <PostCard />
      <PostCard />
      <PostCard />
      <PostCard />
      <PostCard />
    </Container>
  );
};

export default PostListContainer;
