import styled from 'styled-components';

const Container = styled.div`
  display: flex;
  border: 1px solid #333;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

const Menu = styled.div`
  width: 200px;
  margin-top: 25px;
`;

const Navigator = () => {
  return (
    <Container>
      <Menu>ㅇㅇ</Menu>
      <Menu>ㅇㅇ</Menu>
      <Menu>ㅇㅇ</Menu>
      <Menu>ㅇㅇ</Menu>
    </Container>
  );
};

export default Navigator;
