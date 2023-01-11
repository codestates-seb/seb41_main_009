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

const InputCard = () => {
  return <Container />;
};

export default InputCard;
