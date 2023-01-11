import styled from 'styled-components';
import Openidbox from '../organisms/Openidbox';
import Signupbox from '../organisms/Signupbox';

const Container = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 1500px;
  background-color: gray;
`;

const Body = styled.div`
  display: flex;
  flex-direction: column;
  width: 400px;
  height: 600px;
  background-color: white;
`;

const Signup = () => {
  return (
    <Container>
      <Body>
        <Openidbox />
        <Signupbox />
      </Body>
    </Container>
  );
};

export default Signup;
