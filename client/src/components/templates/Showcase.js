import styled from 'styled-components';
import Showcasebox from '../organisms/Showcase/Showcasebox';

const Container = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  width: 100%;
  height: 100vh;
  background-color: gray;
  padding-top: 100px;
`;

const Header = styled.div`
  display: flex;
  width: 100%;
  height: 50px;
  background-color: green;
`;

const Body = styled.div`
  display: flex;
  flex-direction: row;
  margin-top: 20px;
  background-color: yellow;
  width: 100%;
  height: auto;
`;

const CaseContainer = styled.div`
  display: flex;
  flex-grow: 1;
  flex-direction: column;
  padding-right: 20px;

  &:last-child {
    padding-right: 0px;
  }
`;

const Showcase = () => {
  return (
    <Container>
      <Header> Header </Header>
      <Body>
        <CaseContainer>
          <Showcasebox />
          <Showcasebox />
          <Showcasebox />
        </CaseContainer>
        <CaseContainer>
          <Showcasebox />
          <Showcasebox />
          <Showcasebox />
        </CaseContainer>
        <CaseContainer>
          <Showcasebox />
          <Showcasebox />
          <Showcasebox />
        </CaseContainer>
      </Body>
    </Container>
  );
};

export default Showcase;
