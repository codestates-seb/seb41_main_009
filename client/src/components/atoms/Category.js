import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { LabelMedium } from '../../styles/typo';
import { TAGNAME_DICT } from '../../constants/Categories';

/**
 * 카테고리로 이동하는 링크 컴포넌트 atom
 * @param {string|number} id - 카테고리의 id
 * @param {*} children - 하위 컴포넌트
 * @param {string} padding - 적용할 패딩값
 * @returns {JSX.Element} - Link 태그
 */
const Category = ({ id, padding, color }) => {
  return (
    <Container color={color} padding={padding} to={`/posts/${id}`}>
      {TAGNAME_DICT[id]}
    </Container>
  );
};

const Container = styled(Link)`
  ${LabelMedium};
  width: 100%;
  font-weight: 700;
  color: white;
  background-color: ${props => props.color || 'black'};
  box-sizing: border-box;
  padding: ${props => props.padding || '10px'};
  text-decoration: none;
`;

export default Category;
