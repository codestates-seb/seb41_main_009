import styled from 'styled-components';

const Container = styled.div`
  box-sizing: border-box;
  width: ${props => (props.width ? props.width : '331px')};
  height: ${props => (props.height ? props.height : '76px')};
  padding: 28px 20px;
  gap: 10px;
  border: 2px solid black;
  box-shadow: var(--boxShadow-02) black;

  margin: 50px;
`;

const Input = styled.input`
  border: none;
  width: ${props => (props.width ? props.width : '')};
  height: ${props => (props.height ? props.height : '')};
`;

const InputCard = ({ width, height, inputWidth, inputHeight }) => {
  return (
    <Container width={width} height={height}>
      <Input placeholder="Input Here" width={inputWidth} height={inputHeight} />
    </Container>
  );
};

export default InputCard;
