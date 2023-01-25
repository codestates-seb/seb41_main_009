import axios from 'axios';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import useAuthStore from '../../store/useAuthStore';
import { LabelMedium } from '../../styles/typo';
import { TextButton } from '../atoms/Buttons';
import SearchInput from '../molecules/SearchInput';

const Container = styled.div`
  position: fixed;
  top: 0;
  width: 100%;
  height: var(--header-height);
  background-color: var(--gray-50);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999;

  box-shadow: 0px 2px 8px rgba(40, 41, 61, 0.08), 0px 20px 32px rgba(96, 97, 112, 0.24);
`;

const Body = styled.div`
  display: flex;
  width: 1440px;
  justify-content: space-between;
  align-items: center;
`;

const Logo = styled(Link)`
  width: 160px;
  height: 50px;
  background-image: url('/image/logo.svg');
  background-repeat: no-repeat;
  background-size: 160px 53px;
`;

const ButtonList = styled.div`
  display: flex;
  width: 150px;
  align-items: center;
  justify-content: space-between;
`;

const UserButton = styled(TextButton)`
  width: 70px;
  justify-content: center;
  ${LabelMedium}
`;

const Header = () => {
  const { currentUserId, setUserId } = useAuthStore(state => state);

  const handleLogout = () => {
    const url = 'logout';

    axios
      .get(url)
      .then(() => {
        setUserId(0);
      })
      .catch(err => {
        console.log(err);
      })
      .finally(() => {
        setUserId(0);
      });
  };

  return (
    <Container>
      <Body>
        <Logo to="/" alt="logo" />
        <SearchInput height="40px" />
        <ButtonList>
          {currentUserId ? (
            <>
              <UserButton to={`users/${currentUserId}`}>My Page</UserButton>
              <UserButton onClick={handleLogout}>Log Out</UserButton>
            </>
          ) : (
            <>
              <UserButton to="/login">Log In</UserButton>
              <UserButton to="/signup">Sign Up</UserButton>
            </>
          )}
        </ButtonList>
      </Body>
    </Container>
  );
};

export default Header;
