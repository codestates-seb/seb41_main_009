import styled from 'styled-components';
import Box from '../atoms/Box';
import CategoryTag from '../atoms/Category';

/**
 * 쇼케이스에서 사용하는 이미지 썸네일 molecules
 * @param {string|number} id - 해당 이미지의 카테고리
 * @param {string} name - 카테고리 이름
 * @param {string} thumnail - 썸네일 URL
 * @returns {JSX.Element} - 쇼케이스용 이미지 썸네일 컴포넌트
 */
const ImageCard = ({ id, name, thumnail }) => {
  return (
    <Box padding="0px" boxShadow="null">
      <ThumnailCard src={thumnail} />
      <CategoryTag id={id}>{name}</CategoryTag>
    </Box>
  );
};

const ThumnailCard = styled.img`
  display: block;
  width: 100%;
  object-fit: cover;
  min-height: 200px;
  max-height: 453px;
  background-color: var(--gray-50);
`;

export default ImageCard;
