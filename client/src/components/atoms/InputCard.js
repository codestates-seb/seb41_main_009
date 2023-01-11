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

const Message = styled.div`
  display: ${props => (props.display ? 'block' : 'none')};
  color: ${props => (props.color ? props.color : 'black')};
  font-size: var(--label-xs);
  margin-top: 5px;
  margin-left: 3px;
`;

const InputCard = ({
  width,
  height,
  type,
  inputWidth,
  inputHeight,
  onChange,
  messageDisplay,
  messageColor,
  message,
}) => {
  return (
    <Container width={width} height={height}>
      <Input type={'' || type} placeholder="Input Here" width={inputWidth} height={inputHeight} onChange={onChange} />
      <Message display={messageDisplay} color={messageColor}>
        {'' || message}
      </Message>
    </Container>
  );
};

export default InputCard;
