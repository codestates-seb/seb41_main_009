import styled from 'styled-components';
import { LabelListTitle } from '../../styles/typo';
import { BlackShadowButton } from '../atoms/Buttons';
import InputCard from '../molecules/InputCard';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 400px;
  justify-content: center;
  align-items: center;
`;

const LoginInput = ({ width, height, type, placeholder, onChange, messageColor, message, asideInput }) => {
  return (
    <InputCard
      width={width}
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
  margin: 15px 0;
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
        <BlackShadowButton>Log In</BlackShadowButton>
        {/* <SignUpMessage /> */}
      </Box>
    </Container>
  );
};

export default Loginbox;
