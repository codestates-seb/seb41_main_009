import styled from 'styled-components';

const TabContentBox = ({ tag, children }) => {
  return (
    <Container>
      <Header>{tag}</Header>
      {children}
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 30px;
`;

const Header = styled.div`
  font-size: 18px;
  margin-bottom: 8px;
`;

export default TabContentBox;
