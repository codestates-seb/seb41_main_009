import styled from 'styled-components';

const Input = styled.input`
  box-sizing: border-box;
  padding: 7px;
  border: 1px solid var(--gray-50);
  background-color: var(--gray-50);
  border-radius: 3px;
  width: ${props => props.width};
  height: ${props => props.height};
  &:focus {
    outline: none !important;
    border-color: rgb(89, 164, 222);
    box-shadow: rgb(217, 234, 247) 0px 1px 4px, rgb(217, 234, 247) 0px 0px 0px 4px;
  }
`;

export default Input;
