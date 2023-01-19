import styled from 'styled-components';
import { useParams } from 'react-router-dom';
import PostHeaderLayer from '../organisms/posts/PostHeaderLayer';
import PostSubHeaderLayer from '../organisms/posts/PostSubHeaderLayer';
import PostContentLayer from '../organisms/posts/PostContentLayer';
import Comments from '../organisms/Comments';
import useGetPost from '../../hooks/useGetPost';

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

// isLoading, isLoadingError 나중에 추가
const PostPage = () => {
  const { category, postId } = useParams();

  const { post } = useGetPost(category, postId);

  return (
    <Container>
      <PostHeaderLayer post={post} />
      <PostSubHeaderLayer post={post} />
      <PostContentLayer post={post} />
      <Comments />
    </Container>
  );
};

export default PostPage;
