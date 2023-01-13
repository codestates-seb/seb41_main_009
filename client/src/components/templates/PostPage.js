import styled from 'styled-components';
import PostHeaderContainer from '../organisms/posts/PostHeaderContainer';
import PostSubHeaderContainer from '../organisms/posts/PostSubHeaderContainer';
import PostContent from '../organisms/posts/PostContent';
import Comments from '../organisms/Comments';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: inherit;
  margin: 0 auto;
  justify-content: center;
  align-items: center;
`;

const PostPage = () => {
  return (
    <Container>
      <PostHeaderContainer />
      <PostSubHeaderContainer />
      <PostContent />
      <Comments />
    </Container>
  );
};

export default PostPage;
