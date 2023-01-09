import styled from 'styled-components';
import Openidbox from '../organisms/Openidbox';
import Loginbox from '../organisms/Loginbox';

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

const Signin = () => {
  return (
    <Container>
      <Body>
        <Openidbox />
        <Loginbox />
      </Body>
    </Container>
  );
};

export default Signin;
