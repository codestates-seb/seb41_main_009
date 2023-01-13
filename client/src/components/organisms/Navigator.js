import styled from 'styled-components';
import { LabelMedium } from '../../styles/typo';
import { TextButton } from '../atoms/Buttons';
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
  width: 150px;
  align-items: center;
  justify-content: space-between;
`;

const UserButton = styled(TextButton)`
  width: 70px;
  ${LabelMedium}
`;

const Navigator = () => {
  return (
    <Container>
      <Body>
        <Logo src="/image/logo.svg" alt="logo" />
        <SearchInput height="40px" />
        <ButtonList>
          {/* Session에 따라 변경되도록 수정 예정 */}
          {true ? (
            <>
              <UserButton to="/signin">Log In</UserButton>
              <UserButton to="/signup">Sign Up</UserButton>
            </>
          ) : (
            <>
              <UserButton to="/user">My Page</UserButton>
              <UserButton to="/">Log Out</UserButton>
            </>
          )}
        </ButtonList>
      </Body>
    </Container>
  );
};

export default Navigator;
