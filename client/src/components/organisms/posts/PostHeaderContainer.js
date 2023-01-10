import styled from 'styled-components';
import PostHeader from '../../molecules/PostHeader';
import PostUserInfo from '../../molecules/PostUserInfo';

const Container = styled.div`
  display: flex;
  width: 1056px;
  height: 193px;
  margin: 0 auto;
  flex-direction: column;
  align-items: center;
  padding: 29px 31px;
  gap: 10px;
  background-color: aqua;
`;

const PostHeaderContainer = () => {
  return (
    <Container>
      <PostHeader />
      <PostUserInfo />
    </Container>
  );
};

export default PostHeaderContainer;
