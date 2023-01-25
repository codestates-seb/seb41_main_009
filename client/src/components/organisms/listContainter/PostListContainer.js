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

const PostListContainer = ({ postList }) => {
  return (
    <Container>
      {postList.map(post => {
        return <PostCard key={post.id} postId={post.id} />;
      })}
    </Container>
  );
};

export default PostListContainer;
