import styled from 'styled-components';
import PostCreateBody from '../organisms/postcreate/PostCreateBody';
import PostCreateButtons from '../organisms/postcreate/PostCreateButtons';
import PostCreateDescription from '../organisms/postcreate/PostCreateDescription';
import PostCreateHeader from '../organisms/postcreate/PostCreateHeader';
import PostCreateIndex from '../organisms/postcreate/PostCreateIndex';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  width: 100%;
  align-items: center;
  background-color: cadetblue;
`;

const PostCreatePage = () => {
  return (
    <Container>
      <PostCreateHeader />
      <PostCreateDescription />
      <PostCreateIndex />
      <PostCreateBody />
      <PostCreateButtons />
    </Container>
  );
};

export default PostCreatePage;
