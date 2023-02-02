import styled from 'styled-components';
import { useParams } from 'react-router-dom';
import PostHeaderLayer from '../organisms/posts/PostHeaderLayer';
import PostSubHeaderLayer from '../organisms/posts/PostSubHeaderLayer';
import PostContentLayer from '../organisms/posts/PostContentLayer';
import Comments from '../organisms/comment/Comments';
import useGetPost from '../../hooks/useGetPost';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: inherit;
  margin: 0 auto;
  justify-content: center;
  align-items: center;

  background: #ffffff;
  border-radius: 30px;
`;

// isLoading, isLoadingError 나중에 추가
const PostPage = () => {
  const { id } = useParams();
  const { post } = useGetPost(id);

  return (
    <Container>
      <PostHeaderLayer post={post} />
      <PostSubHeaderLayer post={post} />
      <PostContentLayer post={post} />
      <Comments basePath="posts" id={id} />
    </Container>
  );
};

export default PostPage;
