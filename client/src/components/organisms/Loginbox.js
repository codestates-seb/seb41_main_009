import { useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { INVALID_EMAIL, INVALID_PASSWORD } from '../../constants/Messages';
import { isValidEmail, isValidPassword } from '../../functions/isValid';
import { LabelListTitle } from '../../styles/typo';
import { BlackShadowButton } from '../atoms/Buttons';
import InputCard from '../molecules/InputCard';
import { SignUpMessage } from '../molecules/SignUpMessage';
import useAuthStore from '../../store/useAuthStore';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
  justify-content: center;
  align-items: center;
`;

const LoginInput = ({ height, type, placeholder, onChange, message, asideInput }) => {
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
      messageColor="red"
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
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [emailMessage, setEmailMessage] = useState('');
  const [password, setPassword] = useState('');
  const [passwordMessage, setPasswordMessage] = useState('');
  const { setUserId, setAuthorization } = useAuthStore(state => state);

  /**
   *
   * @param {Event} e
   * @returns {void}
   */
  const onEmailInput = e => {
    const emailValue = e.target.value;

    setEmail(emailValue);

    if (isValidEmail(emailValue) || emailValue.length === 0) {
      setEmailMessage('');
    } else {
      setEmailMessage(INVALID_EMAIL);
    }
  };

  const onPasswordInput = e => {
    const passwordValue = e.target.value;

    setPassword(passwordValue);

    if (isValidPassword(passwordValue) || passwordValue.length === 0) {
      setPasswordMessage('');
    } else {
      setPasswordMessage(INVALID_PASSWORD);
    }
  };

  const onLoginClick = () => {
    if (!email || !password || emailMessage || passwordMessage) {
      // return;
    }

    const url = 'login';
    const body = {
      email,
      password,
    };
    const header = {
      withCredentials: true,
    };

    axios
      .post(url, body, header)
      .then(data => {
        setUserId(data.data);
        setAuthorization(data.headers.authorization);
        navigate('/');
      })
      .catch(err => {
        console.log(err.message);
      });
  };

  // const onKeyDown = event => {
  //   if (event.keyCode === 13) {
  //     onLoginClick();
  //   }
  // };
  return (
    <Container>
      <Box>
        <Label>Email</Label>
        <LoginInput placeholder="Enter Your Email" onChange={onEmailInput} message={emailMessage} />
      </Box>
      <Box>
        <Label>Password</Label>
        <LoginInput
          type="password"
          placeholder="Enter Your Password"
          onChange={onPasswordInput}
          message={passwordMessage}
          // onKeyDown={onKeyDown}
        />
      </Box>
      <Box>
        <BlackShadowButton width="512px" onClick={onLoginClick}>
          Log In
        </BlackShadowButton>
        <SignUpMessage />
      </Box>
    </Container>
  );
};

export default Loginbox;
