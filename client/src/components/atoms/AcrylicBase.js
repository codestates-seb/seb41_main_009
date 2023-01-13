import styled from 'styled-components';

/**
 * @width : 상속
 * @height : 상속
 * 순서를 어떻게 정해야할지 모르겠음
 * z-index 또는 order 설정해야함
 */
const AcrylicBase = styled.div`
  position: absolute;
  width: ${props => props.width || 'inherit'};
  height: ${props => props.height || 'inherit'};
  box-sizing: border-box;
  background: rgba(211, 211, 211, 0.44);
  background-blend-mode: luminosity;
  backdrop-filter: blur(30px);
  border-radius: 7px;
`;

const AcrylicBaseDark = styled.div`
  position: absolute;
  width: ${props => props.width || 'inherit'};
  height: ${props => props.height || 'inherit'};
  box-sizing: border-box;
  background: rgba(45, 45, 45, 0.44);
  background-blend-mode: normal, luminosity;
  backdrop-filter: blur(30px);
  border-radius: 7px;
`;

export { AcrylicBase, AcrylicBaseDark };
