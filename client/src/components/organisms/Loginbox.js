import styled from 'styled-components';
import { LabelListTitle } from '../../styles/typo';
import { BlackShadowButton } from '../atoms/Buttons';
import InputCard from '../molecules/InputCard';
import { SignUpMessage } from '../molecules/SignUpMessage';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 400px;
  justify-content: center;
  align-items: center;
`;

const LoginInput = ({ height, type, placeholder, onChange, messageColor, message, asideInput }) => {
  return (
    <InputCard
      width="512px"
      height={height}
      boxShadow="var(--boxShadow-00) black"
      type={'' || type}
      placeholder={placeholder}
      inputWidth="100%"
      inputHeight="20px"
      onChange={onChange}
      asideInput={asideInput}
      message={message}
      messageColor={messageColor}
    />
  );
};

const Box = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 20px 0;
`;

const Label = styled.div`
  width: 100%;
  margin-bottom: 5px;
  ${LabelListTitle}
`;

const Loginbox = () => {
  return (
    <Container>
      <Box>
        <Label>Email</Label>
        <LoginInput placeholder="Enter Your Email" />
      </Box>
      <Box>
        <Label>Password</Label>
        <LoginInput type="password" placeholder="Enter Your Password" />
      </Box>
      <Box>
        <BlackShadowButton width="512px">Log In</BlackShadowButton>
        <SignUpMessage />
      </Box>
    </Container>
  );
};

export default Loginbox;
