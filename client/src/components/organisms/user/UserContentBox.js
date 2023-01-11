import styled from 'styled-components';

const TabContentBox = ({ tag, children }) => {
  return (
    <Container>
      <Header>{tag}</Header>
      <Body>{children}</Body>
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 30px;
  border: 0px solid black;
`;

const Header = styled.div`
  font-size: 18px;
  margin-bottom: 8px;
`;

const Body = styled.div`
  border: 1px solid black;
  padding: 20px;
  background-color: aqua;
`;

export default TabContentBox;
