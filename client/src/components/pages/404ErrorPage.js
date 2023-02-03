import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import useSidebarStore from '../../store/sidebarStore';
import { DisplayLarge } from '../../styles/typo';
import { BlackShadowButton, WhiteShadowButton } from '../atoms/Buttons';
import { SplashStickerLabelDefault } from '../molecules/stickerLabel/SplashStickerLabel';

const ErrorPage = () => {
  const navigate = useNavigate();
  const { setCurrentTab } = useSidebarStore(state => state);

  const handleCurrentTab = () => {
    setCurrentTab('Home');
  };

  return (
    <Container>
      <Heading>404 Not Found</Heading>
      <Buttons>
        <BlackShadowButton to="/" width="170px" height="70px" onClick={handleCurrentTab}>
          홈으로
        </BlackShadowButton>
        <WhiteShadowButton width="170px" height="70px" onClick={() => navigate(-1)}>
          이전 페이지로
        </WhiteShadowButton>
      </Buttons>
      <SplashStickerLabelDefault />
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-around;
  width: 100%;
  height: 600px;
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
