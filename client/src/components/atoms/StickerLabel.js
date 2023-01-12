import styled from 'styled-components';
import Sticker from './icons';

const Container = styled.div`
  box-sizing: border-box;

  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
  padding: 10px 20px 10px 40px;
  gap: 10px;
  width: ${props => (props.width ? props.width : 'fit-content')};
  height: ${props => (props.height ? props.height : '44px')};

  background: #ffffff;
  border: 5px solid #000000;
  box-shadow: 7px 7px 0px #000000;
  border-radius: 14px;
`;

const Label = styled.div`
  font-family: 'Roboto';
  font-style: normal;
  font-weight: 800;
  font-size: 18px;
  line-height: 24px;

  color: #333333;
`;

const StickerNicknameBoy = ({ width, height, message }) => {
  return (
    <Container width={width} height={height}>
      <Sticker src="https://api.iconify.design/mdi/face-man-shimmer.svg" />
      <Label> {message || 'Nickname'}</Label>
    </Container>
  );
};
const StickerNicknameGirl = ({ width, height, message }) => {
  return (
    <Container width={width} height={height}>
      <Sticker src="https://api.iconify.design/mdi/face-woman-shimmer.svg" />
      <Label> {message || 'Nickname'}</Label>
    </Container>
  );
};

const StickerEmail = ({ width, height, message }) => {
  return (
    <Container width={width} height={height}>
      <Sticker src="https://api.iconify.design/mdi/face-woman-shimmer.svg" />
      <Label> {message || 'Email'}</Label>
    </Container>
  );
};

const StickerCreatedAt = ({ width, height, message }) => {
  return (
    <Container width={width} height={height}>
      <Sticker src="https://api.iconify.design/mdi/face-woman-shimmer.svg" />
      <Label> {message || 'CreatedAt'}</Label>
    </Container>
  );
};

export { StickerNicknameBoy, StickerNicknameGirl, StickerEmail, StickerCreatedAt };
