import styled from 'styled-components';
import SearchInput from '../molecules/SearchInput';

const Container = styled.div`
  position: fixed;
  top: 0;
  width: 100%;
  height: var(--header-height);
  background-color: white;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const Body = styled.div`
  display: flex;
  width: 1440px;
  justify-content: space-between;
  align-items: center;
`;

const Logo = styled.img`
  height: 50px;
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
        <Logo src="/image/logo.svg" alt="logo" />
        <SearchInput height="40px" />
        <ButtonList>
          <UserButton>LOGIN</UserButton>
          <UserButton>SignUp</UserButton>
        </ButtonList>
      </Body>
    </Container>
  );
};

export default Navigator;
