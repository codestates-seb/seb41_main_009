import styled from 'styled-components';

const AcrylicBase = styled.div`
  display: flex;
  flex-direction: ${props => props.flexDirection || 'row'};
  justify-content: center;
  align-items: center;
  padding: 10px;
  gap: 10px;

  position: relative;
  width: ${props => props.width || 'inherit'};
  height: ${props => props.height || 'inherit'};

  box-sizing: border-box;
  background: rgba(45, 45, 45, 0.44);
  background-blend-mode: normal, luminosity;
  backdrop-filter: blur(50px);
  border-radius: 30px;
  overflow: hidden;
  &:hover {
    background-color: rgba(40, 40, 40, 0.54);
  }
`;

const AcrylicBaseDark = styled.div`
  position: relative;
  width: ${props => props.width || 'inherit'};
  height: ${props => props.height || 'inherit'};
  box-sizing: border-box;
  background: rgba(45, 45, 45, 0.64);
  background-blend-mode: normal, luminosity;
  backdrop-filter: blur(30px);
  border-radius: 7px;
`;

export { AcrylicBase, AcrylicBaseDark };
