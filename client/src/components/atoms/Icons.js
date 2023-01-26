import styled from 'styled-components';

const Sticker = styled.img`
  width: 20px;
  height: 20px;
  src: ${props => (props.src ? props.src : 'https://api.iconify.design/mdi/face-man-shimmer.svg')};
`;

export default Sticker;
