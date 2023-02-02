import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import useSidebarStore from '../../store/sidebarStore';
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
  z-index: 99;

  box-shadow: 0px 2px 8px rgba(40, 41, 61, 0.08), 0px 20px 32px rgba(96, 97, 112, 0.24);
`;

const Body = styled.div`
  display: flex;
  width: 1440px;
  justify-content: space-between;
  align-items: center;
  margin: 0 10px;
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
  const { currentUserId, setUserId, setAuthorization } = useAuthStore(state => state);
  const { setCurrentTab } = useSidebarStore(state => state);
  const navigate = useNavigate();

  const handleLogout = () => {
    const url = 'auth/logout';

    axios
      .get(url)
      .then(() => {
        setUserId(0);
        setAuthorization('');
        navigate('/');
        window.location.reload();
      })
      .catch(err => {
        console.log(err);
      });
  };

  const handleClickHome = () => {
    setCurrentTab('Home');
  };

  const handleClickOthers = () => {
    setCurrentTab('');
  };

  return (
    <Container>
      <Body>
        <Logo to="/" alt="logo" onClick={handleClickHome} />
        <SearchInput height="40px" />
        <ButtonList>
          {currentUserId ? (
            <>
              <UserButton to={`users/${currentUserId}`} onClick={handleClickOthers}>
                My Page
              </UserButton>
              <UserButton onClick={handleLogout}>Log Out</UserButton>
            </>
          ) : (
            <>
              <UserButton to="/login" onClick={handleClickOthers}>
                Log In
              </UserButton>
              <UserButton to="/signup" onClick={handleClickOthers}>
                Sign Up
              </UserButton>
            </>
          )}
        </ButtonList>
      </Body>
    </Container>
  );
};

export default Header;
