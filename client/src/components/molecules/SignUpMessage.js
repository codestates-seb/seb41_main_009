import styled from 'styled-components';
import { TextButton } from '../atoms/Buttons';
import { LabelSmall } from '../../styles/typo';

const Container = styled.div`
  display: flex;
  align-items: center;
  margin-top: 15px;
`;

const MoveLink = styled(TextButton)`
  color: var(--orange-400);
  ${LabelSmall}

  &:visited {
    color: var(--orange-400);
  }
`;

const Message = styled.span`
  ${LabelSmall}
  margin-right: 10px;
`;

const SignUpMessage = () => {
  return (
    <Container>
      <Message>아직 계정이 없으신가요?</Message>
      <MoveLink to="/signup">Sign Up</MoveLink>
    </Container>
  );
};

const LoginMessage = () => {
  return (
    <Container>
      <Message>이미 계정이 있으신가요?</Message>
      <MoveLink to="/login">Log In</MoveLink>
    </Container>
  );
};

const FindPasswordMessage = () => {
  return (
    <Container>
      <Message>비밀번호를 잊으셨나요?</Message>
      <MoveLink to="/findpassword">Find Password</MoveLink>
    </Container>
  );
};

export { SignUpMessage, LoginMessage, FindPasswordMessage };
