import styled from 'styled-components';

import { PostCard } from '../../molecules/list/PostCard';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: fit-content;
  margin-bottom: 50px;
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
