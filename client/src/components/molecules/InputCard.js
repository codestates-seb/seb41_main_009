import styled from 'styled-components';

const Container = styled.div`
  box-sizing: border-box;
  width: ${props => (props.width ? props.width : '331px')};
  height: ${props => (props.height ? props.height : '76px')};
  padding: 28px 20px;
  gap: 10px;
  border: 2px solid black;
  box-shadow: ${props => (props.boxShadow ? props.boxShadow : 'var(--boxShadow-02) black')};
`;

const Input = styled.input`
  border: none;
  width: ${props => (props.width ? props.width : '')};
  height: ${props => (props.height ? props.height : '')};
`;

const Message = styled.div`
  color: ${props => (props.color ? props.color : 'black')};
  font-size: var(--label-xs);
  margin-top: 5px;
  margin-left: 3px;
`;

/**
 * props 설명
 * width, height: Input Card 컨테이너에 대한 높이, 너비 지정
 * type: Input type 지정 (password 등)
 * inputWidth, inputHeight: Input에 대한 높이, 너비 지정
 * placeholder: Input에 대한 placeholder 지정
 * onChange: Input에 onChange 함수 설정
 * messageColor, message: Input 하단의 메시지 div 설정
 * asideInput: Input 옆에 추가적인 컴포넌트 렌더 가능
 */

const InputCard = ({
  width,
  height,
  boxShadow,
  type,
  inputWidth,
  inputHeight,
  placeholder,
  onChange,
  messageColor,
  message,
  asideInput,
}) => {
  return (
    <Container width={width} height={height} boxShadow={boxShadow}>
      <div style={{ display: 'flex', justifyContent: 'space-between' }}>
        <Input
          type={'' || type}
          placeholder={placeholder}
          width={inputWidth}
          height={inputHeight}
          onChange={onChange}
        />
        {asideInput || ''}
      </div>
      <Message color={messageColor}>{'' || message}</Message>
    </Container>
  );
};

export default InputCard;
