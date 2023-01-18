import styled from 'styled-components';
import PostHeader from '../organisms/posts/PostHeader';
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

  /* display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 30px 63px;
  gap: 52px; */
  background: #ffffff;
  border-radius: 30px;
`;

const PostPage = () => {
  return (
    <Container>
      <PostHeader />
      <PostSubHeaderContainer />
      <PostContent />
      <Comments />
    </Container>
  );
};

export default PostPage;
