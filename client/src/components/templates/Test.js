import styled from 'styled-components';
import Dialog from '../molecules/Dialog';
import { SplashProfileStickerLabel } from '../molecules/stickerLabel/SplashStickerLabel';
import { PostList, PostListStack } from '../molecules/PostList';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 1500px;
  height: 1500px;
  background-color: gray;
  gap: 30px;
`;

const Test = () => {
  return (
    <Container>
      <Dialog />
      <SplashProfileStickerLabel />
      <PostList />
      <PostListStack />
    </Container>
  );
};

export default Test;
