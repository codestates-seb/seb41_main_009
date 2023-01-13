import styled from 'styled-components';
import Openidbox from '../organisms/Openidbox';
import Loginbox from '../organisms/Loginbox';
import { SplashProfileStickerLabel } from '../molecules/stickerLabel/SplashStickerLabel';

const Container = styled.div`
  display: flex;
  width: 100%;
  justify-content: center;
  align-items: center;
`;

const Body = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 400px;
  background-color: white;
`;

const Signin = () => {
  return (
    <Container>
      <SplashProfileStickerLabel />
      <Body>
        <Loginbox />
        <Openidbox />
      </Body>
    </Container>
  );
};

export default Signin;
