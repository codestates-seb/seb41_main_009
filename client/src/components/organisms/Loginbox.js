import { useState } from 'react';
import styled from 'styled-components';
import { INVALIDEMAIL } from '../../constants/Messages';
import { isValidEmail } from '../../functions/isValid';
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
  const [email, setEmail] = useState('');
  const [emailMessage, setEmailMessage] = useState('');

  const onEmailInput = e => {
    const emailValue = e.target.value;

    setEmail(emailValue);
    console.log(email);

    if (isValidEmail(emailValue) || emailValue.length === 0) {
      setEmailMessage('');
    } else {
      setEmailMessage(INVALIDEMAIL);
    }
  };

  return (
    <Container>
      <Box>
        <Label>Email</Label>
        <LoginInput placeholder="Enter Your Email" onChange={onEmailInput} message={emailMessage} messageColor="red" />
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
