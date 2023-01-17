import { Link } from 'react-router-dom';
import styled from 'styled-components';
import { Icon } from '@iconify/react';
import { LabelLarge, LabelSmall } from '../../styles/typo';
import { ReactComponent as GithubLogo } from '../../static/githubLogo.svg';
import { ReactComponent as GoogleLogo } from '../../static/googleLogo.svg';

const WhiteButton = styled(Link)`
  width: ${props => (props.width ? props.width : '116px')};
  height: ${props => (props.height ? props.height : '44px')};
  margin: ${props => (props.margin ? props.margin : '0')};
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px 30px;
  background-color: #ffffff;
  border: 1px solid var(--gray-600);
  text-decoration: none;
  color: black;
  ${LabelLarge}

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
  box-shadow: var(--boxShadow-00) var(--gray-700);
`;

const BlackShadowButton = styled(BlackButton)`
  box-shadow: var(--boxShadow-00) var(--gray-600);
`;

const BlueShadowButton = styled(BlackShadowButton)`
  background-color: var(--blue-400);
  border-color: var(--blue-400);
  color: white;
  box-shadow: var(--boxShadow-00) var(--orange-400);
`;

const TextButton = styled(Link)`
  display: flex;
  width: ${props => (props.width ? props.width : '100px')};
  height: ${props => (props.height ? props.height : '30px')};
  margin: ${props => (props.margin ? props.margin : '0')};
  align-items: center;
  text-decoration: none;
  ${LabelLarge}
  color: black;

  &:visited {
    color: black;
  }

  &:hover {
    cursor: pointer;
  }
`;

const SidebarMainButton = styled(TextButton)`
  margin-top: 15px;
`;

const SidebarTagsButton = styled(TextButton)`
  ${LabelSmall}
  color: var(--gray-500);

  &:visited {
    color: var(--gray-500);
  }
`;

const ClearBlurButton = ({ handleClick }) => {
  const Container = styled.div`
    display: flex;
    background-color: rgba(207, 207, 207, 20%);
    color: white;
    padding: 10px;
    gap: 10px;
  `;

  const Button = styled.button`
    all: unset;
  `;
  return (
    <Container>
      <Icon icon="mdi:shimmer" style={{ fontSize: '20px' }} />
      <Button type="button" onClick={handleClick}>
        Clear Blur
      </Button>
    </Container>
  );
};

export {
  WhiteButton,
  BlackButton,
  OrangeButton,
  WhiteShadowButton,
  BlackShadowButton,
  BlueShadowButton,
  TextButton,
  SidebarMainButton,
  SidebarTagsButton,
  ClearBlurButton,
};

const OAuth2Button = styled.a`
  width: ${props => (props.width ? props.width : '512px')};
  height: ${props => (props.height ? props.height : 'fit-content')};
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 10px 0px;
  gap: 12px;
  background-color: #ffffff;
  border: 1px solid var(--gray-600);
  ${LabelLarge}

  color: black;
  box-shadow: var(--boxShadow-00) var(--gray-700);

  &:visited {
    color: black;
  }
`;
const OAuth2GithubButton = ({ message }) => {
  return (
    <OAuth2Button>
      <GithubLogo />
      {message || 'Continue with Github'}
    </OAuth2Button>
  );
};

const OAuth2GoogleButton = ({ message }) => {
  return (
    <OAuth2Button>
      <GoogleLogo />
      {message || 'Continue with Google'}
    </OAuth2Button>
  );
};

export { OAuth2GithubButton, OAuth2GoogleButton };
