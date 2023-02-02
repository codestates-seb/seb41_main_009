import axios from 'axios';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { SIGNUP_SUCCESS } from '../../constants/Messages';
import { BlackShadowButton } from '../atoms/Buttons';
import EmailBox from '../molecules/signup/EmailBox';
import EmailValidationBox from '../molecules/signup/EmailValidationBox';
import NicknameBox from '../molecules/signup/NicknameBox';
import PasswordBox from '../molecules/signup/PasswordBox';
import PasswordCheckBox from '../molecules/signup/PasswordCheckBox';
import { LoginMessage } from '../molecules/SignUpMessage';
import { Box } from '../atoms/signup/SignupComponents';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
  justify-content: center;
  align-items: center;
`;

const Signupbox = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [emailMessage, setEmailMessage] = useState('');
  const [nickname, setNickname] = useState('');
  const [nicknameMessage, setNicknameMessage] = useState('');
  const [password, setPassword] = useState('');
  const [passwordMessage, setPasswordMessage] = useState('');
  const [passwordCheck, setPasswordCheck] = useState('');
  const [passwordCheckMessage, setPasswordCheckMessage] = useState('');
  const [emailValidation, setEmailValidation] = useState(false);
  const [emailValidationCode, setEmailValidationCode] = useState('');
  const [emailValidationMessage, setEmailValidationMessage] = useState('');

  const onSignUpClick = async () => {
    if (
      !email ||
      !password ||
      !passwordCheck ||
      !nickname ||
      emailMessage ||
      passwordMessage ||
      passwordCheckMessage ||
      nicknameMessage
    ) {
      return;
    }

    // 비밀번호 전달 시 Hash 사용하여 암호화 추후 구현 필요
    const url = 'members';
    const body = {
      email,
      nickname,
      password,
    };

    axios
      .post(url, body)
      .then(() => {
        alert(SIGNUP_SUCCESS);
        navigate('/login');
      })
      .catch(err => {
        alert(err.message);
      });
  };

  return (
    <Container>
      <EmailBox
        email={email}
        setEmail={setEmail}
        emailMessage={emailMessage}
        setEmailMessage={setEmailMessage}
        setEmailValidation={setEmailValidation}
      />
      <EmailValidationBox
        email={email}
        emailValidation={emailValidation}
        emailValidationCode={emailValidationCode}
        setEmailValidationCode={setEmailValidationCode}
        emailValidationMessage={emailValidationMessage}
        setEmailValidationMessage={setEmailValidationMessage}
      />
      <NicknameBox
        setNickname={setNickname}
        nicknameMessage={nicknameMessage}
        setNicknameMessage={setNicknameMessage}
      />
      <PasswordBox
        setPassword={setPassword}
        passwordMessage={passwordMessage}
        setPasswordMessage={setPasswordMessage}
      />
      <PasswordCheckBox
        password={password}
        setPasswordCheck={setPasswordCheck}
        passwordCheckMessage={passwordCheckMessage}
        setPasswordCheckMessage={setPasswordCheckMessage}
      />
      <Box>
        <BlackShadowButton type="button" width="512px" onClick={onSignUpClick}>
          Sign Up
        </BlackShadowButton>
        <LoginMessage />
      </Box>
    </Container>
  );
};

export default Signupbox;
