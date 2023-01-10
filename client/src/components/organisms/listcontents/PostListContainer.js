import styled from 'styled-components';
import Post from '../../molecules/Post';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 874px;
  margin-bottom: 50px;
`;

const PostListContainer = () => {
  return (
    <Container>
      <Post />
      <Post />
      <Post />
      <Post />
    </Container>
  );
};

export default PostListContainer;
