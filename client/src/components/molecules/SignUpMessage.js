import styled from 'styled-components';
import { TextButton } from '../atoms/Buttons';
import { LabelMedium } from '../../styles/typo';

const Container = styled.div`
  display: flex;
  align-items: center;
  margin-top: 15px;
`;

const MoveLink = styled(TextButton)`
  color: var(--orange-400);

  &:visited {
    color: var(--orange-400);
  }
`;

const Message = styled.span`
  ${LabelMedium}
  margin-right: 10px;
`;

const SignUpMessage = () => {
  return (
    <Container>
      <Message>계정이 아직 없으신가요?</Message>
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

export { SignUpMessage, LoginMessage };
