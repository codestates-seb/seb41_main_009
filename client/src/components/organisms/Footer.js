import { Link } from 'react-router-dom';
import styled from 'styled-components';
import { LabelMedium } from '../../styles/typo';
import { TextButton } from '../atoms/Buttons';

const Container = styled.div`
  width: 100%;
  height: fit-content;
  background-color: var(--gray-700);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 50px;
  margin-top: 500px;
  z-index: 9999;
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
  flex-direction: row;
  width: 150px;
  align-items: center;
  justify-content: space-between;
`;

const UserButton = styled(TextButton)`
  width: 70px;
  justify-content: center;
  color: var(--gray-300);

  ${LabelMedium}
`;

const Footer = () => {
  return (
    <Container>
      <Logo to="/" alt="logo" />
      <ButtonList>
        <UserButton to="/">Questions</UserButton>
        <UserButton to="/">Help</UserButton>
      </ButtonList>
    </Container>
  );
};

export default Footer;
