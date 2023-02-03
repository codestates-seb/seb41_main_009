import styled from 'styled-components';
import TiltedContainer from './TiltedContainer';
import { LabelListTitle } from '../../../styles/typo';

const Container = styled.div`
  width: 1056px;
  height: 260px;

  /* all/blue400 */

  background: ${props => (props.background ? props.background : 'var(--gray-50)')};
  box-shadow: ${props => (props.boxShadow ? props.boxShadow : '')};
  border-radius: 15px;
  overflow: hidden;
`;

const ProfileContainer = styled.div`
  position: relative;
  width: 512px;
  height: 717px;

  background: ${props => (props.background ? props.background : 'var(--gray-50)')};
  box-shadow: ${props => (props.boxShadow ? props.boxShadow : '')};
  border-radius: 15px;
  overflow: hidden;
`;

const ProfileInfoText = styled.div`
  position: absolute;
  width: 180px;
  height: 80px;
  left: 46px;
  top: 43px;

  ${LabelListTitle}
`;

const ProfileInfoTextEmphasizeA = styled.span`
  color: var(--orange-400);
`;

const SplashStickerLabelDefault = ({ boyMessage, girlMessage, emailMessage, createdAtMessage }) => {
  return (
    <Container>
      <TiltedContainer
        boyMessage={boyMessage}
        girlMessage={girlMessage}
        emailMessage={emailMessage}
        createdAtMessage={createdAtMessage}
      />
    </Container>
  );
};

const SplashStickerLabelBlue = ({ background = '#0C3BDD', boxShadow = '4px 4px 0px #F77E10;' }) => {
  return (
    <Container background={background} boxShadow={boxShadow}>
      <TiltedContainer />
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
  boyMessage,
  girlMessage,
  emailMessage,
  createdAtMessage,
}) => {
  return (
    <ProfileContainer background={background} boxShadow={boxShadow}>
      <ProfileInfoText>
        Share Your Interest
        <ProfileInfoTextEmphasizeA>,</ProfileInfoTextEmphasizeA> And Dive
        <ProfileInfoTextEmphasizeA> IntoRest</ProfileInfoTextEmphasizeA>
      </ProfileInfoText>
      <TiltedContainer
        width={width}
        height={height}
        left={left}
        top={top}
        boyMessage={boyMessage}
        girlMessage={girlMessage}
        emailMessage={emailMessage}
        createdAtMessage={createdAtMessage}
      />
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
      <TiltedContainer width={width} height={height} left={left} top={top} />
    </ProfileContainer>
  );
};

export { SplashStickerLabelDefault, SplashStickerLabelBlue, SplashProfileStickerLabel, SplashProfileStickerLabelBlue };
