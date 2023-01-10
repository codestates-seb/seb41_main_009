import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 300px;
  margin-top: 15px;
  justify-content: center;
  align-items: center;
  background-color: #eee;
`;

const Loginbox = () => {
  return <Container>Login Box</Container>;
};

export default Loginbox;
