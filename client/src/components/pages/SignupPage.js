import styled from 'styled-components';
import { SplashProfileStickerLabel } from '../molecules/stickerLabel/SplashStickerLabel';
import Signupbox from '../organisms/Signupbox';

const Signup = () => {
  return (
    <Container>
      <SplashProfileStickerLabel
        boyMessage="Dive"
        girlMessage="IntoRest"
        emailMessage="And Share"
        createdAtMessage="Interests"
      />
      <Body>
        <Signupbox />
      </Body>
    </Container>
  );
};

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
  height: 100%;
`;

export default Signup;
