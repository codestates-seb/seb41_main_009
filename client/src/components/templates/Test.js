import styled from 'styled-components';
import Dialog from '../molecules/Dialog';

const Container = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 1500px;
  height: 1500px;
  background-color: gray;
  gap: 30px;
`;

const Test = () => {
  return (
    <Container>
      <Dialog />
      <Dialog message="Dialog Props Test" background="var(--green-400)" />
    </Container>
  );
};

export default Test;
