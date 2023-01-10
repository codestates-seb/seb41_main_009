import styled from 'styled-components';
import Post from '../../molecules/Post';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: inherit;
  background-color: lightcoral;
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
