import styled from 'styled-components';

/**
 * border-radius 가 적용된 프로필 이미지 atom
 * @param {string} sizes - 이미지크기
 * @returns {JSX.Element} - img 태그
 */
const UserImage = styled.img`
  width: ${props => (props.sizes ? props.sizes : '36px')};
  height: ${props => (props.sizes ? props.sizes : '36px')};
  border-radius: 30px;
  background-color: #d9d9d9;
  cursor: pointer;
`;

export default UserImage;
