import styled from 'styled-components';

const CardContainer = styled.div`
  box-sizing: border-box;
  width: ${props => (props.width ? props.width : '331px')};
  padding: 28px 20px;
  gap: 10px;
  border: 2px solid black;
  box-shadow: var(--boxShadow-02) black;
`;

export default CardContainer;
