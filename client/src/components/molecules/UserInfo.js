import styled from 'styled-components';
import Nickname from '../atoms/Nickname';
import UserImage from '../atoms/UserImage';

/**
 * 프로필 이미지와 닉네임 정보를 담은 molecules
 * @param {string|number} id - 유저 고유 아이디
 * @param {string} name - 유저 닉네임
 * @param {string} image - 유저 프로필 이미지 URL
 * @param {string} color - 유저 닉네임 색상
 * @param {CSSProperties} typo - 적용할 타이포
 * @param {*} children - 하위 컴포넌트
 * @returns {JSX.Element} - 프로필이미지, 닉네임정보를 담은 컴포넌트
 */
const UserInfo = ({ id, name, image, color, size, typo, children }) => {
  return (
    <Container>
      <UserImage src={image} sizes={size} />
      <Nickname id={id} name={name} color={color} typo={typo} />
      {children}
    </Container>
  );
};

const UserInfoSmall = ({ id, name, image, color }) => {
  return (
    <Container>
      <UserImage src={image} sizes="24px" />
      <Nickname id={id} name={name} color={color} />
    </Container>
  );
};
const Container = styled.div`
  display: flex;
  gap: 24px;
  align-items: center;
`;

export { UserInfo, UserInfoSmall };
