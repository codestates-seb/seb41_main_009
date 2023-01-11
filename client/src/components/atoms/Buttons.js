import styled from 'styled-components';

const WhiteButton = styled.a`
  width: ${props => (props.width ? props.width : '116px')};
  height: ${props => (props.height ? props.height : '44px')};
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px 30px;
  background: #ffffff;
  border: 1px solid #545454;
  text-decoration: none;

  &:visited {
    color: black;
  }
`;

export default WhiteButton;
