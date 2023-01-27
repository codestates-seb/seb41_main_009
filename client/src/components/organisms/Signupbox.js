import axios from 'axios';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { INVALIDNICKNAME, INVALIDPASSWORD, PASSWORDNOTMATCH, SIGNUP_SUCCESS } from '../../constants/Messages';
import { isValidNickname, isValidPassword } from '../../functions/isValid';
import { BlackShadowButton } from '../atoms/Buttons';
import EmailBox from '../molecules/signup/EmailBox';
import EmailValidationBox from '../molecules/signup/EmailValidationBox';
import NicknameBox from '../molecules/signup/NicknameBox';
import { LoginMessage } from '../molecules/SignUpMessage';

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

  const onPasswordInput = e => {
    const passwordValue = e.target.value;

    setPassword(passwordValue);

    if (isValidPassword(passwordValue) || passwordValue.length === 0) {
      setPasswordMessage('');
    } else {
      setPasswordMessage(INVALIDPASSWORD);
    }
  };

  const onPasswordCheckInput = e => {
    const passwordCheckValue = e.target.value;

    setPasswordCheck(passwordCheckValue);

    if (password === passwordCheckValue || passwordCheckValue.length === 0) {
      setPasswordCheckMessage('');
    } else {
      setPasswordCheckMessage(PASSWORDNOTMATCH);
    }
  };

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
      .then(res => {
        alert(SIGNUP_SUCCESS);
        console.log(res);
        navigate('/login');
      })
      .catch(err => {
        console.log(err);
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
      <Box>
        <Label>Password</Label>
        <SignupInput
          type="password"
          placeholder="Enter Your Password"
          onChange={onPasswordInput}
          message={passwordMessage}
        />
      </Box>
      <Box>
        <Label>Password Check</Label>
        <SignupInput
          type="password"
          placeholder="Re-Enter Your Password"
          onChange={onPasswordCheckInput}
          message={passwordCheckMessage}
        />
      </Box>
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
