import axios from 'axios';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { INVALIDEMAIL, INVALIDPASSWORD, PASSWORDNOTMATCH, SIGNUP_SUCCESS } from '../../constants/Messages';
import { isValidEmail, isValidPassword } from '../../functions/isValid';
import { LabelListTitle } from '../../styles/typo';
import { BlackShadowButton } from '../atoms/Buttons';
import InputCard from '../molecules/InputCard';
import { LoginMessage } from '../molecules/SignUpMessage';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
  justify-content: center;
  align-items: center;
`;

const SignupInput = ({ height, type, placeholder, onChange, message, asideInput }) => {
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

const Signupbox = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [emailMessage, setEmailMessage] = useState('');
  const [nickname, setNickname] = useState('');
  // const [nicknameMessage, setNicknameMessage] = useState('');
  const [password, setPassword] = useState('');
  const [passwordMessage, setPasswordMessage] = useState('');
  const [passwordCheck, setPasswordCheck] = useState('');
  const [passwordCheckMessage, setPasswordCheckMessage] = useState('');

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
      setEmailMessage(INVALIDEMAIL);
    }
  };

  const onNicknameInput = e => {
    const nicknameValue = e.target.value;
    setNickname(nicknameValue);

    // if (isValidNickname(nicknameValue) || nicknameValue.length === 0) {
    //   setNicknameMessage('');
    // } else {
    //   setNicknameMessage(INVALIDNICKNAME);
    // }
  };

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
    if (!email || !password || !passwordCheck || !nickname || emailMessage || passwordMessage || passwordCheckMessage) {
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
      <Box>
        <Label>Email</Label>
        <SignupInput placeholder="Enter Your Email" onChange={onEmailInput} message={emailMessage} />
      </Box>
      <Box>
        <Label>Nickname</Label>
        <SignupInput placeholder="Enter Your Nickname" onChange={onNicknameInput} />
      </Box>
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
