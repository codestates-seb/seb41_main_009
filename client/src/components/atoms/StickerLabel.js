import styled from 'styled-components';
import { Icon } from '@iconify/react';

const Container = styled.div`
  box-sizing: border-box;

  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
  padding: 10px 20px 10px 40px;
  gap: 10px;
  width: ${props => (props.width ? props.width : '172px')};
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
`;

const StickerLabel = ({ width, height }) => {
  return (
    <Container width={width} height={height}>
      <Icon icon="mdi:face-man-shimmer" />
      <Label> asd</Label>
    </Container>
  );
};

export default StickerLabel;
