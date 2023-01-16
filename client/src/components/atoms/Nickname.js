import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { LabelMedium } from '../../styles/typo';

/**
 * 카테고리로 이동하는 링크 컴포넌트 atom
 * @param {string} name - 표시될 유저의 닉네임
 * @param {string|number} id - 유저의 고유번호
 * @param {string} color - 닉네임에 적용할 색깔
 * @param {CSSProperties} typo - 적용할 타이포 CSS 프로퍼티
 * @returns {JSX.Element} - Link 태그
 */
const Nickname = ({ name, id, color, typo }) => {
  return (
    <Container to={`/user/${id}`} color={color} typo={typo}>
      {name}
    </Container>
  );
};

const Container = styled(Link)`
  ${props => props.typo || LabelMedium};
  color: ${props => props.color || 'inherit'};
  cursor: pointer;
  text-decoration: none;
`;

export default Nickname;
