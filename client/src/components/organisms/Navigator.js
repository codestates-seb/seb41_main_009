import styled from 'styled-components';

const Container = styled.div`
  position: fixed;
  width: 100%;
  height: 100px;
  background-color: white;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const Body = styled.div`
  display: flex;
  width: 1440px;
  justify-content: space-between;
`;

const Logo = styled.h1`
  font-size: 20px;
  color: white;
  background-color: red;
`;

const Searchbar = styled.div`
  width: 300px;
  border: 2px solid #333;
`;

const ButtonList = styled.div`
  display: flex;
`;

const UserButton = styled.button`
  width: 100px;
`;

const Navigator = () => {
  return (
    <Container>
      <Body>
        <Logo>IntoREST</Logo>
        <Searchbar />
        <ButtonList>
          <UserButton>LOGIN</UserButton>
          <UserButton>SignUp</UserButton>
        </ButtonList>
      </Body>
    </Container>
  );
};

export default Navigator;
