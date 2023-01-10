import styled from 'styled-components';
import PostCreateDescription from '../organisms/postcreate/PostCreateDescription';
import PostCreateHeader from '../organisms/postcreate/PostCreateHeader';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  padding-top: 100px;
  width: 920px;
  height: 600px;
  align-items: center;
  background-color: cadetblue;
`;

const PostCreatePage = () => {
  return (
    <Container>
      <PostCreateHeader />
      <PostCreateDescription />
    </Container>
  );
};

export default PostCreatePage;
