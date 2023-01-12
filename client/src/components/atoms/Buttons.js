import styled from 'styled-components';

const WhiteButton = styled.a`
  width: ${props => (props.width ? props.width : '116px')};
  height: ${props => (props.height ? props.height : '44px')};
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px 30px;
  background-color: #ffffff;
  border: 1px solid var(--gray-600);
  text-decoration: none;
  font-size: var(--label-l);
  font-weight: 500;
  color: black;

  &:visited {
    color: black;
  }
`;

const BlackButton = styled(WhiteButton)`
  color: white;
  border: 1px solid var(--gray-700);
  background-color: var(--gray-700);

  &:visited {
    color: white;
  }
`;

const OrangeButton = styled(WhiteButton)`
  color: var(--orange-400);
  border: 1px solid var(--orange-400);

  &:visited {
    color: var(--orange-400);
  }
`;

const WhiteShadowButton = styled(WhiteButton)`
  box-shadow: var(--boxShadow-02) var(--gray-700);
`;

const BlackShadowButton = styled(BlackButton)`
  box-shadow: var(--boxShadow-02) var(--gray-600);
`;

const BlueShadowButton = styled(BlackShadowButton)`
  background-color: var(--blue-400);
  border-color: var(--blue-400);
  color: white;
  box-shadow: var(--boxShadow-02) var(--orange-400);
`;

const TextButton = styled.a`
  display: flex;
  width: ${props => (props.width ? props.width : '100px')};
  height: ${props => (props.height ? props.height : '30px')};
  align-items: center;
  text-decoration: none;

  &:visited {
    color: black;
  }

  &:hover {
    cursor: pointer;
  }
`;

export { WhiteButton, BlackButton, OrangeButton, WhiteShadowButton, BlackShadowButton, BlueShadowButton, TextButton };
