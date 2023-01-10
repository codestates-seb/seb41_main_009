import styled from 'styled-components';
import PostHeaderContainer from '../organisms/posts/PostHeaderContainer';
import PostSubHeaderContainer from '../organisms/posts/PostSubHeaderContainer';
import PostContent from '../organisms/posts/PostContent';
import PostCommentContainer from '../organisms/posts/PostCommentContainer';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 1056px;
  margin: 0 auto;
  padding-top: 100px;
  justify-content: center;
  align-items: center;
`;

const PostPage = () => {
  return (
    <Container>
      <PostHeaderContainer />
      <PostSubHeaderContainer />
      <PostContent />
      <PostCommentContainer />
    </Container>
  );
};

export default PostPage;
