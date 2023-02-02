import styled from 'styled-components';
import PostHeaderLayer from '../organisms/posts/PostHeaderLayer';
import PostSubHeaderLayer from '../organisms/posts/PostSubHeaderLayer';
import PostContentLayer from '../organisms/posts/PostContentLayer';
import Comments from '../organisms/comment/Comments';
import useGetPost from '../../hooks/useGetPost';
import useSeriesStore from '../../store/seriesStore';

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
const PostInSeriesPage = () => {
  const { currentPostId } = useSeriesStore();

  // `${HOST}/posts/${category}/${postId}` 해당하는 정보를 가져옴
  const { post } = useGetPost(currentPostId);

  return (
    <Container>
      <PostHeaderLayer post={post} />
      <PostSubHeaderLayer post={post} />
      <PostContentLayer post={post} />
      <Comments basePath="posts" id={currentPostId} />
    </Container>
  );
};

export default PostInSeriesPage;
