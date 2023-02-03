import styled from 'styled-components';
import { WhiteShadowButton } from '../atoms/Buttons';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-top: 15px;
  justify-content: center;
  align-items: center;
`;

const OAuthButton = ({ children }) => {
  return (
    <Container>
      <WhiteShadowButton width="512px">{children}</WhiteShadowButton>
    </Container>
  );
};

const Openidbox = () => {
  return (
    <Container>
      <OAuthButton> Log in with Google </OAuthButton>
      <OAuthButton> Log in with GitHub </OAuthButton>
    </Container>
  );
};

export default Openidbox;
