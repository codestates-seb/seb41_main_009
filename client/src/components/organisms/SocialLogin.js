import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-top: 15px;
  justify-content: center;
  align-items: center;
`;

const OAuthButton = styled.div`
  width: 80%;
  height: 30px;
  margin-top: 15px;
  text-align: center;
  background-color: #eee;
`;

const SocialLogin = () => {
  return (
    <Container>
      <OAuthButton> Github Login </OAuthButton>
      <OAuthButton> Naver Login </OAuthButton>
      <OAuthButton> Google Login </OAuthButton>
    </Container>
  );
};

export default SocialLogin;
