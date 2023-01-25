import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { LabelMedium } from '../../styles/typo';

/**
 * 카테고리로 이동하는 링크 컴포넌트 atom
 * @param {string|number} id - 카테고리의 id
 * @param {*} children - 하위 컴포넌트
 * @param {string} padding - 적용할 패딩값
 * @returns {JSX.Element} - Link 태그
 */
const Category = ({ id, children, padding, color }) => {
  const Container = styled(Link)`
    ${LabelMedium};
    width: 100%;
    font-weight: 700;
    color: white;
    background-color: ${() => color || 'black'};
    box-sizing: border-box;
    padding: ${() => padding || '10px'};
    text-decoration: none;
  `;
  return <Container to={`/posts/${id}`}>{children}</Container>;
};

export default Category;
