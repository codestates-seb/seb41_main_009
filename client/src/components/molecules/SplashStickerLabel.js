import styled from 'styled-components';
import { StickerCreatedAt, StickerNicknameBoy, StickerEmail } from './StickerLabel';

const Container = styled.div`
  width: 1056px;
  height: 260px;

  /* all/blue400 */

  background: ${props => (props.background ? props.background : '#EFEFEF;')};
  box-shadow: ${props => (props.boxShadow ? props.boxShadow : '')};
  border-radius: 15px;
  overflow: hidden;
`;

const ProfileContainer = styled.div`
  position: relative;
  width: 512px;
  height: 717px;

  background: #efefef;
  border-radius: 15px;

  background: ${props => (props.background ? props.background : '#EFEFEF;')};
  box-shadow: ${props => (props.boxShadow ? props.boxShadow : '')};
  border-radius: 15px;
  overflow: hidden;
`;

const ProfileInfoText = styled.div`
  position: absolute;
  width: 200px;
  height: 80px;
  left: 46px;
  top: 43px;

  font-family: 'Roboto';
  font-style: normal;
  font-weight: 800;
  font-size: 18px;
  line-height: 40px;
`;

const ProfileInfoTextEmphasizeA = styled.span`
  color: var(--orange-400);
`;
const TiltedContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0px;
  gap: 15px;

  position: relative;
  width: ${props => (props.width ? props.width : '400px;')};
  height: ${props => (props.height ? props.height : '50px;')};
  left: ${props => (props.left ? props.left : '10px;')};
  top: ${props => (props.top ? props.top : '50px;')};

  transform: rotate(-10deg);
`;

const StickerLabelLine = styled.div`
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  padding: 10px;
  gap: 20px;

  width: 1152px;
  height: 64px;
`;

const SplashStickerLabelDefault = () => {
  return (
    <Container>
      <TiltedContainer>
        <StickerLabelLine>
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
        </StickerLabelLine>
        <StickerLabelLine>
          <StickerEmail />
          <StickerEmail />
          <StickerEmail />
          <StickerEmail />
          <StickerEmail />

          <StickerEmail />
        </StickerLabelLine>
        <StickerLabelLine>
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
        </StickerLabelLine>
      </TiltedContainer>
    </Container>
  );
};

const SplashStickerLabelBlue = ({ background = '#0C3BDD', boxShadow = '4px 4px 0px #F77E10;' }) => {
  return (
    <Container background={background} boxShadow={boxShadow}>
      <TiltedContainer>
        <StickerLabelLine>
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
        </StickerLabelLine>
        <StickerLabelLine>
          <StickerEmail />
          <StickerEmail />
          <StickerEmail />
          <StickerEmail />
          <StickerEmail />
          <StickerEmail />
        </StickerLabelLine>
        <StickerLabelLine>
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
        </StickerLabelLine>
      </TiltedContainer>
    </Container>
  );
};

const SplashProfileStickerLabel = ({
  background,
  boxShadow,
  width = '860px',
  height = '32px',
  left = '-150px',
  top = '380px',
}) => {
  return (
    <ProfileContainer background={background} boxShadow={boxShadow}>
      <ProfileInfoText>
        Share Your Interest
        <ProfileInfoTextEmphasizeA>,</ProfileInfoTextEmphasizeA> And Dive
        <ProfileInfoTextEmphasizeA> IntoRest</ProfileInfoTextEmphasizeA>
      </ProfileInfoText>
      <TiltedContainer width={width} height={height} left={left} top={top}>
        <StickerLabelLine>
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
        </StickerLabelLine>
        <StickerLabelLine>
          <StickerEmail />
          <StickerEmail />
          <StickerEmail />
          <StickerEmail />
          <StickerEmail />
          <StickerEmail />
        </StickerLabelLine>
        <StickerLabelLine>
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
        </StickerLabelLine>
      </TiltedContainer>
    </ProfileContainer>
  );
};

const SplashProfileStickerLabelBlue = ({
  background = '#0C3BDD',
  boxShadow = '4px 4px 0px #F77E10;',
  width = '860px',
  height = '32px',
  left = '-150px',
  top = '380px',
}) => {
  return (
    <ProfileContainer background={background} boxShadow={boxShadow}>
      <ProfileInfoText>
        Share Your Interest
        <ProfileInfoTextEmphasizeA>,</ProfileInfoTextEmphasizeA> And Dive
        <ProfileInfoTextEmphasizeA> IntoRest</ProfileInfoTextEmphasizeA>
      </ProfileInfoText>
      <TiltedContainer width={width} height={height} left={left} top={top}>
        <StickerLabelLine>
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
          <StickerNicknameBoy />
        </StickerLabelLine>
        <StickerLabelLine>
          <StickerEmail />
          <StickerEmail />
          <StickerEmail />
          <StickerEmail />
          <StickerEmail />
          <StickerEmail />
        </StickerLabelLine>
        <StickerLabelLine>
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
          <StickerCreatedAt />
        </StickerLabelLine>
      </TiltedContainer>
    </ProfileContainer>
  );
};

export { SplashStickerLabelDefault, SplashStickerLabelBlue, SplashProfileStickerLabel, SplashProfileStickerLabelBlue };
