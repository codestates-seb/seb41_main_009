import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { DisplayLarge } from '../../styles/typo';
import { BlackShadowButton, WhiteShadowButton } from '../atoms/Buttons';

const ErrorPage = () => {
  const navigate = useNavigate();

  return (
    <Container>
      <Heading>404 Not Found</Heading>
      <Buttons>
        <BlackShadowButton to="/" width="170px" height="80px">
          홈으로
        </BlackShadowButton>
        <WhiteShadowButton width="170px" height="80px" onClick={() => navigate(-1)}>
          이전 페이지로
        </WhiteShadowButton>
      </Buttons>
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100%;
`;

const Heading = styled.div`
  ${DisplayLarge}
`;

const Buttons = styled.div`
  display: flex;
  width: 50%;
  align-items: center;
  justify-content: space-around;
`;

export default ErrorPage;
