import styled from 'styled-components';
import Nickname from '../atoms/Nickname';
import UserImage from '../atoms/UserImage';

/**
 * 프로필 이미지와 닉네임 정보를 담은 molecules
 * @param {string|number} id - 유저 고유 아이디
 * @param {string} name - 유저 닉네임
 * @param {string} image - 유저 프로필 이미지 URL
 * @param {string} color - 유저 닉네임 색상
 * @returns {JSX.Element} - 프로필이미지, 닉네임정보를 담은 컴포넌트
 */
const UserInfo = ({ id, name, image, color }) => {
  return (
    <Container>
      <UserImage src={image} />
      <Nickname id={id} name={name} color={color} />
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  gap: 24px;
  align-items: center;
`;

export default UserInfo;
