import styled from 'styled-components';
import { LabelMedium } from '../../styles/typo';

/**
 * 컨테이너 스타일용 박스 컴포넌트 atom
 * @param {string} color - 백그라운드 색상을 지정
 * @param {string} padding - 패딩값을 지정
 * @param {string} border - 보더스타일 지정
 * @param {string} boxShadow - 박스의 그림자값을 지정함
 * @param children - 하위 컴포넌트
 * @returns {JSX.Element} - div 태그
 */
const Box = ({ color, padding, margin, border, boxShadow, children }) => {
  return (
    <Container color={color} padding={padding} margin={margin} border={border} boxShadow={boxShadow}>
      {children}
    </Container>
  );
};

const Container = styled.div`
  ${LabelMedium};
  display: flex;
  flex-direction: column;
  width: 100%;
  box-sizing: border-box;
  margin: ${props => props.margin || 'null'};
  background-color: ${props => props.color || 'white'};
  padding: ${props => props.padding || '14px 15px'};
  border: ${props => props.border || '2px solid black'};
  box-shadow: ${props => props.boxShadow || `var(--boxShadow-00) black`};
`;

export default Box;
