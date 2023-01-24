import styled from 'styled-components';
import { HeadingLarge } from '../../styles/typo';
import { BlueShadowButton } from '../atoms/Buttons';
import Nickname from '../atoms/Nickname';
import UserImage from '../atoms/UserImage';

/**
 * 프로필 이미지와 닉네임 정보를 담은 molecules
 * @param {string|number} id - 유저 고유 아이디
 * @param {string} name - 유저 닉네임
 * @param {string} introduction - 유저 소개
 * @param {string} image - 유저 프로필 이미지 URL
 * @returns {JSX.Element} - 프로필이미지, 닉네임정보를 담은 컴포넌트
 */
const UserInfo = ({ id, name, introduction, image }) => {
  return (
    <UserInfoBigContainer>
      <UserImage src={image} sizes="128px" />
      <UserHeader>
        <Nickname id={id} name={name} color="var(--orange-400)" typo={HeadingLarge} />
        <Description>{introduction}</Description>
      </UserHeader>
      {false ? <div style={{ width: '116px' }} /> : <BlueShadowButton to="edit">Edit</BlueShadowButton>}
    </UserInfoBigContainer>
  );
};

const UserInfoSmall = ({ id, name, image, color, size }) => {
  return (
    <Container>
      <UserImage src={image || 'https://unsplash.it/1920/1080/?random'} sizes={size || '24px'} />
      <Nickname id={id} name={name || 'Name'} color={color} />
    </Container>
  );
};

const Container = styled.div`
  display: flex;
  gap: 24px;
  align-items: center;
`;

const UserInfoBigContainer = styled.div`
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 227px;
  padding: 20px 60px;
  border: 5px solid var(--gray-900);
  box-shadow: var(--boxShadow-01) var(--gray-700);
  border-radius: 15px;
`;

const UserHeader = styled.div`
  display: flex;
  height: 80px;
  flex-direction: column;
  justify-content: space-between;
  width: 50%;
  margin-right: 100px;
`;

const Description = styled.div`
  display: block;
  width: 100%;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
`;

export { UserInfo, UserInfoSmall };
