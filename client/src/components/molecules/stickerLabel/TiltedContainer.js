import styled from 'styled-components';
import { StickerCreatedAt, StickerNicknameBoy, StickerEmail, StickerNicknameGirl } from './StickerLabel';

const Container = styled.div`
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

const TiltedContainer = ({ width, height, left, top }) => {
  return (
    <Container width={width} height={height} left={left} top={top}>
      <StickerLabelLine>
        <StickerNicknameBoy />
        <StickerNicknameGirl />
        <StickerNicknameBoy />
        <StickerNicknameGirl />
        <StickerNicknameBoy />
        <StickerNicknameGirl />
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
    </Container>
  );
};

export default TiltedContainer;
